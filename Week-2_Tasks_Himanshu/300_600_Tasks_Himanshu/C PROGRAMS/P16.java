import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Movie {
    private String title;
    private String genre;
    private int durationMinutes;

    public Movie(String title, String genre, int durationMinutes) {
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String toString() {
        return "Movie Title: " + title + "\nGenre: " + genre + "\nDuration: " + durationMinutes + " minutes";
    }
}

class Theater {
    private String name;
    private Map<String, List<Boolean>> showtimes;

    public Theater(String name) {
        this.name = name;
        this.showtimes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, List<Boolean>> getShowtimes() {
        return showtimes;
    }

    public void addShowtime(String movieTitle, int capacity) {
        List<Boolean> seats = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            seats.add(true); // true indicates an available seat
        }
        showtimes.put(movieTitle, seats);
    }

    public void bookSeat(String movieTitle, int seatNumber) {
        List<Boolean> seats = showtimes.get(movieTitle);
        if (seats != null && seatNumber >= 0 && seatNumber < seats.size() && seats.get(seatNumber)) {
            seats.set(seatNumber, false); // false indicates a booked seat
            System.out.println("Seat booked successfully.");
        } else {
            System.out.println("Invalid seat or seat already booked.");
        }
    }
}

class MovieTicketBookingSystem {
    private List<Movie> movies;
    private List<Theater> theaters;

    public MovieTicketBookingSystem() {
        this.movies = new ArrayList<>();
        this.theaters = new ArrayList<>();
    }

    public void addMovie(String title, String genre, int durationMinutes) {
        movies.add(new Movie(title, genre, durationMinutes));
        System.out.println("Movie added successfully.");
    }

    public void addTheater(String name) {
        theaters.add(new Theater(name));
        System.out.println("Theater added successfully.");
    }

    public void addShowtime(String theaterName, String movieTitle, int capacity) {
        Theater theater = findTheaterByName(theaterName);
        if (theater != null) {
            theater.addShowtime(movieTitle, capacity);
            System.out.println("Showtime added successfully.");
        } else {
            System.out.println("Theater not found.");
        }
    }

    public void bookSeat(String theaterName, String movieTitle, int seatNumber) {
        Theater theater = findTheaterByName(theaterName);
        if (theater != null) {
            theater.bookSeat(movieTitle, seatNumber);
        } else {
            System.out.println("Theater not found.");
        }
    }

    public void displayMovies() {
        System.out.println("\nAvailable Movies:");
        for (Movie movie : movies) {
            System.out.println(movie);
            System.out.println("-----------");
        }
    }

    public void displayTheaters() {
        System.out.println("\nAvailable Theaters:");
        for (Theater theater : theaters) {
            System.out.println("Theater Name: " + theater.getName());
            System.out.println("Showtimes:");
            for (Map.Entry<String, List<Boolean>> entry : theater.getShowtimes().entrySet()) {
                System.out.println("  - Movie: " + entry.getKey());
                System.out.println("    Available Seats: " + countAvailableSeats(entry.getValue()));
            }
            System.out.println("-----------");
        }
    }

    private int countAvailableSeats(List<Boolean> seats) {
        int count = 0;
        for (boolean seat : seats) {
            if (seat) {
                count++;
            }
        }
        return count;
    }

    private Theater findTheaterByName(String name) {
        for (Theater theater : theaters) {
            if (theater.getName().equals(name)) {
                return theater;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MovieTicketBookingSystem bookingSystem = new MovieTicketBookingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Movie");
            System.out.println("2. Add Theater");
            System.out.println("3. Add Showtime");
            System.out.println("4. Display Movies");
            System.out.println("5. Display Theaters");
            System.out.println("6. Book Seat");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter movie title: ");
                    String movieTitle = scanner.nextLine();
                    System.out.print("Enter movie genre: ");
                    String movieGenre = scanner.nextLine();
                    System.out.print("Enter movie duration in minutes: ");
                    int movieDuration = scanner.nextInt();
                    bookingSystem.addMovie(movieTitle, movieGenre, movieDuration);
                    break;
                case 2:
                    System.out.print("Enter theater name: ");
                    String theaterName = scanner.nextLine();
                    bookingSystem.addTheater(theaterName);
                    break;
                case 3:
                    System.out.print("Enter theater name: ");
                    String showtimeTheater = scanner.nextLine();
                    System.out.print("Enter movie title: ");
                    String showtimeMovie = scanner.nextLine();
                    System.out.print("Enter capacity for the showtime: ");
                    int showtimeCapacity = scanner.nextInt();
                    bookingSystem.addShowtime(showtimeTheater, showtimeMovie, showtimeCapacity);
                    break;
                case 4:
                    bookingSystem.displayMovies();
                    break;
                case 5:
                    bookingSystem.displayTheaters();
                    break;
                case 6:
                    System.out.print("Enter theater name: ");
                    String bookTheater = scanner.nextLine();
                    System.out.print("Enter movie title: ");
                    String bookMovie = scanner.nextLine();
                    System.out.print("Enter seat number: ");
                    int seatNumber = scanner.nextInt();
                    bookingSystem.bookSeat(bookTheater, bookMovie, seatNumber);
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
