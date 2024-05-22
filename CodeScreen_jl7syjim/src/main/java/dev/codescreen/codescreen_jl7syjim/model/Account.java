package dev.codescreen.codescreen_jl7syjim.model;

import java.math.BigDecimal;

public class Account {

    private static final long serialVersionUID = 1L;

    private String userId;

    private BigDecimal balance;

    public Account(String userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
