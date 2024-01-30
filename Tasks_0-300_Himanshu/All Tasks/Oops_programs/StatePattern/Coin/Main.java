package Coin;

import java.util.Scanner;

interface vendingMachine {
    public void change();
}

class Context {
    public vendingMachine changeMachineState;

    public void setState(vendingMachine changeMachineState) {
        this.changeMachineState = changeMachineState;
    }

    public void change() {
        changeMachineState.change();
    }
}

class NoCoinState implements vendingMachine {
    @Override
    public void change() {
        System.out.println("State is changes to Without Coin State");
        System.out.println("Make payment with Notes, Creditcard or Debitcard.");
    }
}

class HasCoinState implements vendingMachine {
    @Override
    public void change() {
        System.out.println("State is change to Coin State");
        System.out.println("Make payment using Coin.");
    }
}

public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the Payment Method 0.Coin && 1.without Coin : ");
        int method = sc.nextInt();

        if (method == 0) {
            context.setState(new HasCoinState());
            context.change();
        } else {
            context.setState(new NoCoinState());
            context.change();
        }

    }
}
