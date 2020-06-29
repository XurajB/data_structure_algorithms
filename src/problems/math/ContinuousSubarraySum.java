package problems.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a
 * continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 */
public class ContinuousSubarraySum {
    public static void main(String[] args) {
        int[] nums = {26,2,4,6,7};
        System.out.println(checkSubarraySum(nums, 6));
    }

    // (sum1 - sum2) % k = 0, means the subarray sum between these indices are multiple of k
    // to check if the size of the subarray >= k, we also store indices
    // O(N), O(N)
    private static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // sum%k, index
        map.put(0, -1); // special case when (because 0 index)

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) >= 2) { // subarray size is >= 2
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }
}
