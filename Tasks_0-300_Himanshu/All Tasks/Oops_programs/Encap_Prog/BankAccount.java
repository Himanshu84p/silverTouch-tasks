package Oops_programs.Encap_Prog;

public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited amount " + amount);
        } else {
            System.out.println("Invalid input!!");
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            System.out.println("Withdrawal amount is " + amount);
        } else {
            System.out.println("insufficient amount to withdraw");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("123456789", 1000);

        System.out.println("initial balance" + acc1.getBalance());
        acc1.deposit(500);
        acc1.withdraw(600);
        System.out.println("total balance after deposit " + acc1.getBalance());

    }

}
