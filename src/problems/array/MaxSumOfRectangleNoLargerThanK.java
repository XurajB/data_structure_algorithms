package problems.array;

import java.util.TreeSet;

/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
 */
public class MaxSumOfRectangleNoLargerThanK {
    public static void main(String[] args) {
        int[][] matrix = {{1,0,1},{0,-2,3}};
        System.out.println(maxSumSubmatrix(matrix, 2));
    }

    // this question is similar to: MaximumSumRectangularSubmatrix
    // brute force would be to calculate every sum of every rectangle and keep max which is <= k. this will be O(N^4)
    // O(R^2 * CLogC). If R is much larger than C, then complexity can be O(C^2 * RLogR) by creating a row-sum array instead of column-sum array
    // space: O(col)
    private static int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < row; i++) {
            int[] colSum = new int[col];
            for (int j = i; j < row; j++) {
                for (int c = 0; c < col; c++) {
                    colSum[c] += matrix[j][c];
                }

                max = Math.max(max, findMax(colSum, k));
            }
        }

        return max;
    }

    // using kadane's algorithm in the col sum to find out max sum <= k
    private static int findMax(int[] colSum, int k) {
        int max = Integer.MIN_VALUE;
        int sum = 0; // sum of subarray from array element 0 to i
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        for (int value : colSum) {
            int t = sum + value; // rectangle sum
            sum = t;
            Integer gap = set.ceiling(sum - k); // ceiling = least element greater than or equals to sum-k or null
            if (gap != null) {
                max = Math.max(max, sum - gap); // computes smallest previously encountered subarray sum that is >= sum - k
            }
            set.add(t);
        }
        return max;
    }
}
