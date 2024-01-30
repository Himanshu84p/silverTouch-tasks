
interface Target {
    void request();
}

class Adaptee {
    public void specificRequest() {
        System.out.println("Adaptee's specific request");
    }
}

class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

public class Main {
    public static void main(String[] args) {

        Adaptee adaptee = new Adaptee();

        Target adapter = new Adapter(adaptee);

        adapter.request();
    }
}
