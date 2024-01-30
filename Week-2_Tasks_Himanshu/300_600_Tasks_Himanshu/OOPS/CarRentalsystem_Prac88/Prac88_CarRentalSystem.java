package CarRentalsystem_Prac88;

import java.util.ArrayList;
import java.util.List;

class Car {
    public String brand;
    public String model;
    public String licensePlate;
    public boolean available;

    public Car(String brand, String model, String licensePlate) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.available = true;
    }

    public void rent() {
        if (available) {
            available = false;
            System.out.println("Car rented: " + brand + " " + model + " (" + licensePlate + ")");
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar() {
        available = true;
        System.out.println("Car returned: " + brand + " " + model + " (" + licensePlate + ")");
    }
}

class RentalAgency {
    public String name;
    public List<Car> cars;

    public RentalAgency(String name) {
        this.name = name;
        this.cars = new ArrayList<>();
    }

    public void displayAvailableCars() {
        System.out.println("Available cars at " + name + " rental agency:");
        for (Car car : cars) {
            if (car.available) {
                System.out.println(car.brand + " " + car.model + " (" + car.licensePlate + ")");
            }
        }
    }

    public void rentCar(Customer customer, String licensePlate) {
        for (Car car : cars) {
            if (car.licensePlate.equals(licensePlate)) {
                customer.rentCar(car);
                return;
            }
        }
        System.out.println("Car with license plate " + licensePlate + " not found.");
    }

    public void returnCar(Customer customer, String licensePlate) {
        for (Car car : cars) {
            if (car.licensePlate.equals(licensePlate)) {
                customer.returnCar(car);
                return;
            }
        }
        System.out.println("Car with license plate " + licensePlate + " not found.");
    }
}

class Customer {
    public String name;
    public List<Car> rentedCars;

    public Customer(String name) {
        this.name = name;
        this.rentedCars = new ArrayList<>();
    }

    public void rentCar(Car car) {
        car.rent();
        rentedCars.add(car);
    }

    public void returnCar(Car car) {
        car.returnCar();
        rentedCars.remove(car);
    }

    public void displayRentalHistory() {
        System.out.println("Rental history for customer " + name + ":");
        for (Car car : rentedCars) {
            System.out.println(car.brand + " " + car.model + " (" + car.licensePlate + ")");
        }
    }
}

public class Prac88_CarRentalSystem {
    public static void main(String[] args) {
        // Create cars
        Car car1 = new Car("Toyota", "Camry", "ABC123");
        Car car2 = new Car("Honda", "Accord", "XYZ789");

        // Create rental agency
        RentalAgency rentalAgency = new RentalAgency("BestRentals");
        rentalAgency.cars = List.of(car1, car2);

        // Create customer
        Customer customer = new Customer("John");

        // Display available cars
        rentalAgency.displayAvailableCars();

        // Rent a car
        rentalAgency.rentCar(customer, "ABC123");

        // Display rental history
        customer.displayRentalHistory();

        // Return the rented car
        rentalAgency.returnCar(customer, "ABC123");

        // Display available cars after return
        rentalAgency.displayAvailableCars();
    }
}
