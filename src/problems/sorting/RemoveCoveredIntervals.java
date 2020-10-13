package problems.sorting;

import java.util.Arrays;

/**
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 * After doing so, return the number of remaining intervals.
 */
public class RemoveCoveredIntervals {
    public static void main(String[] args) {
    }

    private static int removeCoveredIntervals(int[][] intervals) {
        // sort ascending by start, then descending by end if equal
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int end = 0;
        int ans = 0;
        for (int[] interval: intervals) {
            int currentEnd = interval[1];
            if (end < currentEnd) {
                ans++;
                end = currentEnd;
            }
        }

        return ans;
    }
}
