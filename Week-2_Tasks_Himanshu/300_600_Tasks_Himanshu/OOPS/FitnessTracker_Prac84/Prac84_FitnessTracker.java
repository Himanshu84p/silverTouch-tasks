package FitnessTracker_Prac84;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Activity {
    private String name;
    private double duration; // in minutes

    public Activity(String activityName, double activityDuration) {
        this.name = activityName;
        this.duration = activityDuration;
    }

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }
}

class User {
    private String username;
    private List<Activity> activities;

    public User(String name) {
        this.username = name;
        this.activities = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void logActivity(Activity activity) {
        activities.add(activity);
        System.out.println("Activity logged for user " + username + ": " + activity.getName() + " - Duration: "
                + activity.getDuration() + " minutes");
    }

    public void generateActivityReport() {
        System.out.println("Activity Report for user " + username + ":");
        for (Activity activity : activities) {
            System.out.println("- " + activity.getName() + ": " + activity.getDuration() + " minutes");
        }
    }
}

class Tracker {
    private List<User> users;

    public Tracker() {
        this.users = new ArrayList<>();
    }

    public void addUser(String username) {
        users.add(new User(username));
        System.out.println("User added to the tracker: " + username);
    }

    public void logActivity(String username, Activity activity) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        if (userOptional.isPresent()) {
            userOptional.get().logActivity(activity);
        } else {
            System.out.println("User not found in the tracker.");
        }
    }

    public void generateUserReport(String username) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        if (userOptional.isPresent()) {
            userOptional.get().generateActivityReport();
        } else {
            System.out.println("User not found in the tracker.");
        }
    }
}

public class Prac84_FitnessTracker {
    public static void main(String[] args) {
        Tracker fitnessTracker = new Tracker();

        fitnessTracker.addUser("John");
        fitnessTracker.addUser("Alice");

        Activity running = new Activity("Running", 30.0);
        Activity cycling = new Activity("Cycling", 45.0);

        fitnessTracker.logActivity("John", running);
        fitnessTracker.logActivity("Alice", cycling);

        fitnessTracker.generateUserReport("John");
        fitnessTracker.generateUserReport("Alice");
    }
}
