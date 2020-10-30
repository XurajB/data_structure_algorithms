package problems.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
    }

    // O(N), O(N)
    private static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int count = 1;
        for (int num: nums) {
            if (!set.contains(num-1)) {
                int current = num;
                int tempCount = 1;

                while (set.contains(current+1)) {
                    current = current + 1;
                    tempCount++;
                }
                count = Math.max(tempCount, count);
            }

        }
        return count;
    }

    // O(nlogn)
    private static int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int tempMax = 1;
        int max = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (Math.abs(nums[i] - nums[i-1]) == 1) {
                tempMax++;
            } else if (nums[i] != nums[i-1]) {
                max = Math.max(tempMax, max);
                tempMax = 1;
            }
        }

        return Math.max(tempMax, max);
    }
}
