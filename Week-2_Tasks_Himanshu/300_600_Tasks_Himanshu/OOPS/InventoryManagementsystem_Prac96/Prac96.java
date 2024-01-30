package InventoryManagementsystem_Prac96;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int quantity) {
        stock += quantity;
        if (stock < 0) {
            stock = 0;
        }
    }
}

class Category {
    private String name;
    private List<Product> products;

    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addProduct(String productName, double price, int stock) {
        products.add(new Product(productName, price, stock));
    }

    public void removeProduct(String productName) {
        products.removeIf(product -> product.getName().equals(productName));
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getTotalStock() {
        int totalStock = 0;
        for (Product product : products) {
            totalStock += product.getStock();
        }
        return totalStock;
    }
}

class Warehouse {
    private String name;
    private List<Category> categories;

    public Warehouse(String name) {
        this.name = name;
        this.categories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCategory(String categoryName) {
        categories.add(new Category(categoryName));
    }

    public void removeCategory(String categoryName) {
        categories.removeIf(category -> category.getName().equals(categoryName));
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void generateInventoryReport() {
        System.out.println("\nInventory Report for Warehouse " + name + ":\n" +
                "---------------------------------------------");

        for (Category category : categories) {
            System.out.println("Category: " + category.getName());
            for (Product product : category.getProducts()) {
                System.out.println("  - " + product.getName() + ", Stock: " + product.getStock() +
                        ", Price: $" + product.getPrice());
            }
            System.out.println("  Total Stock: " + category.getTotalStock() +
                    "\n---------------------------------------------");
        }
    }
}

public class Prac96 {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse("Main Warehouse");

        warehouse.addCategory("Electronics");
        warehouse.addCategory("Clothing");

        warehouse.getCategories().get(0).addProduct("Laptop", 1200.0, 10);
        warehouse.getCategories().get(0).addProduct("Smartphone", 600.0, 20);
        warehouse.getCategories().get(1).addProduct("T-Shirt", 15.0, 50);
        warehouse.getCategories().get(1).addProduct("Jeans", 40.0, 30);

        warehouse.generateInventoryReport();

        warehouse.getCategories().get(0).getProducts().get(0).updateStock(-5);
        warehouse.getCategories().get(1).getProducts().get(1).updateStock(10);

        warehouse.generateInventoryReport();
    }
}
