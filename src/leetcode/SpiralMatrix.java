package leetcode;

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

        int row = matrix.length;
        int col = matrix[0].length;

        int r = 0;
        int c = 0;

        int total = row * col;

        List<Integer> result = new ArrayList<>();

        boolean upDownDirection = false;

        for (int current = 0; current < total; current++) {

            System.out.println("[" + r + ", " + c + "]");
            result.add(matrix[r][c]);


            if (!upDownDirection) {

                if (c == col - 1) {
                    r--;
                    row--;
                    upDownDirection = true;
                } else if (!upDownDirection) {
                    c++;
                }
            }

            if (r < row) {

                if (r == row - 1) {
                    c--;
                    col--;
                    upDownDirection = false;
                } else if (upDownDirection) {
                    r++;
                }
            }

        }

        return result;

    }
}
