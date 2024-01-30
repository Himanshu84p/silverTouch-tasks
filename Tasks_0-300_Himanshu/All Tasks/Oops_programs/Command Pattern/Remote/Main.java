package Remote;

import java.util.ArrayList;
import java.util.List;

interface Command {
    public void execute();
}

class Remote {
    private boolean switchOn;

    public void SwitchRemote(boolean adf) {
        this.switchOn = adf;
        if (this.switchOn) {
            System.out.println("Remote is ON");
        } else {
            System.out.println("Remote is OFF");
        }
    }
}

class RemoteOnCommand implements Command {

    private Remote Remote;

    public RemoteOnCommand(Remote Remote) {
        this.Remote = Remote;
    }

    @Override
    public void execute() {
        Remote.SwitchRemote(true);
    }

}

class RemoteOffCommand implements Command {
    private Remote Remote;

    public RemoteOffCommand(Remote Remote) {
        this.Remote = Remote;
    }

    @Override
    public void execute() {
        Remote.SwitchRemote(false);
    }
}

class RemoteCommandExecutioner {
    private final List<Command> RemoteOperations = new ArrayList<>();

    public void executeOperation(Command cmd) {
        RemoteOperations.add(cmd);
        cmd.execute();
    }
}

public class Main {
    public static void main(String[] args) {
        RemoteCommandExecutioner RemoteExecute = new RemoteCommandExecutioner();
        System.out.println("Turning On Remote by Remote On Command.");
        RemoteExecute.executeOperation(new RemoteOnCommand(new Remote()));
        System.out.println("Turning Off Remote by Remote Off Command.");
        RemoteExecute.executeOperation(new RemoteOffCommand(new Remote()));
    }

}
