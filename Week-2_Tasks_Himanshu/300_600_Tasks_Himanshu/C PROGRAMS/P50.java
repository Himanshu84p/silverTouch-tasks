import java.util.LinkedList;
import java.util.Queue;

public class P50 {


    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static int bfs(char[][] grid, int startX, int startY, int endX, int endY) {
        int rows = grid.length;
        int cols = grid[0].length;


        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();


        queue.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int steps = current[2];


            if (x == endX && y == endY) {
                return steps;
            }


            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY] && grid[newX][newY] != '#') {
                    queue.offer(new int[]{newX, newY, steps + 1});
                    visited[newX][newY] = true;
                }
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'S', '.', '.', '#', '.'},
                {'.', '#', '.', '.', '.'},
                {'.', '#', '#', '#', '.'},
                {'.', '.', '.', '.', 'E'}
        };

        int startX = 0;
        int startY = 0;
        int endX = 3;
        int endY = 4;

        int steps = bfs(grid, startX, startY, endX, endY);

        if (steps != -1) {
            System.out.println("Minimum steps to reach the destination: " + steps);
        } else {
            System.out.println("Destination is not reachable.");
        }
    }
}
