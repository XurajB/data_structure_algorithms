package problems.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 * If there is no non-empty subarray with sum at least K, return -1.
 */
public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        int[] nums = {2,-1, 3, 2};
        System.out.println(shortestSubarray(nums, 4));
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
        for (int i = 0; i <= n; i++) {
            // check if the sum >= k
            while (deque.size() > 0 && prefixSum[i] - prefixSum[deque.peek()] >= K) {
                ans = Math.min(ans, i - deque.poll()); // one we inserted first
            }
            // to keep our deque increasing
            // to keep the ans minimum, we have prefixsum which is smaller than last item in deque so we have greater chance to minimize ans
            while (deque.size() > 0 && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast(); // one we inserted last
            }
            deque.offer(i);
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    // N^2
    public int shortestSubarray2(int[] A, int K) {
        int n = A.length;
        int ans = Integer.MAX_VALUE;
        int[] prefixSum = new int[n+1];
        for (int i = 0; i < n; i++) {
            prefixSum[i+1] = prefixSum[i] + A[i];
        }
        for (int i = 0; i <= A.length; i++) {
            for (int j = i; j <= A.length; j++) {
                if (prefixSum[j] - prefixSum[i] >= K) {
                    ans = Math.min(ans, j-i);
                    break;
                }

            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
