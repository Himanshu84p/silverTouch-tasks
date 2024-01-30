import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class P14 {
class User {
    private String username;
    private String password;
    private List<String> posts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void addPost(String post) {
        posts.add(post);
    }

    @Override
    public String toString() {
        return "Username: " + username;
    }
}


    private Map<String, User> users;
    private List<String> newsFeed;

    public P14() {
        this.users = new HashMap<>();
        this.newsFeed = new ArrayList<>();
    }

    public void createUser(String username, String password) {
        users.put(username, new User(username, password));
        System.out.println("User created successfully.");
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Invalid username or password. Login failed.");
            return null;
        }
    }

    public void postMessage(User user, String message) {
        if (user != null) {
            user.addPost(message);
            newsFeed.add(user.getUsername() + ": " + message);
            System.out.println("Message posted successfully.");
        } else {
            System.out.println("Login required to post a message.");
        }
    }

    public void displayNewsFeed() {
        System.out.println("\nNews Feed:");
        for (String post : newsFeed) {
            System.out.println(post);
        }
    }

    public static void main(String[] args) {
        P14 socialNetwork = new P14();
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            System.out.println("\n1. Create User");
            System.out.println("2. Login");
            System.out.println("3. Post Message");
            System.out.println("4. Display News Feed");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String createUserUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String createUserPassword = scanner.nextLine();
                    socialNetwork.createUser(createUserUsername, createUserPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    currentUser = socialNetwork.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    if (currentUser != null) {
                        System.out.print("Enter your message: ");
                        String message = scanner.nextLine();
                        socialNetwork.postMessage(currentUser, message);
                    } else {
                        System.out.println("Login required to post a message.");
                    }
                    break;
                case 4:
                    socialNetwork.displayNewsFeed();
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
