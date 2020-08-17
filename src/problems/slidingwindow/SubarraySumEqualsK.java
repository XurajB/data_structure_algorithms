package problems.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of positive integers, find the contiguous subarray that sums to a given number X.
 * For example, input = [1,2,3,5,2] and X=8, Result = [2,3] // indexes from start to end
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = {1,2,35,5,2};
        System.out.println(Arrays.toString(subarraySumK(nums, 7)));
    }

    private static int subarraySum2(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // sum, number of occurrences of sum
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

    // assuming only positive values in input
    // if positive and negative - check ^^
    private static int[] subarraySumK(int[] nums, int k) {
        int start = 0;
        int end = 0;

        int sum = 0;
        while (end < nums.length) {
            if (sum > k) {
                sum -= nums[start];
                start++;
            } else if (sum < k) {
                sum += nums[end];
                end++;
            } else {
                return new int[] {start, end - 1};
            }
        }

        // for the last element
        if (sum == k) {
            return new int[] {start, end - 1};
        }
        return new int[]{};
    }
}
