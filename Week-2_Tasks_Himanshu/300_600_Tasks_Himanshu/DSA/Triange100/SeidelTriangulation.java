package Triange100;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class SeidelTriangulation {

    static class Edge {
        int start;
        int end;

        Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class MonotoneVertex {
        Point2D point;
        int originalIndex;

        MonotoneVertex(Point2D point, int originalIndex) {
            this.point = point;
            this.originalIndex = originalIndex;
        }
    }

    public static List<Edge> triangulate(List<Point2D> polygon) {
        List<Edge> triangulation = new ArrayList<>();

        if (!isSimple(polygon)) {
            System.out.println("The polygon is not simple. Triangulation not possible.");
            return triangulation;
        }

        int n = polygon.size();

        List<List<MonotoneVertex>> monotonePolygons = decomposeToMonotonePolygons(polygon);

        for (List<MonotoneVertex> monotonePolygon : monotonePolygons) {
            triangulation.addAll(triangulateMonotonePolygon(monotonePolygon));
        }

        return triangulation;
    }

    private static boolean isSimple(List<Point2D> polygon) {
        int n = polygon.size();
        for (int i = 0; i < n; i++) {
            Point2D a1 = polygon.get(i);
            Point2D a2 = polygon.get((i + 1) % n);

            for (int j = i + 1; j < n; j++) {
                Point2D b1 = polygon.get(j);
                Point2D b2 = polygon.get((j + 1) % n);

                if (doIntersect(a1, a2, b1, b2)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean doIntersect(Point2D p1, Point2D q1, Point2D p2, Point2D q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        return false;
    }

    private static int orientation(Point2D p, Point2D q, Point2D r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    private static List<List<MonotoneVertex>> decomposeToMonotonePolygons(List<Point2D> polygon) {
        List<MonotoneVertex> sortedVertices = sortVertices(polygon);
        Stack<MonotoneVertex> stack = new Stack<>();
        List<List<MonotoneVertex>> monotonePolygons = new ArrayList<>();

        for (MonotoneVertex vertex : sortedVertices) {
            if (vertex.point.getY() > vertex.point.getY() + 1e-9) {
                while (!stack.isEmpty() && stack.peek().point.getY() < vertex.point.getY()) {
                    stack.pop();
                }
                stack.push(vertex);
            } else {
                List<MonotoneVertex> rightChain = new ArrayList<>();
                while (!stack.isEmpty() && stack.peek().point.getY() > vertex.point.getY()) {
                    rightChain.add(stack.pop());
                }
                stack.push(vertex);
                monotonePolygons.add(rightChain);
            }
        }

        return monotonePolygons;
    }

    private static List<Edge> triangulateMonotonePolygon(List<MonotoneVertex> monotonePolygon) {
        List<Edge> diagonals = new ArrayList<>();
        Stack<MonotoneVertex> stack = new Stack<>();

        for (MonotoneVertex vertex : monotonePolygon) {
            if (vertex.point.getY() > vertex.point.getY() + 1e-9) {
                while (stack.size() > 1) {
                    if (stack.peek().point.getY() < vertex.point.getY()) {
                        diagonals.add(new Edge(vertex.originalIndex, stack.peek().originalIndex));
                    } else {
                        break;
                    }
                    stack.pop();
                }
                stack.push(vertex);
            } else {
                while (stack.size() > 1) {
                    diagonals.add(new Edge(vertex.originalIndex, stack.peek().originalIndex));
                    stack.pop();
                }
                stack.pop();
            }
        }

        return diagonals;
    }

    private static List<MonotoneVertex> sortVertices(List<Point2D> polygon) {
        int n = polygon.size();
        List<MonotoneVertex> sortedVertices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            sortedVertices.add(new MonotoneVertex(polygon.get(i), i));
        }

        sortedVertices.sort((a, b) -> {
            if (a.point.getY() != b.point.getY()) {
                return Double.compare(a.point.getY(), b.point.getY());
            } else {
                return Double.compare(a.point.getX(), b.point.getX());
            }
        });

        return sortedVertices;
    }

    public static void main(String[] args) {
        // Example: Simple polygon vertices
        List<Point2D> polygon = new ArrayList<>();
        polygon.add(new Point2D.Double(0, 0));
        polygon.add(new Point2D.Double(4, 0));
        polygon.add(new Point2D.Double(2, 3));
        polygon.add(new Point2D.Double(1, 1));
        polygon.add(new Point2D.Double(3, 1));

        List<Edge> triangulation = triangulate(polygon);

        System.out.println("Triangulation Edges:");
        for (Edge edge : triangulation) {
            System.out.println("Edge: " + edge.start + " - " + edge.end);
        }
    }
}
