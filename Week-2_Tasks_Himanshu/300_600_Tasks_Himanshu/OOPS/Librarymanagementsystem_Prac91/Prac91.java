package Librarymanagementsystem_Prac91;

import java.util.ArrayList;
import java.util.List;

class Author {
    private String name;

    public Author(String authorName) {
        name = authorName;
    }

    public String getName() {
        return name;
    }
}

class Book {
    private String title;
    private Author author;
    private boolean isAvailable;
    private List<Member> borrowers;

    public Book(String bookTitle, Author bookAuthor) {
        title = bookTitle;
        author = bookAuthor;
        isAvailable = true;
        borrowers = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void borrowBook(Member member) {
        if (isAvailable) {
            isAvailable = false;
            borrowers.add(member);
            member.borrowBook(this);
            System.out.println("Book \"" + title + "\" by " + author.getName() + " has been borrowed.");
        } else {
            System.out.println("Book \"" + title + "\" is not available for borrowing.");
        }
    }

    public void returnBook(Member member) {
        if (borrowers.contains(member)) {
            isAvailable = true;
            borrowers.remove(member);
            member.returnBook(this);
            System.out.println("Book \"" + title + "\" by " + author.getName() + " has been returned.");
        } else {
            System.out.println("You did not borrow book \"" + title + "\".");
        }
    }
}

class Member {
    private String name;
    private List<Book> borrowedBooks;

    public Member(String memberName) {
        name = memberName;
        borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrowBook(this);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook(this);
    }

    public void displayBorrowedBooks() {
        System.out.println("Borrowed Books by " + name + ":\n");
        System.out.println("------------------------");
        for (Book book : borrowedBooks) {
            System.out.println("Title: " + book.getTitle() + " by " + book.getAuthor().getName());
        }
        System.out.println("------------------------\n");
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(String title, String authorName) {
        Author author = new Author(authorName);
        Book book = new Book(title, author);
        books.add(book);
    }

    public void addMember(String memberName) {
        Member member = new Member(memberName);
        members.add(member);
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Member findMember(String memberName) {
        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        return null;
    }

    public void displayBooks() {
        System.out.println("Library Books:\n");
        System.out.println("------------------------");
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + " by " + book.getAuthor().getName() + " - "
                    + (book.getIsAvailable() ? "Available" : "Not Available"));
        }
        System.out.println("------------------------\n");
    }
}

public class Prac91 {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        library.addBook("To Kill a Mockingbird", "Harper Lee");
        library.addBook("1984", "George Orwell");

        library.addMember("Alice");
        library.addMember("Bob");

        library.displayBooks();

        Member alice = library.findMember("Alice");
        Member bob = library.findMember("Bob");

        Book gatsby = library.findBook("The Great Gatsby");
        Book mockingbird = library.findBook("To Kill a Mockingbird");
        Book nineteenEightyFour = library.findBook("1984");

        if (alice != null && bob != null && gatsby != null && mockingbird != null && nineteenEightyFour != null) {
            alice.borrowBook(gatsby);
            bob.borrowBook(mockingbird);

            alice.displayBorrowedBooks();
            bob.displayBorrowedBooks();

            alice.returnBook(gatsby);
            bob.returnBook(mockingbird);

            alice.displayBorrowedBooks();
            bob.displayBorrowedBooks();
        }
    }
}