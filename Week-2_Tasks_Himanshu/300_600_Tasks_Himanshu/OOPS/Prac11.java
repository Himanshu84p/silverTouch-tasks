import java.util.Scanner;

abstract class ChainHandler{
    public ChainHandler next;

    public void setNext(ChainHandler next){
        this.next = next;
    }

    public abstract void handleChain(double num);
}

class FirstHandler extends ChainHandler{
    @Override
    public void handleChain(double num){
        if(num<=10){
            System.out.println("First Handle can handle this.");
        } else {
            next.handleChain(num);
        }
    }
}

class SecondHandler extends ChainHandler{
    @Override
    public void handleChain(double num){
        if(num<=20){
            System.out.println("Second Handle can handle this.");
        } else {
            next.handleChain(num);
        }
    }
}

class ThirdHandler extends ChainHandler{
    @Override
    public void handleChain(double num){
        if(num<=30){
            System.out.println("Third Handle can handle this.");
        } else {
            next.handleChain(num);
        }
    }
}

public class Prac11 {
    public static void main(String[] args) {
        ChainHandler First = new FirstHandler();
        ChainHandler Second = new SecondHandler();
        ChainHandler Third = new ThirdHandler();

        First.setNext(Second);
        Second.setNext(Third);

        System.out.println("Number should not be more than!!");
        System.out.print("Enter Your number to check which handle can handle this : ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.close();

        First.handleChain(num);

    }
}
