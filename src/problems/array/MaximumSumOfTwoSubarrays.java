package problems.array;

/**
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.
 * (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 */
public class MaximumSumOfTwoSubarrays {
    public static void main(String[] args) {
        int[] A = {0,6,5,2,2,5,1,9,4};
        System.out.println(maxSumTwoNoOverlap(A, 1, 2));
    }

    // this problem is similar to BestTimeToSellStock3
    private static int maxSumTwoNoOverlap(int[] A, int L, int M) {
        // L and M can be at left or right
        // so we need to calculate max from both sides
        return Math.max(calculate(A, L, M), calculate(A, M, L));
    }

    private static int calculate(int[] A, int L, int M) {
        int len = A.length;

        // calculate sum so far from the left
        // similar to max so far in BestTimeToSellStock3
        int[] prefixSum = new int[len];
        prefixSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSum[i] = prefixSum[i-1] + A[i];
        }

        // calculate sum of L size from left
        int[] leftSum = new int[len];
        leftSum[L-1] = prefixSum[L-1];
        for (int i = L; i < len; i++) {
            leftSum[i] = Math.max(leftSum[i-1], prefixSum[i] - prefixSum[i-L]); // i - (i-l) is sum between i and i-l
        }

        // calculate sum so far from the right
        int[] suffixSum = new int[len];
        suffixSum[len-1] = A[len-1];
        for (int i = len - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i+1] + A[i];
        }

        // calculate sum of M size from right
        int[] rightSum = new int[len];
        rightSum[len-M] = suffixSum[len-M];
        for (int i = len - M - 1; i >= 0; i--) {
            rightSum[i] = Math.max(rightSum[i+1], suffixSum[i] - suffixSum[i+M]);
        }

        // now we have data for max with Length L from left and max sum with length M from the right
        // i+1 so they don't overlap
        int ans = Integer.MIN_VALUE;
        for(int i = L-1; i <= len-M-1; i++) {
            ans = Math.max(leftSum[i] + rightSum[i+1], ans); // NOTE: i+1
        }

        return ans;
    }
}
