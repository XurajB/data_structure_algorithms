package problems.array;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = {5,1,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    // O(N), O(1)
    // Naive way is to compute all permutations and find permutation that is one step bigger than current (or the
    // one with least difference with current). That will be N!
    // Other way to look at: if the array is increasing all the way to the start starting from right, then that is the final permutation and can't go any further => (3,2,1,0)
    // for other cases like (1,2,3,0): what kind of rearrangement will produce next larger number? find the first non-increasing number from the right => 2
    // after that we need to swap this with just larger than itself (3) => 1,3,2,0 from the right. But this is not the next permutation, so we need to reverse
    // elements to the right from 2, because when we first travelled from right left, everything until 2 is sorted. Reversing this will produce lowest possible on the
    // right of 2.
    private static void nextPermutation(int[] nums) {
        // step 1
        int i = nums.length - 2;
        while (i >= 0 && nums[i+1] <= nums[i]) {
            i--;
        }

        // i = -1 if we didn't find smaller number than the right
        // which mean no next larger permutation is possible, in that case sort and return or reverse the whole array
        // otherwise step 2
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }

            // step 3
            swap(nums, i, j);
        }

        // step 4
        reverse(nums, i+1);
    }

    private static void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;

        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
