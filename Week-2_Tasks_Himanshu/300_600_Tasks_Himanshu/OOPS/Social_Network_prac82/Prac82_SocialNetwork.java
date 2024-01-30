package Social_Network_prac82;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Post {
    private String content;
    private int likes;

    public Post(String postContent) {
        this.content = postContent;
        this.likes = 0;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public void like() {
        likes++;
    }
}

class User {
    private String username;
    private List<Post> posts;

    public User(String name) {
        this.username = name;
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void createPost(String content) {
        posts.add(new Post(content));
        System.out.println("User " + username + " created a post: " + content);
    }

    public void likePost(Post post) {
        post.like();
        System.out.println("User " + username + " liked a post: " + post.getContent());
    }
}

class Network {
    private List<User> users;

    public Network() {
        this.users = new ArrayList<>();
    }

    public void addUser(String username) {
        users.add(new User(username));
        System.out.println("User " + username + " has joined the network.");
    }

    public void connectUsers(String username1, String username2) {
        Optional<User> user1 = findUser(username1);
        Optional<User> user2 = findUser(username2);

        if (user1.isPresent() && user2.isPresent()) {
            System.out.println("Users " + username1 + " and " + username2 + " are now connected.");
        } else {
            System.out.println("One or both users not found.");
        }
    }

    public Optional<User> findUser(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public void displayInfluentialUsers() {
        System.out.println("Influential Users:");
        System.out.println("------------------------");
        for (User user : users) {
            int totalLikes = user.getPosts().stream()
                    .mapToInt(Post::getLikes)
                    .sum();
            System.out.println("User " + user.getUsername() + " - Total Likes: " + totalLikes);
        }
        System.out.println("------------------------");
    }

    public void displayPopularPosts() {
        System.out.println("Popular Posts:");
        System.out.println("------------------------");
        for (User user : users) {
            for (Post post : user.getPosts()) {
                System.out.println("Post by User " + user.getUsername() + ": " + post.getContent() +
                        " - Likes: " + post.getLikes());
            }
        }
        System.out.println("------------------------");
    }
}

public class Prac82_SocialNetwork {
    public static void main(String[] args) {
        Network socialNetwork = new Network();

        socialNetwork.addUser("Alice");
        socialNetwork.addUser("Bob");
        socialNetwork.addUser("Charlie");

        socialNetwork.connectUsers("Alice", "Bob");
        socialNetwork.connectUsers("Bob", "Charlie");

        Optional<User> alice = socialNetwork.findUser("Alice");
        Optional<User> bob = socialNetwork.findUser("Bob");
        Optional<User> charlie = socialNetwork.findUser("Charlie");

        alice.ifPresent(user -> user.createPost("Having a great day!"));
        bob.ifPresent(user -> user.createPost("Just finished reading a good book."));
        charlie.ifPresent(user -> user.createPost("Excited about the new project!"));

        alice.ifPresent(user -> bob.ifPresent(b -> user.likePost(b.getPosts().get(0))));
        bob.ifPresent(user -> charlie.ifPresent(c -> user.likePost(c.getPosts().get(0))));

        socialNetwork.displayInfluentialUsers();
        socialNetwork.displayPopularPosts();
    }
}
