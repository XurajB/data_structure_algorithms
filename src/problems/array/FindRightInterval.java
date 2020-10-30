package problems.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is
 * bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 *
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i.
 * If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.
 */
public class FindRightInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1,2}};
        System.out.println(Arrays.toString(findRightInterval(intervals)));
    }

    // O(nlogn)
    private static int[] findRightInterval(int[][] intervals) {
        int[] ans = new int[intervals.length];

        if (intervals.length == 1) {
            ans[0] = -1;
            return ans;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>(); // start, index
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }

        for (int i = 0; i < intervals.length; i++) {
            Integer key = map.ceilingKey(intervals[i][1]); // equal or higher than end. binary search
            if (key != null) {
                ans[i] = map.get(key);
            } else {
                ans[i] = -1;
            }
        }

        return ans;
    }

    // exactly same as above but we are manually doing sorting and searching
    private static int[] findRightInterval2(int[][] intervals) {
        int[] ans = new int[intervals.length];

        if (intervals.length == 1) {
            ans[0] = -1;
            return ans;
        }

        Map<Integer, Integer> map = new HashMap<>(); // start, index
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }

        // sort by
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < intervals.length; i++) {
            // binary search
            int index = search(intervals, intervals[i][1], 0, intervals.length);
            ans[i] = index;
        }

        return ans;
    }

    private static int search(int[][] intervals, int key, int start, int end) {
        int lo = start;
        int high = end - 1;

        int ans = -1;
        while (lo <= high) {
            int mid = lo + (high - lo) >> 1;

            if (intervals[mid][0] == key) {
                ans = mid;
                break;
            } else if (intervals[mid][0] < key) {
                lo = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // if we didn't find the key, look for start that's just greater
        if ((ans == -1) && (lo < intervals.length) && intervals[lo][0] > key) {
            ans = lo;
        }

        return ans;
    }
}
