package problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {
    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[] {0,1}));
    }

    // O(N), O(N)
    private static int findMaxLength(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // sum == 0 means equals num of 0 and 1

        int sum = 0; // sum == 0 means, equal num of 1 and 0
        for (int i = 0; i < nums.length; i++) {
            // if we encounter same sum next time, then anything in between has equal 0 and 1. coz sum[j]-sum[i] = 0;
            sum += nums[i] == 1 ? 1 : -1; // NOTE here

            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
