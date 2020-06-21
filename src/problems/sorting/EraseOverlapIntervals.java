package problems.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 */
public class EraseOverlapIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][] {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    private static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // sort by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int overlap = 0;
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // if they overlap, count
            int[] current = intervals[i];
            if (current[0] < prev[1]) {
                overlap++;
                prev[1] = Math.min(prev[1], current[1]);
            } else {
                prev = current;
            }
        }
        return overlap;
    }
}
