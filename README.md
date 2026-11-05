# ATM Interface

A console-based ATM simulation built using Java and Object-Oriented Programming (OOP). The application allows users to log in securely and perform basic banking transactions.

## Features

- User login with User ID and PIN
- Maximum of 3 incorrect login attempts
- Masked PIN input using a password dialog
- Withdraw money with balance validation
- Deposit money
- Transfer money between accounts
- View transaction history with account numbers, amounts, and timestamps
- Prevent transfers to invalid or identical accounts
- Display updated account balance after transactions

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Swing (`JPasswordField`, `JOptionPane`) for masked PIN input
- ArrayList for transaction history

## Project Structure

```text
ATMInterface/
├── src/
│   └── com/
│       └── atm/
│           ├── Account.java
│           ├── ATM.java
│           ├── Bank.java
│           ├── Main.java
│           └── Transaction.java
├── README.md
└── .gitignore
```

## Sample Login Credentials

| User ID | PIN | Account ID | Initial Balance |
|---|---|---|---:|
| user1 | 1234 | 1001 | ₹10,000 |
| user2 | 5678 | 1002 | ₹5,000 |
| user3 | 9999 | 1003 | ₹2,000 |

## How to Run

### Prerequisites

- Java JDK 17 or later
- Git (optional, for cloning the repository)

### Compile

Open a terminal in the project directory and run:

```bash
javac -d out src/com/atm/*.java
```

### Run

```bash
java -cp out com.atm.Main
```

## ATM Menu

```text
===== ATM MENU =====
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
```

## Transaction History

The transaction history records successful deposits, withdrawals, and transfers during the current session. It displays the transaction type, amount, account details, and date/time.


