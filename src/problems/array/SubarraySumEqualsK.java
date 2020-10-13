package problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = {3, 4, 7, 2, -3, 1, 4, 2};
        System.out.println(subarraySum(nums, 7));
        System.out.println(subarraySum2(nums, 7));
    }

    // n^2
    private static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                    // can't break here, because of 0,0,0,0,0 case
                }
            }
        }
        return count;
    }

    private static int subarraySum2(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> preSum = new HashMap<>(); // sum, number of occurrences of sum
        preSum.put(0, 1); // think of 1,1,1. k =2. sum: 0, 1, 2, 3. 2-0, 3-1

        for (int num : nums) {
            sum += num;
            if (preSum.containsKey(sum - k)) {
                count += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
