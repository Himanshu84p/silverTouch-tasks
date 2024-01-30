import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class p78 {
    static class Individual {
        String expression;
        double fitness;

        public Individual(String expression) {
            this.expression = expression;
            this.fitness = evaluateFitness(expression);
        }
    }

    static Random random = new Random();

    // Function to generate a random expression
    private static String generateRandomExpression(int length) {
        StringBuilder expression = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            expression.append(randomChar);
        }
        return expression.toString();
    }

    // Function to evaluate the fitness of an expression (lower fitness is better)
    private static double evaluateFitness(String expression) {
        // Placeholder for a fitness evaluation function
        // For symbolic regression, you might compare the expression's output to the
        // target data points
        // and return a measure of the difference.

        // For simplicity, we'll just use a random fitness value in this example.
        return random.nextDouble();
    }

    // Function to perform crossover between two parent expressions
    private static String crossover(String parent1, String parent2) {
        int crossoverPoint = random.nextInt(Math.min(parent1.length(), parent2.length()));
        return parent1.substring(0, crossoverPoint) + parent2.substring(crossoverPoint);
    }

    // Function to perform mutation on an expression
    private static String mutate(String expression, double mutationRate) {
        StringBuilder mutatedExpression = new StringBuilder(expression);
        for (int i = 0; i < expression.length(); i++) {
            if (random.nextDouble() < mutationRate) {
                char randomChar = (char) (random.nextInt(26) + 'a');
                mutatedExpression.setCharAt(i, randomChar);
            }
        }
        return mutatedExpression.toString();
    }

    // Function to select individuals for the next generation using tournament
    // selection
    private static List<Individual> tournamentSelection(List<Individual> population, int tournamentSize) {
        List<Individual> selected = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            int randomIndex = random.nextInt(population.size());
            Individual candidate = population.get(randomIndex);
            for (int j = 1; j < tournamentSize; j++) {
                randomIndex = random.nextInt(population.size());
                Individual competitor = population.get(randomIndex);
                if (competitor.fitness < candidate.fitness) {
                    candidate = competitor;
                }
            }
            selected.add(candidate);
        }
        return selected;
    }

    // Function to evolve the population using genetic programming
    private static List<Individual> evolvePopulation(List<Individual> population, int tournamentSize,
            double mutationRate) {
        List<Individual> newPopulation = new ArrayList<>();

        // Elitism: Keep the best individual from the current population
        newPopulation.add(getBestIndividual(population));

        while (newPopulation.size() < population.size()) {
            // Tournament selection
            List<Individual> parents = tournamentSelection(population, tournamentSize);

            // Crossover
            String childExpression = crossover(parents.get(0).expression, parents.get(1).expression);

            // Mutation
            childExpression = mutate(childExpression, mutationRate);

            // Create a new individual
            newPopulation.add(new Individual(childExpression));
        }

        return newPopulation;
    }

    // Function to get the best individual from a population
    private static Individual getBestIndividual(List<Individual> population) {
        Individual best = population.get(0);
        for (Individual individual : population) {
            if (individual.fitness < best.fitness) {
                best = individual;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        // Parameters
        int populationSize = 100;
        int maxGenerations = 100;
        int tournamentSize = 5;
        double mutationRate = 0.01;
        int expressionLength = 10;

        // Generate an initial population
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            String randomExpression = generateRandomExpression(expressionLength);
            population.add(new Individual(randomExpression));
        }

        // Evolution loop
        for (int generation = 1; generation <= maxGenerations; generation++) {
            // Print the best individual of the current generation
            Individual bestIndividual = getBestIndividual(population);
            System.out.println("Generation " + generation + ", Best Fitness: " + bestIndividual.fitness +
                    ", Expression: " + bestIndividual.expression);

            // Evolve the population
            population = evolvePopulation(population, tournamentSize, mutationRate);
        }
    }
}
