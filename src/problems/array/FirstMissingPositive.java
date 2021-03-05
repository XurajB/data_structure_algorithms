package problems.array;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * Your algorithm should run in O(n) time and uses constant extra space.
 * https://leetcode.com/problems/first-missing-positive/
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        System.out.println(firstMissingPositive(nums));
    }

    private static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 1;
        }

        boolean hasOne = false;

        // if the array do not have a 1, return 1
        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }

        if (!hasOne) {
            return 1;
        }

        // has one and size is 1
        if (n == 1) {
            return 2;
        }

        // we replace any number <= 0 and > n with 1 since our answer is not one of them
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }

        // Use index as a hash key and number sign as a presence detector.
        // For example, if nums[1] is negative that means that number `1`
        // is present in the array.
        // If nums[2] is positive - number 2 is missing.
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            if (a == n) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[a] = -Math.abs(nums[a]); // will not run into OOB because we changed >n to 1s. Abs so we take care of dupes
            }
        }

        // the index of first positive number is missing positive
        // we start at 1, because no 0 left
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        // in cases like [2, 1]
        return (nums[0] > 0) ? n : n+1;
    }
}
