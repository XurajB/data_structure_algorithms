package problems.array;

import java.util.Arrays;

/**
 * Given an array of positive integers arr (not necessarily distinct), return the lexicographically largest permutation that is smaller than arr,
 * that can be made with exactly one swap (A swap exchanges the positions of two numbers arr[i] and arr[j]). If it cannot be done, then return the same array.
 */
public class PreviousPermutation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(prevPerm(new int[] {3,1,1,3})));
    }

    private static int[] prevPerm(int[] nums) {
        // step 1: from the right, find max i that is larger than i-1
        // step 2: from the right, find max j (min if equal j), that is smaller than i
        // step 3: swap
        int n = nums.length;
        if (n <= 1) {
            return nums;
        }

        int i = n - 2;
        while (i >= 0 && nums[i] <= nums[i+1]) {
            i--;
        }

        // already sorted
        if (i == -1) {
            return nums;
        }

        int j = n - 1;
        while (nums[j] == nums[j-1] || nums[j] >= nums[i]) { // skip equal number. ex 3,1,1,3 we want i=1  to make it next smaller
            j--;
        }

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }
}
