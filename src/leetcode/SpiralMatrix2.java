package leetcode;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
 * https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralMatrix2 {

    // O(n*n), O(1)
    public int[][] generateMatrix(int n) {
        int[][] answer = new int[n][n];

        int startRow = 0;
        int startColumn = 0;
        int endRow = n-1;
        int endColumn = n-1;

        int count = 1;
        while (startRow <= endRow && startColumn <= endColumn) {

            for (int i = startColumn; i <= endColumn; i++) {
                answer[startRow][i] = count++;
            }
            startRow++;

            for (int i = startRow; i <= endRow; i++) {
                answer[i][endColumn] = count++;
            }
            endColumn--;

            if (startRow <= endRow) {
                for (int i = endColumn; i >= startColumn; i--) {
                    answer[endRow][i] = count++;
                }
            }
            endRow--;

            if (startColumn <= endColumn) {
                for (int i = endRow; i >= startRow; i--) {
                    answer[i][startColumn] = count++;
                }
            }
            startColumn++;
        }

        return answer;
    }
}
