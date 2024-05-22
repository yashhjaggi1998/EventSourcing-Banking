package dev.codescreen.codescreen_jl7syjim.model;

public class TransactionEvent {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String messageId;

    private String timeStamp;

    private String event;

    private String transactionAmount;

    private Amount amount;

    private ResponseCode responseCode;

    public TransactionEvent() {}

    public TransactionEvent(String userId, String messageId, String timeStamp, String event, Amount amount, String transactionAmount, ResponseCode responseCode) {
        this.userId = userId;
        this.messageId = messageId;
        this.timeStamp = timeStamp;
        this.event = event;
        this.amount = amount;
        this.transactionAmount = transactionAmount;
        this.responseCode = responseCode;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getEvent() {
        return event;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
