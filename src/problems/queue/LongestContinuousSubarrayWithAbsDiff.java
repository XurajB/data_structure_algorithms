package problems.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers nums and an integer limit,
 * return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 */
public class LongestContinuousSubarrayWithAbsDiff {
    public static void main(String[] args) {
        int[] nums = {8,2,4,7};
        System.out.println(longestSubarray(nums, 4));
    }

    // O(N^2)
    // absolute difference between any two elements of this subarray is less than or equal to limit basically means -
    // difference between max and min of that subarray is less <= limit
    // we need to do this because the diff should be valid between any two elements
    public int longestSubarray2(int[] nums, int limit) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int j = i; j < nums.length; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);

                if (max - min <= limit) {
                    count = Math.max(count, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return count;
    }

    private static int longestSubarray(int[] nums, int limit) {
        // to keep track of max and min of a window, recall SlidingWindowMaximum
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();

        int ans = 1;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            // update max with new right pointer
            while (!maxDeque.isEmpty() && nums[right] > maxDeque.peekLast()) {
                maxDeque.pollLast();
            }
            maxDeque.offer(nums[right]);

            // update min with new right pointer
            while (!minDeque.isEmpty() && nums[right] < minDeque.peekLast()) {
                minDeque.pollLast();
            }
            minDeque.offer(nums[right]);

            // shrink the left pointer if limit exceed
            // clean the max, min element if our range goes out of limit
            while (maxDeque.peek() - minDeque.peek() > limit) {
                if (maxDeque.peek() == nums[left]) {
                    maxDeque.poll();
                }
                if (minDeque.peek() == nums[left]) {
                    minDeque.poll();
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
