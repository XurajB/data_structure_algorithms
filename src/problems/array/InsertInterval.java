package problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 */
public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};

        int[][] ans = insert(intervals, newInterval);
        for (int[] a: ans) {
            System.out.println(Arrays.toString(a));
        }
    }

    // O(N)
    private static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int start = newInterval[0];
        int end = newInterval[1];

        int i = 0;

        // insert non overlapping intervals from the beginning
        while (i < intervals.length && intervals[i][1] < start) {
            ans.add(intervals[i]);
            i++;
        }

        // merge overlapping intervals
        while (i < intervals.length && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }

        // add the merged interval
        ans.add(new int[] {start, end});

        // insert non overlapping intervals from the end
        while (i < intervals.length) {
            ans.add(intervals[i]);
            i++;
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}
