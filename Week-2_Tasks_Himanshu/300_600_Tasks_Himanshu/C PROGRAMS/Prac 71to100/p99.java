import java.util.Arrays;

public class p99 {
    public static int[] viterbi(int[] observations, int[] states, double[] startProbabilities,
            double[][] transitionProbabilities, double[][] emissionProbabilities) {
        int numStates = states.length;
        int numObservations = observations.length;

        double[][] viterbiTable = new double[numStates][numObservations];
        int[][] backpointerTable = new int[numStates][numObservations];

        
        for (int i = 0; i < numStates; i++) {
            viterbiTable[i][0] = startProbabilities[i] * emissionProbabilities[i][observations[0]];
            backpointerTable[i][0] = -1;
        }

        
        for (int t = 1; t < numObservations; t++) {
            for (int s = 0; s < numStates; s++) {
                double maxProbability = 0;
                int maxPrevState = -1;

                for (int prev = 0; prev < numStates; prev++) {
                    double probability = viterbiTable[prev][t - 1] * transitionProbabilities[prev][s] *
                            emissionProbabilities[s][observations[t]];

                    if (probability > maxProbability) {
                        maxProbability = probability;
                        maxPrevState = prev;
                    }
                }

                viterbiTable[s][t] = maxProbability;
                backpointerTable[s][t] = maxPrevState;
            }
        }

        
        double maxProbability = 0;
        int maxState = -1;

        for (int s = 0; s < numStates; s++) {
            if (viterbiTable[s][numObservations - 1] > maxProbability) {
                maxProbability = viterbiTable[s][numObservations - 1];
                maxState = s;
            }
        }

        
        int[] path = new int[numObservations];
        path[numObservations - 1] = maxState;

        for (int t = numObservations - 1; t > 0; t--) {
            maxState = backpointerTable[maxState][t];
            path[t - 1] = maxState;
        }

        return path;
    }

    public static void main(String[] args) {
        int[] observations = { 0, 1, 0 }; 
        int[] states = { 0, 1 }; 
        double[] startProbabilities = { 0.8, 0.2 }; 
        double[][] transitionProbabilities = { { 0.7, 0.3 }, { 0.4, 0.6 } }; 
        double[][] emissionProbabilities = { { 0.2, 0.4, 0.4 }, { 0.5, 0.4, 0.1 } }; 

        int[] result = viterbi(observations, states, startProbabilities, transitionProbabilities,
                emissionProbabilities);

        System.out.println("Optimal State Sequence: " + Arrays.toString(result));
    }
}
