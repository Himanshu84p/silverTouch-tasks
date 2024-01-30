package IntervalTrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Interval2 {
    int start, end;

    public Interval2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

public class question2 {

    public static List<Interval2> findOverlappingIntervals(Interval2[] intervals) {
        List<Interval2> result = new ArrayList<>();

        if (intervals == null || intervals.length <= 1) {
            return result; // No overlaps if there are less than two intervals
        }

        // Sort intervals based on their start times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        Interval2 current = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            Interval2 next = intervals[i];

            // Check for overlap
            if (current.end >= next.start) {
                // Overlapping intervals found, merge them
                current.end = Math.max(current.end, next.end);
            } else {
                // No overlap, add the current interval to the result and update current interval
                result.add(current);
                current = next;
            }
        }

        // Add the last interval to the result
        result.add(current);

        return result;
    }

    public static void main(String[] args) {
        Interval2[] intervals = {
                new Interval2(1, 3),
                new Interval2(2, 4),
                new Interval2(5, 7),
                new Interval2(6, 8),
                new Interval2(9, 11),
                new Interval2(10, 12)
        };

        List<Interval2> overlappingIntervals = findOverlappingIntervals(intervals);

        System.out.println("Overlapping Intervals:");
        for (Interval2 interval : overlappingIntervals) {
            System.out.println(interval);
        }
    }
}

