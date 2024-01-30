import java.util.Scanner;

public class P11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the side length of the cube: ");
        double sideLength = scanner.nextDouble();

        double volume = calculateCubeVolume(sideLength);

        System.out.println("The volume of the cube is: " + volume);

        scanner.close();
    }

    private static double calculateCubeVolume(double sideLength) {
        return Math.pow(sideLength, 3);
    }
}
