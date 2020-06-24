package problems.array;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        System.out.println(maxProduct(nums));
    }

    // O(N), O(N)
    private static int maxProduct(int[] nums) {
        // since we also have -ve numbers
        // this will introduce the situation where two -ve numbers can create max product
        // so we will need to keep track of both min and max
        int[] currentMin = new int[nums.length];
        int[] currentMax = new int[nums.length];

        currentMax[0] = nums[0];
        currentMin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // multiplying by -ve number makes big number smaller and small number bigger
            int currentMaxSoFar = Math.max(currentMax[i-1] * nums[i], currentMin[i-1] * nums[i]); // currentMin * nums can produce positive result (if both are -ve)
            int currentMinSoFar = Math.min(currentMax[i-1] * nums[i], currentMin[i-1] * nums[i]);

            currentMax[i] = Math.max(nums[i], currentMaxSoFar);
            currentMin[i] = Math.min(nums[i], currentMinSoFar);
        }

        int ans = Integer.MIN_VALUE;
        for (int num: currentMax) {
            ans = Math.max(ans, num);
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
}
