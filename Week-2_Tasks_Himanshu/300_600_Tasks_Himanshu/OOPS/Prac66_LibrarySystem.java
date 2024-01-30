import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Book {
    private String title;
    private Author author;
    private boolean isCheckedOut;
    private Date dueDate;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
        this.isCheckedOut = false;
        this.dueDate = null;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void checkOut() {
        if (!isCheckedOut) {
            isCheckedOut = true;
            // Set a due date, e.g., two weeks from the current date
            long currentTimeMillis = System.currentTimeMillis();
            long twoWeeksInMillis = 2 * 7 * 24 * 60 * 60 * 1000;
            dueDate = new Date(currentTimeMillis + twoWeeksInMillis);
            System.out.println("Book checked out successfully. Due date: " + dueDate);
        } else {
            System.out.println("Book is already checked out.");
        }
    }

    public void returnBook() {
        if (isCheckedOut) {
            isCheckedOut = false;
            dueDate = null;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book is not checked out.");
        }
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchByAuthor(String authorName) {
        for (Book book : books) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                return book;
            }
        }
        return null; // Book not found for the given author
    }

    public void displayAllBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor().getName());
        }
    }
}

public class Prac66_LibrarySystem {
    public static void main(String[] args) {
        Author author1 = new Author("John Doe");
        Author author2 = new Author("Jane Smith");

        Book book1 = new Book("Book1", author1);
        Book book2 = new Book("Book2", author1);
        Book book3 = new Book("Book3", author2);

        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.displayAllBooks();

        // Checking out a book
        book1.checkOut();

        // Searching for books by author
        Book foundBook = library.searchByAuthor("John Doe");
        if (foundBook != null) {
            System.out.println("Book found: " + foundBook.getTitle());
        } else {
            System.out.println("No books found for the given author.");
        }

        // Returning a book
        book1.returnBook();
    }
}
