// BankAccount class
class BankAccount {
    protected double balance;

    public BankAccount() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }
}

// SavingsAccount class
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: $" + interest);
    }
}

// Main class for testing
public class Prac61_BankAccountExample {
    public static void main(String[] args) {
        // Creating a SavingsAccount with a 3% interest rate
        SavingsAccount savingsAccount = new SavingsAccount(3.0);

        // Depositing and checking balance
        savingsAccount.deposit(1000);
        savingsAccount.checkBalance();

        // Withdrawing and checking balance
        savingsAccount.withdraw(200);
        savingsAccount.checkBalance();

        // Adding interest and checking balance
        savingsAccount.addInterest();
        savingsAccount.checkBalance();
    }
}

