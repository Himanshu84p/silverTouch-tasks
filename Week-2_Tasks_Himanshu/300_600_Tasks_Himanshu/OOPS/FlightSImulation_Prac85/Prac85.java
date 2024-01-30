package FlightSImulation_Prac85;

import java.util.ArrayList;
import java.util.List;

// Class representing an Aircraft
class Aircraft {
    private String model;
    private int capacity;

    public Aircraft(String aircraftModel, int aircraftCapacity) {
        this.model = aircraftModel;
        this.capacity = aircraftCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void simulateMovement() {
        System.out.println("Aircraft " + model + " is moving.");
    }
}

// Class representing an Airport
class Airport {
    private String code;
    private List<Aircraft> availableAircraft;

    public Airport(String airportCode) {
        this.code = airportCode;
        this.availableAircraft = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void addAircraft(Aircraft aircraft) {
        availableAircraft.add(aircraft);
        System.out.println("Aircraft " + aircraft.getModel() + " added to airport " + code + ".");
    }

    public void listAvailableAircraft() {
        System.out.println("Available Aircraft at Airport " + code + ":");
        for (Aircraft aircraft : availableAircraft) {
            System.out.println("- Model: " + aircraft.getModel() + ", Capacity: " + aircraft.getCapacity());
        }
    }
}

// Class representing a Flight
class Flight {
    private String flightNumber;
    private String origin;
    private String destination;

    public Flight(String number, String originAirport, String destinationAirport) {
        this.flightNumber = number;
        this.origin = originAirport;
        this.destination = destinationAirport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public void scheduleFlight() {
        System.out.println("Flight " + flightNumber + " scheduled from " + origin + " to " + destination + ".");
    }
}

public class Prac85 {
    public static void main(String[] args) {
        Airport heathrow = new Airport("LHR");
        Airport jfk = new Airport("JFK");

        Aircraft boeing747 = new Aircraft("Boeing 747", 400);
        Aircraft airbusA380 = new Aircraft("Airbus A380", 550);

        heathrow.addAircraft(boeing747);
        heathrow.addAircraft(airbusA380);

        heathrow.listAvailableAircraft();

        Flight flight1 = new Flight("BA123", "LHR", "JFK");
        Flight flight2 = new Flight("AA456", "JFK", "LHR");

        flight1.scheduleFlight();
        flight2.scheduleFlight();
    }
}
