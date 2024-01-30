import java.util.ArrayList;
import java.util.List;

class Bidder {
    String name;

    Bidder(String bidderName) {
        this.name = bidderName;
    }

    void bidOnItem(Item item, double bidAmount) {
        item.placeBid(this, bidAmount);
    }
}

class Item {
    String name;
    double startingPrice;
    double currentBid;
    Bidder currentBidder;

    Item(String itemName, double price) {
        this.name = itemName;
        this.startingPrice = price;
        this.currentBid = price;
        this.currentBidder = null;
    }

    void placeBid(Bidder bidder, double bidAmount) {
        if (bidAmount > currentBid) {
            currentBid = bidAmount;
            currentBidder = bidder;
            System.out.println("Bid placed successfully by " + bidder.name + " for item " + name + " with bid amount $" + bidAmount);
        } else {
            System.out.println("Bid amount must be greater than the current bid.");
        }
    }
}

class Auction {
    List<Item> items;

    Auction() {
        this.items = new ArrayList<>();
    }

    void addItem(String itemName, double startingPrice) {
        items.add(new Item(itemName, startingPrice));
    }

    void startAuction() {
        System.out.println("Auction Started!");
    }

    void displayItems() {
        System.out.println("Auction Items:");
        for (Item item : items) {
            System.out.print("Item: " + item.name + ", Starting Price: $" + item.startingPrice + ", Current Bid: $" + item.currentBid);
            if (item.currentBidder != null) {
                System.out.print(" (Bidder: " + item.currentBidder.name + ")");
            }
            System.out.println();
        }
    }
}

public class Prac90_OnlineAuctionApp {
    public static void main(String[] args) {
        Auction onlineAuction = new Auction();

        // Adding items to the auction
        onlineAuction.addItem("Painting", 100.0);
        onlineAuction.addItem("Antique Vase", 200.0);
        onlineAuction.addItem("Gaming Console", 150.0);

        // Displaying items in the auction
        onlineAuction.displayItems();

        // Creating bidders
        Bidder bidder1 = new Bidder("John");
        Bidder bidder2 = new Bidder("Alice");

        // Bidding on items
        bidder1.bidOnItem(onlineAuction.items.get(0), 120.0);
        bidder2.bidOnItem(onlineAuction.items.get(0), 130.0);

        bidder2.bidOnItem(onlineAuction.items.get(1), 220.0);
        bidder1.bidOnItem(onlineAuction.items.get(1), 210.0);

        // Displaying updated items in the auction
        onlineAuction.displayItems();
    }
}
