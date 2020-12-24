package problems.math;

import java.util.Arrays;

/**
 * Given two sparse matrices A and B, return the result of AB.
 * https://leetcode.com/problems/sparse-matrix-multiplication/
 */
public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] A = new int[][] {
                {1, 0, 0},
                {-1, 0, 3}
        };

        int[][] B = new int[][] {
                {7,0,0},
                {0,0,1},
                {0,0,1}
        };

        int[][] ans = multiply(A, B);
        for (int[] a: ans) {
            System.out.println(Arrays.toString(a));
        }
    }

    private static int[][] multiply(int[][] A, int[][] B) {
        // result size: A row. B col
        // A col should have same size as B row
        int n1 = A.length;
        int m1 = B[0].length;

        int[][] answer = new int[n1][m1];

        for (int i = 0; i < n1; i++) {
            int[] row = A[i];


            for (int j = 0; j < m1; j++) {
                int sum = 0;
                for (int k = 0; k < row.length; k++) {
                    sum = sum + row[k] * B[k][j];
                }
                answer[i][j] = sum;
            }
        }

        return answer;
    }
}
