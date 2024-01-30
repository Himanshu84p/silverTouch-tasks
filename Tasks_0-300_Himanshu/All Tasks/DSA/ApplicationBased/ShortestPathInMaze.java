import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInMaze {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int shortestPathInMaze(int[][] maze, Point start, Point end) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                if (current.x == end.x && current.y == end.y) {
                    return steps;
                }

                for (int j = 0; j < 4; j++) {
                    int newX = current.x + dx[j];
                    int newY = current.y + dy[j];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols
                            && maze[newX][newY] == 1 && !visited[newX][newY]) {
                        queue.offer(new Point(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] maze = {
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 0, 1 },
                { 0, 0, 1, 1, 1 },
                { 1, 1, 1, 0, 1 },
                { 1, 1, 1, 1, 1 }
        };

        Point start = new Point(0, 0);
        Point end = new Point(4, 4);

        int result = shortestPathInMaze(maze, start, end);
        System.out.println("Shortest Path Length: " + result);
    }
}
