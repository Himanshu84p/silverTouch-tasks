import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Room {
    private int roomNumber;
    private boolean isOccupied;

    public Room(int number) {
        roomNumber = number;
        isOccupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void occupyRoom() {
        isOccupied = true;
        System.out.println("Room " + roomNumber + " has been occupied.");
    }

    public void vacateRoom() {
        isOccupied = false;
        System.out.println("Room " + roomNumber + " has been vacated.");
    }
}

class Guest {
    private String name;

    public Guest(String guestName) {
        name = guestName;
    }

    public String getName() {
        return name;
    }
}

class Booking {
    private Room room;
    private Guest guest;

    public Booking(Room bookedRoom, Guest bookingGuest) {
        room = bookedRoom;
        guest = bookingGuest;
    }

    public Guest getGuest() {
        return guest;
    }

    public void checkIn() {
        if (!room.getIsOccupied()) {
            room.occupyRoom();
            System.out.println(guest.getName() + " has checked in to Room " + room.getRoomNumber() + ".");
        } else {
            System.out.println("Room " + room.getRoomNumber() + " is already occupied.");
        }
    }

    public void checkOut() {
        if (room.getIsOccupied()) {
            room.vacateRoom();
            System.out.println(guest.getName() + " has checked out from Room " + room.getRoomNumber() + ".");
        } else {
            System.out.println("Room " + room.getRoomNumber() + " is not occupied.");
        }
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel(int numRooms) {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();

        for (int i = 1; i <= numRooms; ++i) {
            rooms.add(new Room(i));
        }
    }

    public Optional<Room> findAvailableRoom() {
        return rooms.stream()
                .filter(room -> !room.getIsOccupied())
                .findFirst();
    }

    public void bookRoom(Guest guest) {
        Optional<Room> availableRoom = findAvailableRoom();
        if (availableRoom.isPresent()) {
            bookings.add(new Booking(availableRoom.get(), guest));
            System.out.println(guest.getName() + " has booked Room " + availableRoom.get().getRoomNumber() + ".");
        } else {
            System.out.println("No available rooms for booking.");
        }
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public void displayRoomStatus() {
        System.out.println("Room Status:");
        System.out.println("------------------------");
        for (Room room : rooms) {
            System.out
                    .println("Room " + room.getRoomNumber() + ": " + (room.getIsOccupied() ? "Occupied" : "Available"));
        }
        System.out.println("------------------------");
    }
}

public class Prac81_HotelReservationSystem {

    public static void main(String[] args) {
        Hotel hotel = new Hotel(10); // Create a hotel with 10 rooms

        Guest alice = new Guest("Alice");
        Guest bob = new Guest("Bob");

        hotel.displayRoomStatus();

        hotel.bookRoom(alice);
        hotel.bookRoom(bob);

        hotel.displayRoomStatus();

        Optional<Room> availableRoom = hotel.findAvailableRoom();
        availableRoom.ifPresent(room -> {
            // Create a booking for Alice in the available room
            Booking aliceBooking = new Booking(room, alice);
            aliceBooking.checkIn();
            hotel.displayRoomStatus();
        });

        availableRoom = hotel.findAvailableRoom();
        availableRoom.ifPresent(room -> {
            // Create a booking for Bob in another available room
            Booking bobBooking = new Booking(room, bob);
            bobBooking.checkIn();
            hotel.displayRoomStatus();
        });

        List<Booking> hotelBookings = hotel.getBookings();
        Booking aliceBooking = hotelBookings.stream()
                .filter(booking -> booking.getGuest().getName().equals("Alice"))
                .findFirst()
                .orElse(null);

        if (aliceBooking != null) {
            aliceBooking.checkOut();
            hotel.displayRoomStatus();
        }

        Booking bobBooking = hotelBookings.stream()
                .filter(booking -> booking.getGuest().getName().equals("Bob"))
                .findFirst()
                .orElse(null);

        if (bobBooking != null) {
            bobBooking.checkOut();
            hotel.displayRoomStatus();
        }

    }
}
