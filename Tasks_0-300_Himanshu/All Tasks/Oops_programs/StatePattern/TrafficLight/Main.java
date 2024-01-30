package TrafficLight;

interface TrafficLight {
    public void change();
}

class Context {
    public TrafficLight trafficlight;

    public void setState(TrafficLight trafficlight) {
        this.trafficlight = trafficlight;
    }

    public void change() {
        trafficlight.change();
    }
}

class Red implements TrafficLight {
    @Override
    public void change() {
        System.out.println("Signal is change to RED");
        System.out.println("Stops Your Vehicle");
        System.out.println(" ");
    }
}

class Yellow implements TrafficLight {
    @Override
    public void change() {
        System.out.println("Signal is change to YELLOW");
        System.out.println("Start Your Vehicle");
        System.out.println(" ");
    }
}

class Green implements TrafficLight {
    @Override
    public void change() {
        System.out.println("Signal is change to GREEN");
        System.out.println("You can Go!!");
        System.out.println(" ");
    }
}

public class Main {

    public static void main(String[] args) {
        Context context = new Context();

        context.setState(new Red());
        context.change();

        context.setState(new Yellow());
        context.change();

        context.setState(new Green());
        context.change();
    }
}
