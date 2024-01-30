import java.util.ArrayList;
import java.util.List;

class User {
    private String username;
    private List<Post> posts;
    private List<User> friends;

    public User(String username) {
        this.username = username;
        this.posts = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<User> getFriends() {
        return new ArrayList<>(friends);
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void displayTimeline() {
        System.out.println("Timeline for " + username + ":");
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}

class Post {
    private User author;
    private String content;

    public Post(User author, String content) {
        this.author = author;
        this.content = content;
    }

    @Override
    public String toString() {
        return author.getUsername() + ": " + content;
    }
}

class Friendship {
    public static void establishFriendship(User user1, User user2) {
        user1.addFriend(user2);
        user2.addFriend(user1);
    }
}

public class Prac64_SocialMediaNetwork {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Charlie");

        // Establish friendships
        Friendship.establishFriendship(user1, user2);
        Friendship.establishFriendship(user1, user3);

        // User posts messages
        user1.addPost(new Post(user1, "Hello, friends!"));
        user2.addPost(new Post(user2, "Nice to meet you, Alice!"));
        user3.addPost(new Post(user3, "Greetings, everyone!"));

        // Display timelines
        user1.displayTimeline();
        user2.displayTimeline();
        user3.displayTimeline();
    }
}
