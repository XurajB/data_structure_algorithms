package problems.array;

/**
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 *
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 *
 * Return the length of a maximum size turbulent subarray of A.
 * https://leetcode.com/problems/longest-turbulent-subarray/
 */
public class LongestTurbulentSubarray {
    public static void main(String[] args) {
        int[] nums = {9,4,2,10,7,8,8,1,9};
        System.out.println(maxTurbulenceSize(nums));
    }

    // for each A[i]
    // inc - the length of current valid sequence which ends with two increasing numbers
    // dec - the length of current valid sequence which ends with two decreasing numbers
    // O(N), O(1)
    private static int maxTurbulenceSize(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int inc = 1;
        int dec = 1;
        int total = 1;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i-1]) {
                inc = dec + 1; // last time it was dec
                dec = 1;
            } else if (A[i] < A[i-1]) {
                dec = inc + 1;
                inc = 1;
            } else {
                dec = 1;
                inc = 1;
            }
            total = Math.max(total, Math.max(inc, dec));
        }

        return total;
    }
}
