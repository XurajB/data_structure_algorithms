package problems.array;

/**
 * Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C
 */
public class MaxSumCircularSubarray {

    public static void main(String[] args) {
        int[] A = {5, -2, 5};
        System.out.println(maxSubarraySum(A));
    }

    private static int maxSubarraySum(int[] A) {
        // there are two cases:
        // max subarry is not circular (in the middle of A)
        // max subarry is circular

        int minSum = Integer.MAX_VALUE;
        int maxSum = Integer.MIN_VALUE;
        int localMin = 0;
        int localMax = 0;
        int total = 0;

        for (int a: A) {
            localMax = Math.max(localMax + a, localMax);
            localMin = Math.min(localMin + a, localMin);
            maxSum = Math.max(localMax, maxSum);
            minSum = Math.min(localMin, minSum);
            total += a;
        }

        // in case where all elements are -ve, maxSum will be -ve
        if (maxSum > 0) {
            return Math.max(maxSum, total - minSum);
        }
        return maxSum;
    }
}
