
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using Credit Card: " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using PayPal with email: " + email);
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("Please set a payment strategy before checking out.");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678-9876-5432", "12/24");
        PaymentStrategy payPalPayment = new PayPalPayment("user@example.com");

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setPaymentStrategy(creditCardPayment);
        shoppingCart.checkout(100);

        shoppingCart.setPaymentStrategy(payPalPayment);
        shoppingCart.checkout(50);
    }
}
