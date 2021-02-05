package problems.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 */
public class MaximumSizeSubarraySumEqualsK {
    public static void main(String[] args) {
        System.out.println(maxSubArrayLen(new int[] {1,-1,5,-2,3}, 3));
    }
    // O(N)
    private static int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum-k)) {
                max = Math.max(max, i - map.get(sum-k));
            }
            if (!map.containsKey(sum)) { // maximize length
                map.put(sum, i);
            }
        }
        return max;
    }
}
