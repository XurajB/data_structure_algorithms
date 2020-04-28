package leetcode;

import java.util.Arrays;

/**
 * Given an array A of integers and integer K, return the maximum S such that there A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.
 * https://leetcode.com/problems/two-sum-less-than-k/
 */
public class TwoSumLessThanK {
    public static void main(String[] args) {
        int[] a = new int[] {34,23,1,24,75,33,54,8};
        System.out.println(towSumLessThanK(a, 60));
    }

    // O(nLogn), O(1) - sorting might need space O(n)
    private static int towSumLessThanK(int[] A, int K) {
        int maxSoFar = -1;
        int i = 0;
        int j = A.length - 1;
        Arrays.sort(A);
        while (i < j) {
            int sum = A[i] + A[j];
            if (sum < K) {
                maxSoFar = Math.max(sum, maxSoFar);
                i++;
            } else {
                j--;
            }
        }
        return maxSoFar;
    }
}
