package problems.array;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {2,1,-1,-4};
        System.out.println(maxProduct(nums));
    }

    // O(N), O(N)
    private static int maxProduct(int[] nums) {
        // since we also have -ve numbers
        // this will introduce the situation where two -ve numbers can create max product
        // so we will need to keep track of both min and max
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];

        max[0] = nums[0];
        min[0] = nums[0];

        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {

            int curMax = Math.max(nums[i], Math.max(max[i-1] * nums[i], min[i-1] * nums[i]));
            int curMin = Math.min(nums[i], Math.min(max[i-1] * nums[i], min[i-1] * nums[i]));

            max[i] = curMax;
            min[i] = curMin;

            ans = Math.max(ans, curMax);

        }

        return ans;
    }

    // O(N), O(1)
    private static int maxProduct2(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);

            ans = Math.max(ans, max);
        }

        return ans;
    }

    // O(N), O(1)
    // Calculate from both sides. If the number of -ve is even, left to right will give the answer. if odd, right to left will give the answer
    private static int maxProduct3(int[] nums) {
        int prod = 1;
        int ans = Integer.MIN_VALUE;

        for (int num : nums) {
            prod = prod * num;
            ans = Math.max(ans, prod);
            if (prod == 0) {
                prod = 1;
            }
        }

        prod = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            prod = prod * nums[i];
            ans = Math.max(ans, prod);
            if (prod == 0) {
                prod = 1;
            }
        }

        return ans;
    }
}
