package problems.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, both -ve and +ve, find a contiguous subarray that sums to 0.
 */
public class SubarraySumEqualsZero {
    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4, 2, 3, -1, 2};
        System.out.println(Arrays.toString(subarraySumZero(nums)));
    }

    // [-1, 2, 1, -4, 2, 3, -1, 2]
    // if we calculate prefix sum
    // [-1, 1, 2, -2, 0, 3,  2, 4]
    // properties: if we encounter 0, return [0,end]
    // if two sums are same: sum in between will give 0, return [start + 1, end]
    private static int[] subarraySumZero(int[] nums) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // sum, index
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == 0) {
                return new int[] {0, i};
            }
            if (map.containsKey(sum)) {
                return new int[] {map.get(sum), i};
            }
            map.put(sum, i);
        }

        return null;
    }
}
