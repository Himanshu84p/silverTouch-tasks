package ChatApplicationPrac97;

import java.util.ArrayList;
import java.util.List;

// Class representing a User
class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(ChatRoom chatRoom, String content) {
        Message message = new Message(this, content);
        chatRoom.broadcastMessage(message);
    }

    public void receiveMessage(Message message) {
        System.out.println("[" + message.getSender().getUsername() + "]: " + message.getContent());
    }
}

// Class representing a Message
class Message {
    private final User sender;
    private String content;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
}

// Class representing a ChatRoom
class ChatRoom {
    private String name;
    private List<User> participants;
    private List<Message> messageHistory;

    public ChatRoom(String name) {
        this.name = name;
        this.participants = new ArrayList<>();
        this.messageHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addUser(User user) {
        participants.add(user);
    }

    public void broadcastMessage(Message message) {
        messageHistory.add(message);
        for (User participant : participants) {
            participant.receiveMessage(message);
        }
    }

    public List<Message> getMessageHistory() {
        return messageHistory;
    }
}

public class Prac97 {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Charlie");

        // Create a chat room
        ChatRoom chatRoom = new ChatRoom("General Chat");

        // Add users to the chat room
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        // Users send messages
        user1.sendMessage(chatRoom, "Hello, everyone!");
        user2.sendMessage(chatRoom, "Hi, Alice!");
        user3.sendMessage(chatRoom, "Hey, Bob!");

        // View message history in the chat room
        List<Message> history = chatRoom.getMessageHistory();
        System.out.println("\nChat Room History:");
        for (Message message : history) {
            System.out.println("[" + message.getSender().getUsername() + "]: " + message.getContent());
        }
    }
}
