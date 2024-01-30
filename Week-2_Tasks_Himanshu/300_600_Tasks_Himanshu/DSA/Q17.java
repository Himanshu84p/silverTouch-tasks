import java.util.ArrayList;
import java.util.Collections;

public class Q17 {

    public static void main(String[] args) {
        String input = "abc";
        ArrayList<String> powerSet = generatePowerSet(input);

        System.out.println("Power Set in Lexicographic Order:");
        for (String subset : powerSet) {
            System.out.println(subset);
        }
    }

    public static ArrayList<String> generatePowerSet(String str) {
        ArrayList<String> result = new ArrayList<>();
        generatePowerSetHelper(str, "", 0, result);
        Collections.sort(result);
        return result;
    }

    private static void generatePowerSetHelper(String str, String currentSubset, int index, ArrayList<String> result) {
        int n = str.length();

        result.add(currentSubset);

        for (int i = index; i < n; i++) {
            generatePowerSetHelper(str, currentSubset + str.charAt(i), i + 1, result);
        }
    }
}
