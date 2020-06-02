package problems.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find the contiguous subarray within an array, A of length N which has the largest sum.
 * https://www.interviewbit.com/problems/max-sum-contiguous-subarray/
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaxSubArray {
    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(5,-3,-5));
        System.out.println(maxSubArray2(A));
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
