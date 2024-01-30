class Car {
    private String model;
    private Engine engine;

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getModel() {
        return model;
    }
}
