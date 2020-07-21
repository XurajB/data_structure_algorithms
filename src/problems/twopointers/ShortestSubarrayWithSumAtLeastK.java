package problems.twopointers;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 * If there is no non-empty subarray with sum at least K, return -1.
 */
public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        int[] nums = {2,-1,2};
        System.out.println(shortestSubarray(nums, 3));
    }

    // Similar to: #MinimumSizeSubarraySum but with negative nums
    // O(N)
    private static int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int ans = Integer.MAX_VALUE;

        int[] prefixSum = new int[n+1];
        for (int i = 0; i < n; i++) {
            prefixSum[i+1] = prefixSum[i] + A[i];
        }

        Deque<Integer> deque = new ArrayDeque<>(); // stores index
        for (int i = 0; i < n+1; i++) {
            // check if the sum >= k
            while (deque.size() > 0 && prefixSum[i] - prefixSum[deque.peekFirst()] >= K) {
                ans = Math.min(ans, i - deque.pollFirst());
            }
            // to keep our deque increasing
            while (deque.size() > 0 && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }
}
