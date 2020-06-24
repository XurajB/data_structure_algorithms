package problems.array;

/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 */
public class CountSquares {
    public static void main(String[] args) {
        int[][] A = {
                {0,1,1,1},
                {1,1,1,1},
                {0,1,1,1}
        };
        System.out.println(countSquares(A));
    }

    // O(m*n)
    // keep 1s on first row and column (but count on res)
    // get min from left, top, left top + 1
    // sum it
    private static int countSquares(int[][] A) {
        int res = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] > 0 && i > 0 && j > 0) {
                    A[i][j] = Math.min(A[i - 1][j - 1], Math.min(A[i - 1][j], A[i][j - 1])) + 1;
                }
                res += A[i][j];
            }
        }
        return res;
    }
}
