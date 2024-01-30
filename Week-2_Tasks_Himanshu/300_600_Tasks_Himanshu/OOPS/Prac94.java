import java.util.ArrayList;
import java.util.List;

class Destination {
    private String name;
    private String description;

    public Destination(String destName, String destDescription) {
        this.name = destName;
        this.description = destDescription;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

class Itinerary {
    private List<Destination> destinations;

    public Itinerary() {
        this.destinations = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public void generateItinerary() {
        System.out.println("Travel Itinerary:");
        System.out.println("-------------------");
        for (Destination destination : destinations) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Description: " + destination.getDescription());
            System.out.println("-------------------");
        }
    }
}

class Traveler {
    private String name;
    private Itinerary itinerary;

    public Traveler(String travelerName) {
        this.name = travelerName;
        this.itinerary = new Itinerary();
    }

    public String getName() {
        return name;
    }

    public void planTrip(List<Destination> destinations) {
        for (Destination destination : destinations) {
            itinerary.addDestination(destination);
        }
    }

    public void viewItinerary() {
        System.out.println("Itinerary for " + name + ":");
        System.out.println("-------------------");
        itinerary.generateItinerary();
    }
}

public class Prac94 {
    public static void main(String[] args) {
        Destination dest1 = new Destination("Paris", "City of lights and romance");
        Destination dest2 = new Destination("Tokyo", "Vibrant and modern metropolis");
        Destination dest3 = new Destination("New York", "The city that never sleeps");

        List<Destination> tripDestinations = new ArrayList<>();
        tripDestinations.add(dest1);
        tripDestinations.add(dest2);
        tripDestinations.add(dest3);

        Traveler traveler = new Traveler("Alice");
        traveler.planTrip(tripDestinations);
        traveler.viewItinerary();
    }
}
