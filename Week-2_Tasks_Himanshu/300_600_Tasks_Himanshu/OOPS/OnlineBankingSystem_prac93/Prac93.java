package OnlineBankingSystem_prac93;

import java.util.Date;
import java.util.Vector;

class Transaction {
    private String type;
    private double amount;
    private String date;

    public Transaction(String transType, double transAmount, String transDate) {
        type = transType;
        amount = transAmount;
        date = transDate;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

class Account {
    private int accountNumber;
    private double balance;
    private Vector<Transaction> transactionHistory;

    public Account(int accNumber) {
        accountNumber = accNumber;
        balance = 0.0;
        transactionHistory = new Vector<Transaction>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount, new Date().toString()));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount, new Date().toString()));
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History for Account " + accountNumber + ":");
        System.out.println("--------------------------------------------");
        for (Transaction transaction : transactionHistory) {
            System.out.println("Type: " + transaction.getType());
            System.out.println("Amount: $" + transaction.getAmount());
            System.out.println("Date: " + transaction.getDate());
            System.out.println("--------------------------------------------");
        }
    }
}

class Customer {
    private String name;
    private Vector<Account> accounts;

    public Customer(String customerName) {
        name = customerName;
        accounts = new Vector<Account>();
    }

    public String getName() {
        return name;
    }

    public void openAccount(int accountNumber) {
        accounts.add(new Account(accountNumber));
    }

    public void transferMoney(int fromAccount, int toAccount, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == fromAccount) {
                account.withdraw(amount);
            } else if (account.getAccountNumber() == toAccount) {
                account.deposit(amount);
            }
        }
    }

    public void checkAccountBalance(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                System.out.println("Account Balance for Account " + accountNumber + ": $" + account.getBalance());
                return;
            }
        }
        System.out.println("Account not found!");
    }

    public void viewTransactionHistory(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.viewTransactionHistory();
                return;
            }
        }
        System.out.println("Account not found!");
    }
}

public class Prac93 {
    public static void main(String[] args) {
        Customer customer = new Customer("John Doe");
        customer.openAccount(12345);
        customer.openAccount(67890);

        customer.transferMoney(12345, 67890, 100.0);

        customer.checkAccountBalance(12345);
        customer.checkAccountBalance(67890);

        customer.viewTransactionHistory(12345);
        customer.viewTransactionHistory(67890);
    }
}