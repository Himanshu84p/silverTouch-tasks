public class Main {
    public static void main(String[] args) {
        Engine carEngine = new Engine("V5");

        Car car1 = new Car("XUV", carEngine);

        System.out.println("car model : " + car1.getModel());
        System.out.println("car engine : " + car1.getEngine().getType());
    }
}
