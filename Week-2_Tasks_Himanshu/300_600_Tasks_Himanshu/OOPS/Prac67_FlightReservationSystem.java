import java.util.ArrayList;
import java.util.List;

class Flight {
    private String flightNumber;
    private int totalSeats;
    private int availableSeats;
    private List<Reservation> reservations;

    public Flight(String flightNumber, int totalSeats) {
        this.flightNumber = flightNumber;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.reservations = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean bookSeat(Passenger passenger) {
        
        if (availableSeats > 0) {
            Reservation reservation = new Reservation(passenger, this);
            reservations.add(reservation);
            availableSeats--;
            return true;
        } else {
            System.out.println("Sorry, no available seats for the flight " + flightNumber);
            return false;
        }
    }

    public boolean cancelReservation(Reservation reservation) {
        
        if (reservations.contains(reservation)) {
            reservations.remove(reservation);
            availableSeats++;
            return true;
        } else {
            System.out.println("Reservation not found.");
            return false;
        }
    }

    public void displayFlightDetails() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}

class Passenger {
    private String name;
    private String passportNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Passenger: " + name + " (Passport: " + passportNumber + ")";
    }
}

class Reservation {
    private Passenger passenger;
    private Flight flight;

    public Reservation(Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Reservation for " + passenger.getName() + " on Flight " + flight.getFlightNumber();
    }
}

public class Prac67_FlightReservationSystem {
    public static void main(String[] args) {
        // Create a flight with 10 total seats
        Flight flight1 = new Flight("F123", 10);

        // Create passengers
        Passenger passenger1 = new Passenger("John Doe", "AB123456");
        Passenger passenger2 = new Passenger("Jane Doe", "CD789012");
        
        // Display flight details
        flight1.displayFlightDetails();
        
        flight1.bookSeat(passenger1);
        // Cancel a reservation
        Reservation reservationToCancel = flight1.bookSeat(passenger2) ? flight1.getReservations().get(0) : null;

        // Display flight details
        flight1.displayFlightDetails();
        
        // Cancel a reservation
        if (reservationToCancel != null) {
            flight1.cancelReservation(reservationToCancel);
        }
        
        // Display updated flight details
        flight1.displayFlightDetails();
    }
}
