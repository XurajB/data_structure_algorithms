package leetcode;

/**
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 * https://leetcode.com/problems/missing-element-in-sorted-array/
 */
public class MissingElementInSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[] {4, 7, 9, 10};
        System.out.println(missingElement(nums, 5));
    }

    private static int missingElement(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] + k;
        }

        int reminder = k;
        for (int i = 1; i < n; i++) {
            int diff = nums[i] - nums[i-1] - 1; // how many middle elements
            if (diff < reminder) {
                reminder = reminder - diff;
            } else {
                // we already passed the kth element
                return nums[i-1] + reminder;
            }
        }

        // last case
        return nums[n - 1] + reminder;
    }
}
