import java.util.ArrayList;
import java.util.List;

class Stock {
    private String symbol;
    private String companyName;
    private double currentPrice;

    public Stock(String symbol, String companyName, double currentPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }
}

class Transaction {
    public enum TransactionType {
        BUY, SELL
    }

    private Stock stock;
    private TransactionType transactionType;
    private int quantity;
    private double transactionAmount;

    public Transaction(Stock stock, TransactionType transactionType, int quantity) {
        this.stock = stock;
        this.transactionType = transactionType;
        this.quantity = quantity;
        calculateTransactionAmount();
    }

    private void calculateTransactionAmount() {
        if (transactionType == TransactionType.BUY) {
            transactionAmount = stock.getCurrentPrice() * quantity;
        } else {
            transactionAmount = -stock.getCurrentPrice() * quantity;
        }
    }

    public Stock getStock() {
        return stock;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }
}

class Portfolio {
    private List<Transaction> transactions;

    public Portfolio() {
        this.transactions = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity for buying stocks.");
            return;
        }

        Transaction buyTransaction = new Transaction(stock, Transaction.TransactionType.BUY, quantity);
        transactions.add(buyTransaction);
    }

    public void sellStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity for selling stocks.");
            return;
        }

        // Check if there are enough stocks to sell
        int ownedQuantity = getOwnedStockQuantity(stock);
        if (ownedQuantity < quantity) {
            System.out.println("Not enough stocks to sell.");
            return;
        }

        Transaction sellTransaction = new Transaction(stock, Transaction.TransactionType.SELL, quantity);
        transactions.add(sellTransaction);
    }

    private int getOwnedStockQuantity(Stock stock) {
        int ownedQuantity = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getStock().equals(stock) && transaction.getTransactionType() == Transaction.TransactionType.BUY) {
                ownedQuantity += transaction.getQuantity();
            } else if (transaction.getStock().equals(stock) && transaction.getTransactionType() == Transaction.TransactionType.SELL) {
                ownedQuantity -= transaction.getQuantity();
            }
        }
        return ownedQuantity;
    }
    public double calculatePortfolioValue() {
        double portfolioValue = 0;
        for (Transaction transaction : transactions) {
            portfolioValue += transaction.getTransactionAmount();
        }
        return portfolioValue;
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}

public class Prac76_StockPortfolioManagement {
    public static void main(String[] args) {
        // Create stocks
        Stock appleStock = new Stock("AAPL", "Apple Inc.", 150.0);
        Stock googleStock = new Stock("GOOGL", "Alphabet Inc.", 2800.0);

        // Create a portfolio
        Portfolio portfolio = new Portfolio();

        // Buy and sell stocks
        portfolio.buyStock(appleStock, 10);
        portfolio.buyStock(googleStock, 5);
        portfolio.sellStock(googleStock, 3);

        // Display transaction history
        System.out.println("Transaction History:");
        List<Transaction> transactions = portfolio.getTransactionHistory();
        for (Transaction transaction : transactions) {
            System.out.println("Stock: " + transaction.getStock().getSymbol() +
                    ", Type: " + transaction.getTransactionType() +
                    ", Quantity: " + transaction.getQuantity() +
                    ", Amount: $" + transaction.getTransactionAmount());
        }

        // Calculate and display portfolio value
        double portfolioValue = portfolio.calculatePortfolioValue();
        System.out.println("\nPortfolio Value: $" + portfolioValue);
    }
}
