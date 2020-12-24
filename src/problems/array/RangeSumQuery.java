package problems.array;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 */
public class RangeSumQuery {
    int[][] preSum;
    public RangeSumQuery(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        preSum = new int[m][n];
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
                preSum[i][j] = sum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ans = 0;
        for (int i = row1; i <= row2; i++) {
            if (col1 == 0) {
                ans += preSum[i][col2];
            } else {
                ans += preSum[i][col2] - preSum[i][col1-1];
            }
        }
        return ans;
    }
}
