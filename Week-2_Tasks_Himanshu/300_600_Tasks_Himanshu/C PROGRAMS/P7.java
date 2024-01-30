import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}

class BlogPost {
    private String title;
    private String content;

    public BlogPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

class BloggingPlatform {
    private List<User> users;
    private List<BlogPost> blogPosts;

    public BloggingPlatform() {
        this.users = new ArrayList<>();
        this.blogPosts = new ArrayList<>();
    }

    public void addUser(String username, String password) {
        users.add(new User(username, password));
        System.out.println("User registered successfully.");
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("Login successful.");
                return user;
            }
        }
        System.out.println("Invalid username or password. Login failed.");
        return null;
    }

    public void createBlogPost(User user, String title, String content) {
        if (user != null) {
            BlogPost post = new BlogPost(title, content);
            blogPosts.add(post);
            System.out.println("Blog post created successfully.");
        } else {
            System.out.println("Login required to create a blog post.");
        }
    }

    public void displayAllBlogPosts() {
        System.out.println("\nAll Blog Posts:");
        for (BlogPost post : blogPosts) {
            System.out.println("Title: " + post.getTitle());
            System.out.println("Content: " + post.getContent());
            System.out.println("-----------");
        }
    }
}

public class P7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BloggingPlatform bloggingPlatform = new BloggingPlatform();
        User currentUser = null;

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create Blog Post");
            System.out.println("4. Display All Blog Posts");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    bloggingPlatform.addUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    currentUser = bloggingPlatform.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    if (currentUser != null) {
                        System.out.print("Enter blog post title: ");
                        String postTitle = scanner.nextLine();
                        System.out.print("Enter blog post content: ");
                        String postContent = scanner.nextLine();
                        bloggingPlatform.createBlogPost(currentUser, postTitle, postContent);
                    } else {
                        System.out.println("Login required to create a blog post.");
                    }
                    break;
                case 4:
                    bloggingPlatform.displayAllBlogPosts();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
