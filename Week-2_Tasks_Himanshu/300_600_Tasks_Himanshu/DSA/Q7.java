import java.util.*;

public class Q7 {

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] results = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            Set<String> visited = new HashSet<>();
            results[i] = dfs(graph, start, end, visited);
        }

        return results;
    }

    private static Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            double value = values[i];

            graph.computeIfAbsent(from, k -> new HashMap<>()).put(to, value);
            graph.computeIfAbsent(to, k -> new HashMap<>()).put(from, 1 / value);
        }

        return graph;
    }

    private static double dfs(Map<String, Map<String, Double>> graph, String start, String end, Set<String> visited) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1.0;
        }

        if (start.equals(end)) {
            return 1.0;
        }

        visited.add(start);

        for (Map.Entry<String, Double> neighbor : graph.get(start).entrySet()) {
            String next = neighbor.getKey();
            if (!visited.contains(next)) {
                double result = dfs(graph, next, end, visited);
                if (result != -1.0) {
                    return result * neighbor.getValue();
                }
            }
        }

        return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = { 2.0, 3.0 };
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"),
                Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));

        double[] results = calcEquation(equations, values, queries);

        for (int i = 0; i < results.length; i++) {
            System.out.println(queries.get(i).get(0) + " / " + queries.get(i).get(1) + " = " +
                    (results[i] == -1.0 ? "undefined" : results[i]));
        }
    }
}
