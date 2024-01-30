import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class ParkingLot {
    private int capacity;
    private Map<Ticket, Vehicle> parkedVehicles;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedVehicles = new HashMap<>();
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        if (parkedVehicles.size() >= capacity) {
            throw new RuntimeException("Parking lot is full");
        }

        Ticket ticket = new Ticket(vehicle, LocalDateTime.now());
        parkedVehicles.put(ticket, vehicle);
        return ticket;
    }

    public Vehicle retrieveVehicle(Ticket ticket) {
        if (!parkedVehicles.containsKey(ticket)) {
            throw new RuntimeException("Invalid ticket");
        }

        Vehicle vehicle = parkedVehicles.remove(ticket);
        return vehicle;
    }

    public double calculateParkingFee(Ticket ticket) {
        if (!parkedVehicles.containsKey(ticket)) {
            throw new RuntimeException("Invalid ticket");
        }

        Vehicle vehicle = parkedVehicles.get(ticket);
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = ticket.getStartTime();
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.toSeconds();
        double hours = (double) seconds / 3600;
        double fee = vehicle.getRate() * hours;
        return fee;
    }
}

class Vehicle {
    private String licensePlate;
    private double rate;

    public Vehicle(String licensePlate, double rate) {
        this.licensePlate = licensePlate;
        this.rate = rate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getRate() {
        return rate;
    }
}

class Ticket {
    private Vehicle vehicle;
    private LocalDateTime startTime;

    public Ticket(Vehicle vehicle, LocalDateTime startTime) {
        this.vehicle = vehicle;
        this.startTime = startTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}

public class Prac70 {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(10);

        Vehicle car = new Vehicle("ABC123", 2.5);
        Ticket carTicket = parkingLot.parkVehicle(car);
        System.out.println("Car parked. License plate: " + carTicket.getVehicle().getLicensePlate());

        Vehicle motorcycle = new Vehicle("XYZ789", 1.5);
        Ticket motorcycleTicket = parkingLot.parkVehicle(motorcycle);
        System.out.println("Motorcycle parked. License plate: " + motorcycleTicket.getVehicle().getLicensePlate());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double carParkingFee = parkingLot.calculateParkingFee(carTicket);
        System.out.println("Car parking fee: $" + carParkingFee);

        double motorcycleParkingFee = parkingLot.calculateParkingFee(motorcycleTicket);
        System.out.println("Motorcycle parking fee: $" + motorcycleParkingFee);
        
        Vehicle retrievedVehicle = parkingLot.retrieveVehicle(carTicket);
        System.out.println("Car retrieved. License plate: " + retrievedVehicle.getLicensePlate());
    }
}