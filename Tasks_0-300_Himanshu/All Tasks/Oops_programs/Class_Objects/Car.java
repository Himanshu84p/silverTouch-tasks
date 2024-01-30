package Oops_programs.Class_Objects;

public class Car {
    public String model;
    public int year;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public void display() {
        System.out.println("Model of car : " + model);
        System.out.println("Year of car : " + year);
    }

    public static void main(String[] args) {
        Car car1 = new Car("I20", 2023);
        car1.display();
    }
}
