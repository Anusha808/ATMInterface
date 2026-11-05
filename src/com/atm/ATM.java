
package com.atm;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class ATM {
    private Bank bank;
    private Account currentAccount;
    private Scanner scanner;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        if (!login()) {
            System.out.println("Access denied. Exiting ATM.");
            return;
        }

        showMenu();
    }

    private boolean login() {
        int attempts = 0;

        while (attempts < 3) {
            System.out.println("\n===== ATM LOGIN =====");

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            String pin = getMaskedPin();

            if (pin.isEmpty()) {
                System.out.println("PIN entry cancelled.");
                return false;
            }

            currentAccount = bank.authenticate(userId, pin);

            if (currentAccount != null) {
                System.out.println("\nLogin successful!");
                System.out.println("Account Number: "
                        + currentAccount.getAccountId());
                return true;
            }

            attempts++;
            System.out.println("Invalid User ID or PIN.");
            System.out.println("Attempts remaining: " + (3 - attempts));
        }

        return false;
    }

    private String getMaskedPin() {
        JPasswordField pinField = new JPasswordField(10);

        int option = JOptionPane.showConfirmDialog(
                null,
                pinField,
                "Enter PIN",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            return new String(pinField.getPassword());
        }

        return "";
    }

    private void showMenu() {
        int choice;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ₹");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (currentAccount.withdraw(amount)) {
            transactions.add(new Transaction(
                    "Withdrawal",
                    amount,
                    "Account: " + currentAccount.getAccountId()
            ));

            System.out.println("Withdrawal successful.");
            System.out.printf("Remaining balance: ₹%.2f%n",
                    currentAccount.getBalance());
        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ₹");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        currentAccount.deposit(amount);

        transactions.add(new Transaction(
                "Deposit",
                amount,
                "Account: " + currentAccount.getAccountId()
        ));

        System.out.println("Deposit successful.");
        System.out.printf("Updated balance: ₹%.2f%n",
                currentAccount.getBalance());
    }

    private void transfer() {
        System.out.print("Enter recipient account ID: ");
        String recipientId = scanner.nextLine();

        Account recipient = bank.findAccount(recipientId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (recipient.getAccountId().equals(currentAccount.getAccountId())) {
            System.out.println("Cannot transfer to your own account.");
            return;
        }

        System.out.print("Enter transfer amount: ₹");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (currentAccount.withdraw(amount)) {
            recipient.deposit(amount);

            transactions.add(new Transaction(
                    "Transfer",
                    amount,
                    "From: " + currentAccount.getAccountId()
                            + " To: " + recipient.getAccountId()
            ));

            System.out.println("Transfer successful.");
            System.out.printf("Remaining balance: ₹%.2f%n",
                    currentAccount.getBalance());
        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    private void showTransactionHistory() {
        System.out.println("\n================ TRANSACTION HISTORY ================");
        System.out.printf("%-12s | %-11s | %-35s | %s%n",
                "Type", "Amount", "Account Details", "Date & Time");
        System.out.println("---------------------------------------------------"
                + "-----------------------------------------------");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private double readAmount() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid amount: ₹");
            }
        }
    }
}
