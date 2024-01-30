package IntervalTrees;

import java.util.ArrayList;
import java.util.List;

class Interval {
    int low, high;

    public Interval(int low, int high) {
        this.low = low;
        this.high = high;
    }
}

class IntervalTreeNode {
    Interval interval;
    int max;
    IntervalTreeNode left, right;

    public IntervalTreeNode(Interval interval) {
        this.interval = interval;
        this.max = interval.high;
        this.left = null;
        this.right = null;
    }
}

public class question1 {
    private IntervalTreeNode root;

    public void insert(Interval interval) {
        root = insert(root, interval);
    }

    private IntervalTreeNode insert(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return new IntervalTreeNode(interval);
        }

        int low = node.interval.low;

        if (interval.low < low) {
            node.left = insert(node.left, interval);
        } else {
            node.right = insert(node.right, interval);
        }

        if (node.max < interval.high) {
            node.max = interval.high;
        }

        return node;
    }

    public List<Interval> search(Interval interval) {
        List<Interval> result = new ArrayList<>();
        search(root, interval, result);
        return result;
    }

    private void search(IntervalTreeNode node, Interval interval, List<Interval> result) {
        if (node == null) {
            return;
        }

        if (node.interval.low <= interval.high && node.interval.high >= interval.low) {
            result.add(node.interval);
        }

        if (node.left != null && node.left.max >= interval.low) {
            search(node.left, interval, result);
        }

        search(node.right, interval, result);
    }

    public void delete(Interval interval) {
        root = delete(root, interval);
    }

    private IntervalTreeNode delete(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return null;
        }

        int low = node.interval.low;

        if (interval.low < low) {
            node.left = delete(node.left, interval);
        } else if (interval.low > low) {
            node.right = delete(node.right, interval);
        } else {
            
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            
            node.interval = minValue(node.right);
            node.right = delete(node.right, node.interval);
        }

        
        node.max = Math.max(maxValue(node.left), maxValue(node.right));

        return node;
    }

    private Interval minValue(IntervalTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.interval;
    }

    private int maxValue(IntervalTreeNode node) {
        return (node != null) ? node.max : Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        question1 intervalTree = new question1();

        intervalTree.insert(new Interval(15, 20));
        intervalTree.insert(new Interval(10, 30));
        intervalTree.insert(new Interval(5, 15));
        intervalTree.insert(new Interval(17, 19));
        intervalTree.insert(new Interval(12, 16));
        intervalTree.insert(new Interval(25, 30));

        System.out.println("Interval Tree created.");

        
        Interval searchInterval = new Interval(14, 16);
        List<Interval> searchResult = intervalTree.search(searchInterval);

        System.out.println("Intervals that overlap with [" + searchInterval.low + ", " + searchInterval.high + "]:");
        for (Interval resultInterval : searchResult) {
            System.out.println("[" + resultInterval.low + ", " + resultInterval.high + "]");
        }

        
        intervalTree.delete(new Interval(15, 20));

        System.out.println("Interval [15, 20] deleted.");

        
        searchResult = intervalTree.search(searchInterval);

        System.out.println("Intervals that overlap with [" + searchInterval.low + ", " + searchInterval.high + "] after deletion:");
        for (Interval resultInterval : searchResult) {
            System.out.println("[" + resultInterval.low + ", " + resultInterval.high + "]");
        }
    }
}
