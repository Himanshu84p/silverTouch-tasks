import java.util.LinkedList;
import java.util.Queue;

public class Q21 {

    public static void main(String[] args) {
        char[][] grid = {
                { 'S', '0', '0', '0', '0', '0', '1', '0', '0', '0' },
                { '0', '1', '1', '1', '1', '0', '1', '1', '1', '0' },
                { '0', '1', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '1', '0', '1', '1', '1', '1', '1', '1', '0' },
                { '0', '1', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '1', '1', '1', '1', '1', '1', '1', '1', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', 'E' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' }
        };

        int shortestRouteLength = findShortestSafeRoute(grid);
        System.out.println("Shortest safe route length: " + shortestRouteLength);
    }

    public static int findShortestSafeRoute(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int startX = -1, startY = -1, endX = -1, endY = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.offer(new int[] { startX, startY, 0 });
        visited[startX][startY] = true;

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];

            if (x == endX && y == endY) {
                return distance;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (isValidPosition(newX, newY, rows, cols) && !visited[newX][newY] && grid[newX][newY] != '1') {
                    queue.offer(new int[] { newX, newY, distance + 1 });
                    visited[newX][newY] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isValidPosition(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
