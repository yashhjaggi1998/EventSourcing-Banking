package dev.codescreen.codescreen_jl7syjim.controller;

import dev.codescreen.codescreen_jl7syjim.api.LoadApi;
import dev.codescreen.codescreen_jl7syjim.model.*;
import dev.codescreen.codescreen_jl7syjim.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static dev.codescreen.codescreen_jl7syjim.CodeScreenJl7syjimApplication._transactionHistory;
import static dev.codescreen.codescreen_jl7syjim.CodeScreenJl7syjimApplication._userAccountsDB;


@RestController
public class LoadController implements LoadApi {

    @Override
    public ResponseEntity<?> loadPut(LoadRequest request) {

        // Read request parameters
        String _userId = request.getUserId();
        String _messageId = request.getMessageId();
        String _currency = request.getTransactionAmount().getCurrency();
        String _amount = request.getTransactionAmount().getAmount();
        DebitCredit _debitOrCredit = request.getTransactionAmount().getDebitOrCredit();

        // Non-Empty user cannot be processed.
        if (_userId.isEmpty())
        {
            Error _error = new Error();
            _error.setCode("EMPTY USER");
            _error.setMessage("Cannot load funds since no user is mentioned.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_error);
        }

        if (!_currency.equals("USD"))
        {
            Error _error = new Error();
            _error.setCode("CURRENCY UNSUPPORTED");
            _error.setMessage("Adding of funds is only supported in USD currency.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_error);
        }


        // If the user does not exist we create the user in the database
        if(!_userAccountsDB.containsKey(_userId)) {
            _userAccountsDB.put(_userId, new Account(_userId, new BigDecimal(0)));
        }

        Account _account = _userAccountsDB.get(_userId);

        // Load functionality must be passed CREDIT as the operation
        if( _debitOrCredit == DebitCredit.CREDIT) {

            BigDecimal _balance = _account.getBalance();

            // Check if Transaction amount is non-negative
            if(new BigDecimal(_amount).compareTo(BigDecimal.valueOf(0.0)) < 0 ) {

                handleLoadEvent(_userId, _messageId, _amount, _balance, _currency, _debitOrCredit, ResponseCode.valueOf("DECLINED")); //Handle the load event to add the transaction as part of event history
                LoadResponse _loadResponse = setLoadResponse(_userId, _messageId, _balance, _currency, _debitOrCredit, ResponseCode.valueOf("DECLINED"));  // Set up response object
                return ResponseEntity.status(HttpStatus.OK).body(_loadResponse);
            }

            // Successful Load Operation

            _balance = _balance.add(new BigDecimal(_amount)); // Calculate the user balance
            _userAccountsDB.get(_userId).setBalance(_balance); // Update the user balance in the database

            handleLoadEvent(_userId, _messageId, _amount, _balance, _currency, _debitOrCredit, ResponseCode.valueOf("APPROVED")); //Handle the load event to add the transaction as part of event history
            LoadResponse _loadResponse = setLoadResponse(_userId, _messageId, _balance, _currency, _debitOrCredit, ResponseCode.valueOf("APPROVED"));  // Set up response object

            return ResponseEntity.status(HttpStatus.OK).body(_loadResponse);
        }
        else {
            Error _error = new Error();
            _error.setCode("OPERATION INVALID: DEBIT");
            _error.setMessage("Cannot load funds as the operation specified is DEBIT");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_error);
        }
    }


    // ************************************************ Helper Functions *******************************************

    private LoadResponse setLoadResponse( String userId, String messageId, BigDecimal balance, String currency, DebitCredit debitOrCredit, ResponseCode responseCode ) {

        LoadResponse _loadResponse = new LoadResponse();

        _loadResponse.setUserId(userId);
        _loadResponse.setMessageId(messageId);
        _loadResponse.setBalance(new Amount(balance.toString(), currency, debitOrCredit ));
        _loadResponse.setResponseCode(responseCode);

        return _loadResponse;
    }


    private void handleLoadEvent( String userId, String messageId, String transactionAmount, BigDecimal balance, String currency, DebitCredit debitOrCredit, ResponseCode responseCode) {
        Amount _loadAmountObj = new Amount(balance.toString(), currency, debitOrCredit);
        TransactionEvent _loadEventObj = new TransactionEvent(userId, messageId, OffsetDateTime.now().toString(), "LOAD", _loadAmountObj, transactionAmount, responseCode);

        _transactionHistory.add(_loadEventObj);

        replayTransactions();
    }


    public static void replayTransactions() {
        System.out.println("\n*************************************************");
        for (TransactionEvent _transactionEvent : _transactionHistory) {

            System.out.println(_transactionEvent.getUserId());
            System.out.println(_transactionEvent.getMessageId());
            System.out.println(_transactionEvent.getEvent());
            System.out.println(_transactionEvent.getTimeStamp());
            System.out.println("Balance: " + _transactionEvent.getAmount().getAmount());
            System.out.println("Response Code: " + _transactionEvent.getResponseCode());
            System.out.println("Transaction Amount: " + _transactionEvent.getTransactionAmount() + "\n");
        }
        System.out.println("*************************************************\n");
    }
}
