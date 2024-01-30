package HotelReservation_Prac79;

import java.util.ArrayList;
import java.util.List;

class Room {
    private int roomNumber;
    private boolean isOccupied;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void vacate() {
        isOccupied = false;
    }
}

class Guest {
    private String guestName;
    private String contactNumber;

    public Guest(String guestName, String contactNumber) {
        this.guestName = guestName;
        this.contactNumber = contactNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}

class Reservation {
    private Room room;
    private Guest guest;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(Room room, Guest guest, String checkInDate, String checkOutDate) {
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel(int numRooms) {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();

        // Initialize rooms
        for (int i = 1; i <= numRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public boolean bookRoom(Guest guest, String checkInDate, String checkOutDate) {
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                room.occupy();
                Reservation reservation = new Reservation(room, guest, checkInDate, checkOutDate);
                reservations.add(reservation);
                return true; // Booking successful
            }
        }
        return false; // No available rooms
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}

public class Prac79_HotelReservationSystem {
    public static void main(String[] args) {
        // Create a hotel with 10 rooms
        Hotel hotel = new Hotel(10);

        // Create a guest
        Guest guest = new Guest("John Doe", "123-456-7890");

        // Check available rooms
        List<Room> availableRoomsBeforeBooking = hotel.getAvailableRooms();
        System.out.println("Available Rooms Before Booking: " + availableRoomsBeforeBooking.size());

        // Book a room
        boolean bookingResult = hotel.bookRoom(guest, "2022-01-20", "2022-01-25");
        if (bookingResult) {
            System.out.println("Room booked successfully!");
        } else {
            System.out.println("No available rooms.");
        }

        // Check available rooms after booking
        List<Room> availableRoomsAfterBooking = hotel.getAvailableRooms();
        System.out.println("Available Rooms After Booking: " + availableRoomsAfterBooking.size());
    }
}
