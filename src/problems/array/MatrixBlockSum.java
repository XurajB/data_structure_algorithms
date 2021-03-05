package problems.array;

import java.util.Arrays;

/**
 * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of
 * all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 */
public class MatrixBlockSum {
    public static void main(String[] args) {
        int[][] mat = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(Arrays.deepToString(matrixBlockSum(mat, 1)));
    }

    // O(r*c)
    private static int[][] matrixBlockSum(int[][] mat, int k) {
        // we are creating a square box around i,j where the total side length is 2*k + 1 (including itself in the center)
        // to calculate sum of the elements in that box: using prefix sum: sum up to lower right corner - sum up to lower left - sum up to upper right + sum up to upper left (common)
        // the square box might fall outside of original mat, so check limits

        int m = mat.length;
        int n = mat[0].length;

        int[][] prefix = new int[m+1][n+1];
        int[][] ans = new int[m][n];

        // prefix sum
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + mat[i-1][j-1];
            }
        }

        // calculate block sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r1 = Math.max(0, i - k) + 1;
                int c1 = Math.max(0, j - k) + 1;
                int r2 = Math.min(m, i + k + 1);
                int c2 = Math.min(n, j + k + 1);

                ans[i][j] = prefix[r2][c2] - prefix[r1-1][c2] - prefix[r2][c1 - 1] + prefix[r1-1][c1-1];
            }
        }

        return ans;
    }
}
