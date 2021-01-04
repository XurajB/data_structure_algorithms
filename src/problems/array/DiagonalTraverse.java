package problems.array;

/**
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order
 */
public class DiagonalTraverse {

    public static void main(String[] args) {

    }

    // O(M * N)
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[] {};
        }

        int row = 0;
        int col = 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int[] ans = new int[m * n];

        for (int i = 0; i < m * n; i++) {
            ans[i] = matrix[row][col];
            // when row+col is even, direction is up
            if ((row + col) % 2 == 0) {
                if (col == n - 1) {
                    // if last col, go down
                    row++;
                } else if (row == 0) {
                    // if not last col but if first row, go right
                    col++;
                } else {
                    // if not last col and not first row, go up and right
                    col++;
                    row--;
                }
            } else {
                if (row == m - 1) {
                    // if last row, go right
                    col++;
                } else if (col == 0) {
                    // if not last row but if first col, go down
                    row++;
                } else {
                    // other cases, down and left
                    row++;
                    col--;
                }
            }
        }

        return ans;
    }
}
