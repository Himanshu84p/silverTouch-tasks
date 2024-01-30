package VideoGameInventory_Prac73;
import java.util.ArrayList;
import java.util.List;

class Game {
    private String title;
    private String genre;
    private int completionPercentage;

    public Game(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.completionPercentage = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(int completionPercentage) {
        if (completionPercentage >= 0 && completionPercentage <= 100) {
            this.completionPercentage = completionPercentage;
        } else {
            System.out.println("Invalid completion percentage. It must be between 0 and 100.");
        }
    }
}

class Player {
    private String playerName;
    private int playerLevel;

    public Player(String playerName) {
        this.playerName = playerName;
        this.playerLevel = 1;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void levelUp() {
        playerLevel++;
    }
}

class Inventory {
    private List<Game> games;

    public Inventory() {
        this.games = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public List<Game> getGames() {
        return games;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a player
        Player player = new Player("Player1");

        // Create games
        Game game1 = new Game("The Legend of Zelda", "Adventure");
        Game game2 = new Game("FIFA 22", "Sports");
        Game game3 = new Game("Call of Duty: Warzone", "FPS");

        // Create an inventory
        Inventory inventory = new Inventory();

        // Add games to the inventory
        inventory.addGame(game1);
        inventory.addGame(game2);
        inventory.addGame(game3);

        // Display initial inventory
        System.out.println("Initial Inventory:");
        displayInventory(inventory);

        // Player progresses in games
        game1.setCompletionPercentage(50);
        game2.setCompletionPercentage(75);

        // Player levels up
        player.levelUp();

        // Display updated inventory and player details
        System.out.println("\nUpdated Inventory and Player Details:");
        displayInventory(inventory);
        displayPlayerDetails(player);

        // Remove a game from the inventory
        inventory.removeGame(game2);

        // Display final inventory
        System.out.println("\nFinal Inventory:");
        displayInventory(inventory);
    }

    private static void displayInventory(Inventory inventory) {
        List<Game> games = inventory.getGames();
        for (Game game : games) {
            System.out.println("Title: " + game.getTitle());
            System.out.println("Genre: " + game.getGenre());
            System.out.println("Completion Percentage: " + game.getCompletionPercentage() + "%");
            System.out.println("--------");
        }
    }

    private static void displayPlayerDetails(Player player) {
        System.out.println("Player Name: " + player.getPlayerName());
        System.out.println("Player Level: " + player.getPlayerLevel());
        System.out.println("--------");
    }
}
