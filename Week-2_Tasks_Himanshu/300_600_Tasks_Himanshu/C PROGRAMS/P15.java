import java.util.Scanner;

public class P15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the radius of the sphere: ");
        double radius = scanner.nextDouble();

        double surfaceArea = calculateSphereSurfaceArea(radius);

        System.out.println("The surface area of the sphere is: " + surfaceArea);

        scanner.close();
    }

    private static double calculateSphereSurfaceArea(double radius) {
        return 4 * Math.PI * Math.pow(radius, 2);
    }
}
