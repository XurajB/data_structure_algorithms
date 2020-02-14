package algorithms.leetcode;

import java.util.Arrays;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * Note: Please solve it without division and in O(n).
 * https://leetcode.com/problems/product-of-array-except-self/
 */
public class ProductExceptSelf {

    // without division constraint - it would be an easy solution. Divide total product by that number
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
        System.out.println(Arrays.toString(productExceptSelf2(nums)));
    }

    // time: O(N), space: O(N)
    private static int[] productExceptSelf(int[] nums) {
        // find left product and right product and multiply
        int[] leftProducts = new int[nums.length];
        int[] rightProducts = new int[nums.length];

        // leftProducts[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so leftProducts[0] would be 1
        leftProducts[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProducts[i] = nums[i-1] * leftProducts[i-1];
        }

        rightProducts[nums.length - 1] = 1;
        for (int i = nums.length - 2; i>= 0; i--) {
            // Simply multiplying it with nums[i + 1] would give the product of all
            // elements to the right of index 'i'
            rightProducts[i] = nums[i+1] * rightProducts[i + 1];
        }

        int[] answer = new int[nums.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = leftProducts[i] * rightProducts[i];
        }

        return answer;
    }

    // time: O(N), space: O(1) - not considering answer array
    private static int[] productExceptSelf2(int[] nums) {
        // same theory
        // use answer array for left products
        int[] answer = new int[nums.length];

        answer[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i-1] * nums[i-1];
        }

        // refill answer, while calculating left product on the fly
        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] = rightProduct * answer[i];
            rightProduct *= nums[i];
        }

        return answer;
    }
}
