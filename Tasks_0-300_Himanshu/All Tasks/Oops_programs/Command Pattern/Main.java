import java.util.ArrayList;
import java.util.List;

interface Command {
    public void execute();
}

class Light {
    private boolean switchOn;

    public void SwitchLights(boolean adf) {
        this.switchOn = adf;
        if (this.switchOn) {
            System.out.println("Light is ON");
        } else {
            System.out.println("Light is OFF");
        }
    }
}

class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.SwitchLights(true);
    }

}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.SwitchLights(false);
    }
}

class LightCommandExecutioner {
    private final List<Command> lightOperations = new ArrayList<>();

    public void executeOperation(Command cmd) {
        lightOperations.add(cmd);
        cmd.execute();
    }
}

public class Main {
    public static void main(String[] args) {
        LightCommandExecutioner lightExecute = new LightCommandExecutioner();
        System.out.println("Turning On Light by Light On Command.");
        lightExecute.executeOperation(new LightOnCommand(new Light()));
        System.out.println("Turning Off Light by Light Off Command.");
        lightExecute.executeOperation(new LightOffCommand(new Light()));
    }

}