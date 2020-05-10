package problems.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * https://leetcode.com/problems/spiral-matrix/
 */
public class SpiralMatrix {
    public static void main(String[] args) {

        int[][] num = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(spiralOrder(num).toString());
    }

    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        int rowStart = 0;
        int columnStart = 0;
        int rowEnd = matrix.length - 1;
        int columnEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && columnStart <= columnEnd) {
            // travel left to right
            for (int i = columnStart; i <= columnEnd; i++) {
                ans.add(matrix[rowStart][i]);
            }
            rowStart++;

            // travel from top to bottom
            for (int i = rowStart; i <= rowEnd; i++) {
                ans.add(matrix[i][columnEnd]);
            }
            columnEnd--;

            // travel from right to left
            // to prevent dupes (so we don't traverse back to visited row item)
            if (rowStart <= rowEnd) {
                for (int i = columnEnd; i >= columnStart; i--) {
                    ans.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            // travel from bottom to top
            if (columnStart <= columnEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    ans.add(matrix[i][columnStart]);
                }
            }
            columnStart++;
        }

        return ans;
    }
}
