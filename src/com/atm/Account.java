package com.atm;

public class Account {
    private String userId;
    private String pin;
    private String accountId;
    private double balance;

    public Account(String userId, String pin, String accountId, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }
}