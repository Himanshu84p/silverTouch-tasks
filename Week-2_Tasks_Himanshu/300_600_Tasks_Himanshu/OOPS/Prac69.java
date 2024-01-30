import java.util.ArrayList;
import java.util.List;

class Event {
    private String name;
    private List<Attendee> attendees;
    private Organizer organizer;

    public Event(String name, Organizer organizer) {
        this.name = name;
        this.attendees = new ArrayList<>();
        this.organizer = organizer;
    }

    public void registerAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public void unregisterAttendee(Attendee attendee) {
        attendees.remove(attendee);
    }

    public void sendNotification(String message) {
        for (Attendee attendee : attendees) {
            attendee.receiveNotification(message);
        }
    }
}

class Attendee {
    private String name;

    public Attendee(String name) {
        this.name = name;
    }

    public void receiveNotification(String message) {
        System.out.println(name + " received a notification: " + message);
    }
}

class Organizer {
    private String name;

    public Organizer(String name) {
        this.name = name;
    }
}

public class Prac69 {
    public static void main(String[] args) {
        Organizer organizer = new Organizer("John");
        Event event = new Event("Conference", organizer);

        Attendee attendee1 = new Attendee("Alice");
        Attendee attendee2 = new Attendee("Bob");

        event.registerAttendee(attendee1);
        event.registerAttendee(attendee2);

        event.sendNotification("The event will start soon!");

        event.unregisterAttendee(attendee1);

        event.sendNotification("The event has been canceled.");

    }
}