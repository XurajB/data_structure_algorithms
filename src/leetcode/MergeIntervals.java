package leetcode;

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
        for (int i = 1; i < intervals.length; i++) {
            if (prev[1] >= intervals[i][0]) {
                prev = new int[] {prev[0], Math.max(intervals[i][1], prev[1])};
                if (answer.size() > 0) {
                    answer.set(answer.size() - 1,prev);
                } else {
                    answer.add(prev);
                }
            } else {
                answer.add(intervals[i]);
                prev = intervals[i];
            }
        }

        int[][] ans = new int[answer.size()][2];
        int i = 0;
        for (int[] interval: answer) {
            ans[i] = interval;
            i++;
        }

        return ans;
    }
}
