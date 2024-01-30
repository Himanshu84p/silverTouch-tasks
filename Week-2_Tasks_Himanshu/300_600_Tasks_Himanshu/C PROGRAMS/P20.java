import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P20 {
    public static int josephus(int n, int k) {
        List<Integer> people = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        int index = 0;
        while (people.size() > 1) {
            index = (index + k - 1) % people.size();
            people.remove(index);
        }

        return people.get(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total number of people: ");
        int n = scanner.nextInt();

        System.out.print("Enter the counting interval (k): ");
        int k = scanner.nextInt();

        int survivor = josephus(n, k);
        System.out.println("The survivor is at position: " + survivor);

        scanner.close();
    }
}
