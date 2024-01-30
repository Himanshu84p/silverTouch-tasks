package MovieTicketBooking_Prac74;
import java.util.ArrayList;
import java.util.List;

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
}

class Ticket {
    private int seatNumber;
    private Movie movie;
    private double price;

    public Ticket(int seatNumber, Movie movie, double price) {
        this.seatNumber = seatNumber;
        this.movie = movie;
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getPrice() {
        return price;
    }
}

class Customer {
    private String customerName;
    private List<Ticket> bookedTickets;

    public Customer(String customerName) {
        this.customerName = customerName;
        this.bookedTickets = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void bookTicket(Ticket ticket) {
        bookedTickets.add(ticket);
    }
}

class Cinema {
    private List<Movie> movies;
    private List<Ticket> availableTickets;

    public Cinema() {
        this.movies = new ArrayList<>();
        this.availableTickets = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addAvailableTicket(Ticket ticket) {
        availableTickets.add(ticket);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Ticket> getAvailableTickets() {
        return availableTickets;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a cinema
        Cinema cinema = new Cinema();

        // Add movies to the cinema
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("Titanic", "Romance", 195);
        cinema.addMovie(movie1);
        cinema.addMovie(movie2);

        // Add available tickets to the cinema
        Ticket ticket1 = new Ticket(1, movie1, 10.0);
        Ticket ticket2 = new Ticket(2, movie1, 10.0);
        Ticket ticket3 = new Ticket(3, movie2, 12.0);
        cinema.addAvailableTicket(ticket1);
        cinema.addAvailableTicket(ticket2);
        cinema.addAvailableTicket(ticket3);

        // Create a customer
        Customer customer = new Customer("John Doe");

        // Customer books tickets
        customer.bookTicket(ticket1);
        customer.bookTicket(ticket3);

        // Display movie information
        System.out.println("Movies:");
        for (Movie movie : cinema.getMovies()) {
            System.out.println("Title: " + movie.getTitle() + ", Genre: " + movie.getGenre() + ", Duration: " + movie.getDurationMinutes() + " minutes");
        }

        // Display available tickets
        System.out.println("\nAvailable Tickets:");
        for (Ticket availableTicket : cinema.getAvailableTickets()) {
            System.out.println("Seat: " + availableTicket.getSeatNumber() + ", Movie: " + availableTicket.getMovie().getTitle() + ", Price: $" + availableTicket.getPrice());
        }

        // Display customer's booked tickets
        System.out.println("\nCustomer's Booked Tickets:");
        for (Ticket bookedTicket : customer.getBookedTickets()) {
            System.out.println("Seat: " + bookedTicket.getSeatNumber() + ", Movie: " + bookedTicket.getMovie().getTitle() + ", Price: $" + bookedTicket.getPrice());
        }
    }
}
