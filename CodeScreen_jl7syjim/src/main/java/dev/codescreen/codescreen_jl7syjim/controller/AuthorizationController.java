package dev.codescreen.codescreen_jl7syjim.controller;

import dev.codescreen.codescreen_jl7syjim.api.AuthorizationApi;
import dev.codescreen.codescreen_jl7syjim.model.*;
import dev.codescreen.codescreen_jl7syjim.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static dev.codescreen.codescreen_jl7syjim.CodeScreenJl7syjimApplication._transactionHistory;
import static dev.codescreen.codescreen_jl7syjim.CodeScreenJl7syjimApplication._userAccountsDB;
import static dev.codescreen.codescreen_jl7syjim.controller.LoadController.replayTransactions;

@RestController
public class AuthorizationController implements AuthorizationApi {


    public ResponseEntity<?> authorizationPut(@RequestBody  AuthorizationRequest authorizationRequest) {

        // Read Request Parameters
        String _userId = authorizationRequest.getUserId();
        String _messageId = authorizationRequest.getMessageId();
        String _currency = authorizationRequest.getTransactionAmount().getCurrency();
        String _amount = authorizationRequest.getTransactionAmount().getAmount();
        DebitCredit _debitOrCredit = authorizationRequest.getTransactionAmount().getDebitOrCredit();

        if (!_userAccountsDB.containsKey(_userId)) // Non-Existing User
        {
            Error _error = new Error();
            _error.setCode("USER NOT FOUND: " + _userId);
            _error.setMessage("The user account you are trying to remove funds from does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_error);
        }

        if (DebitCredit.CREDIT == _debitOrCredit) // Invalid Operation
        {
            Error _error = new Error();
            _error.setCode("INVALID OPERATION: CREDIT");
            _error.setMessage("To Remove the funds, operation specified must be DEBIT. The operation specified was CREDIT.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_error);
        }

        if (!_currency.equals("USD")) // Invalid Currency
        {
            Error _error = new Error();
            _error.setCode("CURRENCY UNSUPPORTED");
            _error.setMessage("Removing funds is only supported in USD currency.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(_error);
        }




        Account _userAccount = _userAccountsDB.get(_userId);
        BigDecimal _userBalance = _userAccount.getBalance();
        String RESPONSE_STATUS = "";

        if(new BigDecimal(_amount).compareTo(BigDecimal.valueOf(0.0)) >= 0  &&_userBalance.compareTo(new BigDecimal(_amount) ) >= 0) // Sufficient Funds to remove && Non-negative Transaction amount
        {
            _userBalance = _userBalance.subtract(new BigDecimal(_amount)); // Calculate Balance
            _userAccountsDB.get(_userId).setBalance(_userBalance); // Update Balance in the database
            RESPONSE_STATUS = "APPROVED";
        }
        else { //Insufficient Funds
            RESPONSE_STATUS = "DECLINED";
        }

        // Calculate Amount and Response Code
        Amount _amountObj = new Amount(_userBalance.toString(), _currency, _debitOrCredit);
        ResponseCode _responseCode = ResponseCode.valueOf(RESPONSE_STATUS);

        // Set the Authorization Response field.
        AuthorizationResponse _authorizationResponse;
        _authorizationResponse = setAuthorizationResponse(_userId, _messageId, _responseCode, _amountObj);

        //Log an authorization event
        handleAuthorizationEvent(_userId, _messageId, _amount, _amountObj, _responseCode);

        // Successful return
        return ResponseEntity.status(HttpStatus.OK).body(_authorizationResponse);

    }


    // **************************** Helper Functions *****************************

    private AuthorizationResponse setAuthorizationResponse(String userId, String messageId,  ResponseCode responseCode, Amount balance)
    {
        AuthorizationResponse _authorizationResponse = new AuthorizationResponse();

        _authorizationResponse.setUserId(userId);
        _authorizationResponse.setMessageId(messageId);
        _authorizationResponse.setResponseCode(responseCode);
        _authorizationResponse.setBalance(balance);

        return _authorizationResponse;
    }

    private void handleAuthorizationEvent(String userId, String messageId, String transactionAmount, Amount _authorizationAmountObj, ResponseCode responseCode)
    {
        //Prepare Event Object
        TransactionEvent _authorizationEventObj = new TransactionEvent(userId, messageId, OffsetDateTime.now().toString(), "AUTHORIZATION", _authorizationAmountObj, transactionAmount, responseCode);

        //Add it to DB logs
        _transactionHistory.add(_authorizationEventObj);

        replayTransactions();
    }
}
