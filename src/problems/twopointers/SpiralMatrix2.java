package problems.twopointers;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
 * https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralMatrix2 {

    // O(n*n), O(1)
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];

        int startRow = 0;
        int startCol = 0;
        int endRow = n-1;
        int endCol = n-1;

        int count = 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int i = startCol; i <= endCol; i++) {
                ans[startRow][i] = count++;
            }
            startRow++;

            for (int i = startRow; i <= endRow; i++) {
                ans[i][endCol] = count++;
            }
            endCol--;

            for (int i = endCol; i >= startCol; i--) {
                ans[endRow][i] = count++;
            }
            endRow--;

            for (int i = endRow; i >= startRow; i--) {
                ans[i][startCol] = count++;
            }
            startCol++;
        }
        return ans;
    }
}
