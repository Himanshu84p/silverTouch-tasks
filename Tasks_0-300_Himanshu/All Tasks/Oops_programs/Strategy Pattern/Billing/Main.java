package Billing;

import java.util.Scanner;

interface Billing {
    public void billCreation(double amount);
}

class Context {
    private Billing Bill;

    public Context(Billing Bill) {
        this.Bill = Bill;
    }

    public void makeBill(double amount) {
        Bill.billCreation(amount);
    }
}

class normalBill implements Billing {

    private double initialAmt;

    public normalBill(double amount) {
        this.initialAmt = amount;
    }

    @Override
    public void billCreation(double amount) {
        if (initialAmt == amount) {
            System.out.println("Normal Bill With Amount " + amount + " is created!!");
        }
    }
}

class discountBill implements Billing {
    private double initialAmt;

    public discountBill(double amt) {
        this.initialAmt = amt;
    }

    @Override
    public void billCreation(double amount) {
        if (amount < initialAmt) {
            System.out.println(
                    "Discounted bill of " + amount + " is generated with discount of " + (initialAmt - amount));
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the intial amount : ");
        double amt = sc.nextInt();
        System.out.println("Enter the Discounted amount : ");
        double disamt = sc.nextInt();

        Billing normal = new normalBill(amt);
        Billing discount = new discountBill(amt);

        Context normalContext = new Context(normal);
        normalContext.makeBill(amt);
        Context discountContext = new Context(discount);
        discountContext.makeBill(disamt);
    }

}