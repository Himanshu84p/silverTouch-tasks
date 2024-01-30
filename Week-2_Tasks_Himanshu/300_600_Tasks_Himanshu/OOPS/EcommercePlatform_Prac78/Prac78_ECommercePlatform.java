package EcommercePlatform_Prac78;

import java.util.ArrayList;
import java.util.List;

class Product {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public void addProducts(List<Product> products) {
        items.addAll(products);
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clearCart() {
        items.clear();
    }
}

class Order {
    private String orderId;
    private ShoppingCart cart;
    private String customerName;
    private String shippingAddress;

    public Order(String orderId, ShoppingCart cart, String customerName, String shippingAddress) {
        this.orderId = orderId;
        this.cart = new ShoppingCart();
        // Copy products from the original cart to the order cart
        this.cart.addProducts(cart.getItems());
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
}

public class Prac78_ECommercePlatform {
    public static void main(String[] args) {
        // Create products
        Product laptop = new Product("P1", "Laptop", 999.99);
        Product phone = new Product("P2", "Smartphone", 399.99);

        // Create a shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Add products to the shopping cart
        cart.addProduct(laptop);
        cart.addProduct(phone);

        // Display shopping cart items and total
        System.out.println("Shopping Cart Items:");
        for (Product item : cart.getItems()) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        System.out.println("Total: $" + cart.calculateTotal());

        // Process an order
        Order order = new Order("O1", cart, "John Doe", "123 Main St.");

        // Display order details
        System.out.println("\nOrder Details:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("Shipping Address: " + order.getShippingAddress());
        System.out.println("Order Total: $" + order.getCart().calculateTotal());
    }
}
