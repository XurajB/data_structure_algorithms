package leetcode;

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

    private static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int subarraySum2(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
