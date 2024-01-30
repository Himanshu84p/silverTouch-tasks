import java.util.ArrayList;
import java.util.List;

// Receiver class
class Receiver {
    public void action() {
        System.out.println("Receiver action");
    }
}

// Command interface
interface Command {
    void execute();
}

// ConcreteCommand class
class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand(Receiver rec) {
        this.receiver = rec;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}

// Invoker class
class Invoker {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public void executeAll() {
        for (Command cmd : commands) {
            cmd.execute();
        }
    }
}

public class Prac5 {
    public static void main(String[] args) {
        Receiver rec = new Receiver();
        ConcreteCommand cmd1 = new ConcreteCommand(rec);
        ConcreteCommand cmd2 = new ConcreteCommand(rec);

        Invoker invoker = new Invoker();
        invoker.addCommand(cmd1);
        invoker.addCommand(cmd2);

        invoker.executeAll();
    }
}
