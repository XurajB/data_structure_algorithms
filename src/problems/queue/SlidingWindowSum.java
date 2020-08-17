package problems.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Find sum of each sliding window of size x.
 * Sliding Window Sums:
 * [2,3,5,6,2,1] => 10
 * [2,3,5,6,2,1] => 14
 * [2,3,5,6,2,1] => 13
 * [2,3,5,6,2,1] => 9
 */
public class SlidingWindowSum {
    public static void main(String[] args) {
        int[] nums = {2,3,5,6,2,1};
        System.out.println(Arrays.toString(slidingWindowSum2(nums, 3)));
    }

    private static int[] slidingWindowSum(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int sum = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // remove first then add
            if (queue.size() == k) {
                sum -= queue.poll();
            }
            queue.offer(nums[i]);
            sum += nums[i];
            if (queue.size() == 3) {
                ans[i-k+1] = sum;
            }
        }
        return ans;
    }

    private static int[] slidingWindowSum2(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        int sum = 0;

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i - k + 1 >= 0) {
                if (i - k >= 0) {
                    sum -= nums[i-k];
                }
                ans[index++] = sum;
            }
        }
        return ans;
    }
}
