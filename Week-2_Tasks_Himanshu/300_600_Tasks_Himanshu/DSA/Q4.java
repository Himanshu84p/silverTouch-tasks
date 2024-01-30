import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class CityWithSmallestNeighbors {

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class City implements Comparable<City> {
        int id, distance, neighbors;

        public City(int id, int distance, int neighbors) {
            this.id = id;
            this.distance = distance;
            this.neighbors = neighbors;
        }

        @Override
        public int compareTo(City other) {

            if (this.distance != other.distance) {
                return Integer.compare(this.distance, other.distance);
            }
            return Integer.compare(this.neighbors, other.neighbors);
        }
    }

    static int findCity(int n, int[][] edges, int distanceThreshold) {

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        int smallestNeighbors = Integer.MAX_VALUE;
        int result = -1;

        for (int start = 0; start < n; start++) {

            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[start] = 0;

            PriorityQueue<City> pq = new PriorityQueue<>();
            pq.offer(new City(start, 0, 0));
            City currentCity = null;

            while (!pq.isEmpty()) {
                currentCity = pq.poll();

                for (Edge neighbor : graph.get(currentCity.id)) {
                    int newDistance = distances[currentCity.id] + neighbor.weight;

                    if (newDistance < distances[neighbor.to]) {
                        distances[neighbor.to] = newDistance;
                        pq.offer(new City(neighbor.to, newDistance, currentCity.neighbors + 1));
                    }
                }
            }

            if (distances[start] <= distanceThreshold && currentCity.neighbors < smallestNeighbors) {
                smallestNeighbors = currentCity.neighbors;
                result = start;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = { { 0, 1, 3 }, { 1, 2, 1 }, { 1, 3, 4 }, { 2, 3, 1 } };
        int distanceThreshold = 4;

        int result = findCity(n, edges, distanceThreshold);
        System.out.println("City with the smallest number of neighbors at a threshold distance: " + result);
    }
}
