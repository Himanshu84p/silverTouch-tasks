package YAO_Graph83;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class YaoGraphConstruction {
    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Point other) {
            return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
        }
    }

    public static class Edge {
        Point start, end;

        public Edge(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class YaoGraph {
        List<Point> points;
        List<List<Integer>> adjacencyList;

        public YaoGraph(List<Point> points) {
            this.points = points;
            this.adjacencyList = new ArrayList<>();
            for (int i = 0; i < points.size(); i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void constructYaoGraph() {
            for (int i = 0; i < points.size(); i++) {
                List<Point> sortedByAngle = new ArrayList<>(points);
             //   Collections.sort(sortedByAngle, (p1, p2) ->
                    //    Double.compare(Math.atan2(p1.y - points.get(i).y, p1.x - points.get(i).x),
                      //          Math.atan2(p2.y - points.get(i).y, p2.x - points.get(i).x)));

                for (int j = 1; j < sortedByAngle.size(); j++) {
                    adjacencyList.get(i).add(points.indexOf(sortedByAngle.get(j)));
                }
            }
        }

        public List<Edge> getEdges() {
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < points.size(); i++) {
                for (int neighbor : adjacencyList.get(i)) {
                    edges.add(new Edge(points.get(i), points.get(neighbor)));
                }
            }
            return edges;
        }
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));

        YaoGraph yaoGraph = new YaoGraph(points);
        yaoGraph.constructYaoGraph();

        List<Edge> edges = yaoGraph.getEdges();
        for (Edge edge : edges) {
            System.out.println("Edge: (" + edge.start.x + "," + edge.start.y + ") - (" +
                    edge.end.x + "," + edge.end.y + ")");
        }
    }
}
