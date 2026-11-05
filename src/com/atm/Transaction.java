package com.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private double amount;
    private String details;
    private LocalDateTime timestamp;

    public Transaction(String type, double amount, String details) {
        this.type = type;
        this.amount = amount;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return String.format(
                "%-12s | ₹%-10.2f | %-35s | %s",
                type,
                amount,
                details,
                timestamp.format(formatter)
        );
    }
}