
// Existing class with an incompatible interface
class Adaptee {
    void specificRequest() {
        System.out.println("Existing class method called");
    }
}

// Target interface expected by the client
interface Target {
    void request();
}

// Adapter class that adapts the existing class to the target interface
class Adapter implements Target {
    private Adaptee adaptee;

    Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

// Client code using the Target interface
public class Prac14 {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target adapter = new Adapter(adaptee);

        // Client interacts with the Target interface
        adapter.request();
    }
}