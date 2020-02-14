package algorithms.interviewbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find the contiguous subarray within an array, A of length N which has the largest sum.
 * https://www.interviewbit.com/problems/max-sum-contiguous-subarray/
 */
public class MaxSubArray {
    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4));
        System.out.println(maxSubArray(A));
        System.out.println(maxSubArray2(A));
    }

    // time: O(n^2)
    public static int maxSubArray(final List<Integer> A) {
        int max = Integer.MIN_VALUE;
        int localSum;

        for (int i = 0; i < A.size(); i++) {
            localSum = 0;
            for (int j = i; j < A.size(); j++) {
                localSum += A.get(j);
                max = Math.max(max, localSum);

            }
        }

        return max;
    }

    // time: O(n)
    public static int maxSubArray2(final List<Integer> A) {
        int max = A.get(0);
        int localSum = A.get(0);

        for (int i = 1; i < A.size(); i++) {
            localSum += A.get(i);
            localSum = Math.max(localSum, A.get(i));
            max = Math.max(max, localSum);
        }

        return max;
    }
}
