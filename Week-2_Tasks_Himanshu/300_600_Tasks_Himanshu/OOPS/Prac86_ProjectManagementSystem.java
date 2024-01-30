import java.util.ArrayList;
import java.util.List;

class Task {
    private String name;
    private String description;
    private boolean completed;

    public Task(String taskName, String taskDescription) {
        this.name = taskName;
        this.description = taskDescription;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
        System.out.println("Task '" + name + "' marked as completed.");
    }
}

class Project {
    private String name;
    private List<Task> tasks;

    public Project(String projectName) {
        this.name = projectName;
        this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task '" + task.getName() + "' added to project '" + name + "'.");
    }

    public void listTasks() {
        System.out.println("Tasks for project '" + name + "':");
        for (Task task : tasks) {
            System.out.println("- " + task.getName() + " (" + (task.isCompleted() ? "Completed" : "Incomplete") + ")");
        }
    }
}

class TeamMember {
    private String name;
    private List<Task> assignedTasks;

    public TeamMember(String memberName) {
        this.name = memberName;
        this.assignedTasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void assignTask(Task task) {
        assignedTasks.add(task);
        System.out.println("Task '" + task.getName() + "' assigned to team member '" + name + "'.");
    }

    public void listAssignedTasks() {
        System.out.println("Tasks assigned to team member '" + name + "':");
        for (Task task : assignedTasks) {
            System.out.println("- " + task.getName() + " (" + (task.isCompleted() ? "Completed" : "Incomplete") + ")");
        }
    }
}

public class Prac86_ProjectManagementSystem {
    public static void main(String[] args) {
        Project project = new Project("Software Development");
        Task task1 = new Task("Implement Feature A", "Implement the new feature A for the project");
        Task task2 = new Task("Bug Fixing", "Fix bugs in the existing codebase");

        project.addTask(task1);
        project.addTask(task2);

        project.listTasks();

        TeamMember developer = new TeamMember("John");
        TeamMember tester = new TeamMember("Alice");

        developer.assignTask(task1);
        tester.assignTask(task2);

        developer.listAssignedTasks();
        tester.listAssignedTasks();

        task1.markCompleted();

        developer.listAssignedTasks();
        tester.listAssignedTasks();
    }
}
