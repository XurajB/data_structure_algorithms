package problems.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    // sparse matrix compression
    private int[][] multiply2(int[][] A, int[][] B) {
        if (A.length == 0 || A[0].length == 0
                || B .length == 0 || B[0].length == 0) {
            return new int[][] {};
        }
        int r = A.length;
        int c = A[0].length;
        int cB = B[0].length;

        int[][] ans = new int[r][cB];
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>(); // row, (col, val)

        // compress
        for (int i = 0; i < r; i++) {
            Map<Integer, Integer> temp = new HashMap<>();
            for (int j = 0; j < c; j++) {
                if (A[i][j] != 0) {
                    temp.put(j, A[i][j]);
                }
            }
            map.put(i, temp);
        }

        // multiply, similar to above but use map for matrix A
        for (int key1: map.keySet()) {
            for (int i = 0; i < cB; i++) {
                for (int key2: map.get(key1).keySet()) {
                    ans[key1][i] += map.get(key1).get(key2) * B[key2][i];
                }
            }
        }

        return ans;
    }


    ////////
    // if sparse matrix
    private int[][] multiply3(int[][] A, int[][] B) {
        int rA = A.length;
        int cA = A[0].length;
        int cB = B[0].length;

        int[][] ans = new int[rA][cB];

        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cA; j++) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < cB; k++) {
                        if (B[j][k] != 0) {
                            ans[i][k] += A[i][j] * B[j][k];
                        }
                    }
                }
            }
        }
        return ans;
    }
}
