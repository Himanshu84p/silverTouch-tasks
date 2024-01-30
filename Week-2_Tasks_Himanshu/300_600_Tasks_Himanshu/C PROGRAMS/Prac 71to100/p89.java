import java.util.Arrays;

public class p89 {
    public static void main(String[] args) {
        
        int numSteps = 10;

        
        double[] state = new double[2 * numSteps + 1];
        state[numSteps] = 1.0; 

        
        for (int step = 0; step < numSteps; step++) {
            state = quantumWalkStep(state);
        }

        
        System.out.println("Final Probability Distribution:");
        for (int position = 0; position < state.length; position++) {
            System.out.println("Position " + (position - numSteps) + ": " + state[position]);
        }
    }

    private static double[] quantumWalkStep(double[] state) {
        int numPositions = state.length;

        
        double[] newState = new double[numPositions];
        for (int position = 0; position < numPositions; position++) {
            newState[position] += 0.5 * state[position];
            if (position > 0) {
                newState[position - 1] += 0.5 * state[position];
            }
            if (position < numPositions - 1) {
                newState[position + 1] += 0.5 * state[position];
            }
        }

        
        double[] finalState = new double[numPositions];
        for (int position = 0; position < numPositions; position++) {
            int shiftPosition = (position + 1) % numPositions;
            finalState[shiftPosition] += newState[position];
        }

        return finalState;
    }
}
