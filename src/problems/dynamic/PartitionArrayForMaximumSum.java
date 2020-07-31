package problems.dynamic;

/**
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning.
 */
public class PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        int[] nums = {1,15,7,9,2,5,10};
        System.out.println(maxSumAfterPartitioning(nums, 3));
    }

    // dynamic programming solution works here because the problem has an optimal solution (largest sum) and overlapping sub-problems like
    // A = [9, 10, 2, 5] and K = 3
    // S[9] = 9 (i.e., memo[0] = 9)
    // S[9, 10] = 20 (i.e., memo[1] = 20)
    // S[9, 10, 2] = 30 (i.e., memo[2] = 30)..

    // O(nk)
    private static int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        int[] dp = new int[n];

        // largest sum ended in index i
        // largest sum ended in index i-1 + A[i]*1
        // largest sum ended in index i-2 + max(A[i], A[i-1]) * 2

        dp[0] = A[0];
        int max = A[0];
        for (int i = 1; i < K; i++) {
            max = Math.max(max, A[i]);
            dp[i] = max * (i + 1); // sum up to this point
        }

        for (int i = K; i < n; i++) { // i = sub array size
            int subArrayMax = A[i];
            for (int j = 1; j <= K; j++) {
                subArrayMax = Math.max(subArrayMax, A[i-j+1]);
                dp[i] = Math.max(dp[i], dp[i-j] + subArrayMax * j);
            }
        }

        return dp[n-1];
    }
}
