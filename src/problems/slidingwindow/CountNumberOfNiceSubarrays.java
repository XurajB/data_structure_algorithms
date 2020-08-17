package problems.slidingwindow;

/**
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 * Return the number of nice sub-arrays.
 */
public class CountNumberOfNiceSubarrays {
    public static void main(String[] args) {
        int[] nums = {2,2,2,1,2,2,1,2,2,2};
        System.out.println(numberOfSubarrays(nums, 3));
    }

    private static int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k-1);
    }

    private static int atMost(int[] nums, int k) {
        int ans = 0;
        int start = 0;
        int end = 0;

        while (end < nums.length) {
            k -= nums[end] % 2;
            while (k < 0) {
                k += nums[start] % 2;
                start++;
            }
            ans += end - start + 1;
            end++;
        }
        return ans;
    }
}
