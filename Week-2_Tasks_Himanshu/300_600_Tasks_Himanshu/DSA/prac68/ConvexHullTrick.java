package prac68;

import java.util.ArrayDeque;
import java.util.Deque;

class LinearFunction {
    long slope;
    long intercept;

    public LinearFunction(long slope, long intercept) {
        this.slope = slope;
        this.intercept = intercept;
    }

    long evaluate(long x) {
        return slope * x + intercept;
    }
}

public class ConvexHullTrick {
    private Deque<LinearFunction> lines;

    public ConvexHullTrick() {
        lines = new ArrayDeque<>();
    }

    public void addFunction(long slope, long intercept) {
        LinearFunction newFunction = new LinearFunction(slope, intercept);

        while (lines.size() >= 2) {
            LinearFunction last = lines.pollLast();
            LinearFunction secondLast = lines.peekLast();

            if ((last.intercept - secondLast.intercept)
                    * (newFunction.slope - last.slope) >= (last.slope - secondLast.slope)
                            * (newFunction.intercept - last.intercept)) {

                continue;
            }

            lines.addLast(last);
            break;
        }

        lines.addLast(newFunction);
    }

    public long query(long x) {
        while (lines.size() >= 2) {
            LinearFunction first = lines.pollFirst();
            LinearFunction second = lines.peekFirst();

            if (first.evaluate(x) < second.evaluate(x)) {

                continue;
            }

            lines.addFirst(first);
            break;
        }

        return lines.peekFirst().evaluate(x);
    }

    public static void main(String[] args) {
        ConvexHullTrick cht = new ConvexHullTrick();

        cht.addFunction(1, 3);
        cht.addFunction(2, 5);
        cht.addFunction(-1, 4);

        System.out.println("Maximum at x = 2: " + cht.query(2));
        System.out.println("Maximum at x = 5: " + cht.query(5));
    }
}
