package problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 * Return a sorted list of intervals after all such removals.
 */
public class RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();

        for (int[] interval: intervals) {
            // check for non overlap. two cases -
            if (interval[1] <= toBeRemoved[0] || interval[0] >= toBeRemoved[1]) {
                ans.add(Arrays.asList(interval[0], interval[1]));
            } else {
                // left end no overlap
                if (interval[0] < toBeRemoved[0]) {
                    ans.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
                // right end no overlap
                if (interval[1] > toBeRemoved[1]) {
                    ans.add(Arrays.asList(toBeRemoved[1], interval[1]));
                }
            }
        }
        return ans;
    }
}
