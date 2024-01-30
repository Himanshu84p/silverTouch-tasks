import java.util.Random;

public class P69 {


    static double objectiveFunction(double x) {
        return -x * x;
    }


    static double hillClimbing(double start, double stepSize, int maxIterations) {
        double currentX = start;

        for (int i = 0; i < maxIterations; i++) {
            double currentObjective = objectiveFunction(currentX);

            double nextX = currentX + stepSize;
            double nextObjective = objectiveFunction(nextX);

            if (nextObjective > currentObjective) {
                currentX = nextX;
            } else {
                stepSize = -stepSize;
            }

            System.out.println("Iteration " + (i + 1) + ": x = " + currentX + ", objective = " + currentObjective);
        }

        return currentX;
    }

    public static void main(String[] args) {
        double start = 2.0;
        double stepSize = 0.1;
        int maxIterations = 50;

        double result = hillClimbing(start, stepSize, maxIterations);

        System.out.println("\nOptimal Solution: x = " + result);
        System.out.println("Optimal Objective Value: " + objectiveFunction(result));
    }
}
