import java.util.Arrays;
import java.util.Comparator;

public class P67 {

    static class Item {
        double weight;
        double value;

        Item(double weight, double value) {
            this.weight = weight;
            this.value = value;
        }
    }

    static double fractionalKnapsack(int capacity, Item[] items) {
        Arrays.sort(items, Comparator.comparingDouble(item -> item.value / item.weight));

        double totalValue = 0.0;
        int n = items.length;

        for (int i = n - 1; i >= 0; i--) {
            if (items[i].weight <= capacity) {
                totalValue += items[i].value;
                capacity -= items[i].weight;
            } else {
                totalValue += (capacity / items[i].weight) * items[i].value;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int capacity = 50;
        Item[] items = {
                new Item(10, 60),
                new Item(20, 100),
                new Item(30, 120)
        };

        double maxValue = fractionalKnapsack(capacity, items);
        System.out.println("Maximum value in Knapsack: " + maxValue);
    }
}
