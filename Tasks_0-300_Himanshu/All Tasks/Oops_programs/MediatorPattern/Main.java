
interface Mediator {
    void sendMessage(String message, Colleague colleague);
}

class Colleague {
    private Mediator mediator;
    private String name;

    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public void sendMessage(String message) {
        System.out.println(name + " sends message: " + message);
        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println(name + " receives message: " + message);
    }
}

class ConcreteMediator implements Mediator {
    private Colleague colleague1;
    private Colleague colleague2;

    public void setColleague1(Colleague colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(Colleague colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    public void sendMessage(String message, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.receiveMessage(message);
        } else if (colleague == colleague2) {
            colleague1.receiveMessage(message);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        ConcreteMediator mediator = new ConcreteMediator();

        Colleague colleague1 = new Colleague(mediator, "Colleague 1");
        Colleague colleague2 = new Colleague(mediator, "Colleague 2");
        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.sendMessage("Hello from Colleague 1");

        colleague2.sendMessage("Hi from Colleague 2");
    }
}
