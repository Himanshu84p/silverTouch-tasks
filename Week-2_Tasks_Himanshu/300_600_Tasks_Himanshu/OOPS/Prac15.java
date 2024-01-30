
import java.util.ArrayList;
import java.util.List;

// Command
class AddItemCommand {
    private final String item;

    public AddItemCommand(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}

// Command Handler
class CommandHandler {
    private final List<String> items = new ArrayList<>();

    public void handle(AddItemCommand command) {
        String newItem = command.getItem();
        // Perform validation, business logic, etc.
        items.add(newItem);
        System.out.println("Item added: " + newItem);
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}

// Query
class GetItemsQuery {
    // Empty query for simplicity
}

// Query Handler
class QueryHandler {
    private final CommandHandler commandHandler;

    public QueryHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public List<String> handle(GetItemsQuery query) {
        // You can also forward queries to appropriate query handlers
        // For simplicity, just return items from the command handler
        return commandHandler.getItems();
    }
}

// Client code
public class Prac15 {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        QueryHandler queryHandler = new QueryHandler(commandHandler);

        // Command execution
        AddItemCommand addItemCommand = new AddItemCommand("New Item");
        commandHandler.handle(addItemCommand);

        // Query execution
        GetItemsQuery getItemsQuery = new GetItemsQuery();
        List<String> items = queryHandler.handle(getItemsQuery);
        System.out.println("Items retrieved: " + items);
    }
}
