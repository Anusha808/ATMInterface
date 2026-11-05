package com.atm;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();

    public Bank() {
        accounts.add(new Account("user1", "1234", "1001", 10000));
        accounts.add(new Account("user2", "5678", "1002", 5000));
        accounts.add(new Account("user3", "9999", "1003", 2000));
    }

    public Account authenticate(String userId, String pin) {
        for (Account account : accounts) {
            if (account.getUserId().equals(userId)
                    && account.validatePin(pin)) {
                return account;
            }
        }

        return null;
    }

    public Account findAccount(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }

        return null;
    }
}