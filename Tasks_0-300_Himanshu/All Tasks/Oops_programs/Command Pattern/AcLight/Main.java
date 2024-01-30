package AcLight;

import java.util.ArrayList;
import java.util.List;

interface Command {
    public void execute();
}

class Light {
    public void turnOn() {
        System.out.println("Light is turn On");
    }

    public void turnOff() {
        System.out.println("Light is turn Off");
    }

}

class AC {
    public void turnOn() {
        System.out.println("AC is turn On");
    }

    public void turnOff() {
        System.out.println("AC is turn Off");
    }
}

class TurnOnLightsCommand implements Command {
    private Light light;

    TurnOnLightsCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

}

class TurnOffACCommand implements Command {
    private AC ac;

    TurnOffACCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOff();
    }
}

class RemoteCommandExecutioner {
    private final List<Command> remoteOperations = new ArrayList<>();

    public void CommandExecutioner(Command cmd) {
        remoteOperations.add(cmd);
        cmd.execute();
    }

}

public class Main {
    public static void main(String[] args) {
        RemoteCommandExecutioner cmdExecute = new RemoteCommandExecutioner();

        System.out.println("Turning On Light by Light On Command.");
        cmdExecute.CommandExecutioner(new TurnOnLightsCommand(new Light()));
        System.out.println("Turning Off Ac by Ac Off Command.");
        cmdExecute.CommandExecutioner(new TurnOffACCommand(new AC()));
    }
}
