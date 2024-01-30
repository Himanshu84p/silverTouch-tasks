import java.util.Random;

public class P65 {


    public static double fitnessFunction(double[] x) {

        double sum = 0.0;
        for (double value : x) {
            sum += value * value;
        }
        return sum;
    }


    public static double[] differentialEvolution(int dimensions, int populationSize, int maxGenerations,
                                                 double crossoverProbability, double scalingFactor) {
        Random random = new Random();


        double[][] population = new double[populationSize][dimensions];
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < dimensions; j++) {
                population[i][j] = random.nextDouble();
            }
        }

        for (int generation = 0; generation < maxGenerations; generation++) {
            for (int i = 0; i < populationSize; i++) {
                int r1, r2, r3;
                do {
                    r1 = random.nextInt(populationSize);
                } while (r1 == i);
                do {
                    r2 = random.nextInt(populationSize);
                } while (r2 == i || r2 == r1);
                do {
                    r3 = random.nextInt(populationSize);
                } while (r3 == i || r3 == r1 || r3 == r2);

                double[] mutant = new double[dimensions];
                for (int j = 0; j < dimensions; j++) {
                    mutant[j] = population[r1][j] + scalingFactor * (population[r2][j] - population[r3][j]);
                }

                double[] trial = new double[dimensions];
                for (int j = 0; j < dimensions; j++) {
                    if (random.nextDouble() < crossoverProbability) {
                        trial[j] = mutant[j];
                    } else {
                        trial[j] = population[i][j];
                    }
                }

                double fX = fitnessFunction(population[i]);
                double fTrial = fitnessFunction(trial);
                if (fTrial < fX) {
                    for (int j = 0; j < dimensions; j++) {
                        population[i][j] = trial[j];
                    }
                }
            }
        }

        double[] bestSolution = population[0];
        double bestFitness = fitnessFunction(bestSolution);

        for (int i = 1; i < populationSize; i++) {
            double currentFitness = fitnessFunction(population[i]);
            if (currentFitness < bestFitness) {
                bestFitness = currentFitness;
                bestSolution = population[i];
            }
        }

        return bestSolution;
    }

    public static void main(String[] args) {
        int dimensions = 3;
        int populationSize = 50;
        int maxGenerations = 1000;
        double crossoverProbability = 0.8;
        double scalingFactor = 0.5;

        double[] result = differentialEvolution(dimensions, populationSize, maxGenerations, crossoverProbability, scalingFactor);

        System.out.println("Optimal Solution:");
        for (double value : result) {
            System.out.print(value + " ");
        }
        System.out.println("\nOptimal Fitness: " + fitnessFunction(result));
    }
}
