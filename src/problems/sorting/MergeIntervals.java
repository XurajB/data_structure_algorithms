package problems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] input = {
                {1,3},
                {8,10},
                {2,6},
                {15,18}
        };
        int[][] answer = merge(input);
        for (int[] i: answer) {
            System.out.println(Arrays.toString(i));
        }
    }

    private static int[][] merge(int[][] intervals) {

        // 1. sort the intervals using start
        // 2. iterate invervals and check if current end is greater than next start
        // 3. if yes - merge, if no add to the list

        if (intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        ArrayList<int[]> answer = new ArrayList<>();
        int[] prev = intervals[0];
        answer.add(prev);
        for (int[] interval: intervals) {
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(interval[1], prev[1]); // this will update prev inside the list
            } else {
                prev = interval;
                answer.add(prev);
            }
        }

        return answer.toArray(new int[answer.size()][]);
    }
}
