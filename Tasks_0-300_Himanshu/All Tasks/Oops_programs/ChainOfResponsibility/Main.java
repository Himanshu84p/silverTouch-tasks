interface PurchaseHandler {
    void setNextHandler(PurchaseHandler nextHandler);

    void processRequest(PurchaseRequest request);
}

class Manager implements PurchaseHandler {
    private PurchaseHandler nextHandler;

    @Override
    public void setNextHandler(PurchaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= 1000) {
            System.out.println("Manager approves purchase request for " + request.getAmount());
        } else if (nextHandler != null) {
            nextHandler.processRequest(request);
        } else {
            System.out.println("No handler can process the request.");
        }
    }
}

class Director implements PurchaseHandler {
    private PurchaseHandler nextHandler;

    @Override
    public void setNextHandler(PurchaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= 5000) {
            System.out.println("Director approves purchase request for " + request.getAmount());
        } else if (nextHandler != null) {
            nextHandler.processRequest(request);
        } else {
            System.out.println("No handler can process the request.");
        }
    }
}

class CEO implements PurchaseHandler {
    @Override
    public void setNextHandler(PurchaseHandler nextHandler) {

    }

    @Override
    public void processRequest(PurchaseRequest request) {
        System.out.println("CEO approves purchase request for " + request.getAmount());
    }
}

class PurchaseRequest {
    private double amount;

    public PurchaseRequest(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

public class Main {
    public static void main(String[] args) {

        PurchaseHandler manager = new Manager();
        PurchaseHandler director = new Director();
        PurchaseHandler ceo = new CEO();

        manager.setNextHandler(director);
        director.setNextHandler(ceo);

        PurchaseRequest request = new PurchaseRequest(4500);

        manager.processRequest(request);
    }
}
