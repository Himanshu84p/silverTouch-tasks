import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class p98 {
    static class Individual {
        int[] genes;
        int fitness;

        public Individual(int[] genes) {
            this.genes = genes;
            this.fitness = calculateFitness();
        }

        private int calculateFitness() {
            return Arrays.stream(genes).sum();
        }
    }

    public static Individual geneticAlgorithm(int populationSize, int geneLength, int generations) {
        List<Individual> population = initializePopulation(populationSize, geneLength);

        for (int generation = 0; generation < generations; generation++) {
            population.sort((a, b) -> b.fitness - a.fitness);
            for (int i = populationSize / 2; i < populationSize; i++) {
                int crossoverPoint = geneLength / 2;
                System.arraycopy(population.get(i - populationSize / 2).genes, crossoverPoint,
                        population.get(i).genes, 0, crossoverPoint);
            }

            
            Random random = new Random();
            for (Individual individual : population) {
                int mutationPoint = random.nextInt(geneLength);
                individual.genes[mutationPoint] ^= 1;
            }
        }

        
        return population.get(0);
    }

    private static List<Individual> initializePopulation(int populationSize, int geneLength) {
        List<Individual> population = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            int[] genes = new int[geneLength];
            Random random = new Random();

            for (int j = 0; j < geneLength; j++) {
                genes[j] = random.nextInt(2); 
            }

            population.add(new Individual(genes));
        }

        return population;
    }

    public static void main(String[] args) {
        int populationSize = 10;
        int geneLength = 8;
        int generations = 100;

        Individual result = geneticAlgorithm(populationSize, geneLength, generations);

        System.out.println("Optimal Genes: " + Arrays.toString(result.genes));
        System.out.println("Optimal Fitness: " + result.fitness);
    }
}
