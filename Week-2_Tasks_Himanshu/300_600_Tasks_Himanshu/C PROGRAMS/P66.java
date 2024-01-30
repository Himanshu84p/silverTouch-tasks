import java.util.Arrays;

public class P66
{

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double distanceTo(Point other) {
            int dx = this.x - other.x;
            int dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    // Dynamic Programming function to solve BETSP
    static double bitonicEuclideanTSP(Point[] points) {
        int n = points.length;
        double[][] dp = new double[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Double.POSITIVE_INFINITY);
        }

        dp[0][0] = 0;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = dp[i][j - 1] + points[j - 1].distanceTo(points[j]);
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k][i] + points[k].distanceTo(points[j]));
                }
            }
        }

        double minDistance = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n - 1; i++) {
            minDistance = Math.min(minDistance, dp[i][n - 1] + points[i].distanceTo(points[n - 1]));
        }

        return minDistance;
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(1, 1),
                new Point(2, 4),
                new Point(3, 8),
                new Point(4, 6),
                new Point(5, 3),
                new Point(7, 7),
                new Point(9, 2)
        };

        double minDistance = bitonicEuclideanTSP(points);
        System.out.println("Minimum Distance for Bitonic Euclidean TSP: " + minDistance);
    }
}
