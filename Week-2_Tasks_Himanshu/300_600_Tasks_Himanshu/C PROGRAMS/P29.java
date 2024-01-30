import java.util.ArrayList;
import java.util.List;

public class P29 {

    private int[][] distances;
    private int startCity;

    public P29(int[][] distances, int startCity) {
        this.distances = distances;
        this.startCity = startCity;
    }

    public List<Integer> solve() {
        List<Integer> tour = new ArrayList<>();
        tour.add(startCity);
        int currentCity = startCity;
        int visited = 1;

        while (visited < distances.length) {
            int nearestCity = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < distances.length; i++) {
                if (distances[currentCity][i] > 0 && !tour.contains(i) &&
                        distances[currentCity][i] < minDistance) {
                    nearestCity = i;
                    minDistance = distances[currentCity][i];
                }
            }
            tour.add(nearestCity);
            currentCity = nearestCity;
            visited++;
        }

        tour.add(startCity); // Return to starting city
        return tour;
    }

    public static void main(String[] args) {
        int[][] distances = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int startCity = 0;

        P29 tsp = new P29(distances, startCity);
        List<Integer> tour = tsp.solve();
        System.out.println("Tour: " + tour);
    }
}
