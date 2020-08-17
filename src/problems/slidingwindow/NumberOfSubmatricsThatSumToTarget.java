package problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 */
public class NumberOfSubmatricsThatSumToTarget {
    public static void main(String[] args) {
        int[][] nums = {{1,-1},{-1,1}};
        System.out.println(numSubmatrixSumTarget(nums, 0));
    }

    // O(r.c^2)
    private static int numSubmatrixSumTarget(int[][] A, int target) {
        int ans = 0;
        int row = A.length;
        int col = A[0].length;

        // calculate prefix sum for each row
        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                A[i][j] += A[i][j-1];
            }
        }
        // for every possible range between two columns, accumulate the prefix sum of submatrices that can be formed between
        // these two columns by adding the sum of values between these two columns for every row
        for (int i = 0; i < col; i++) {
            for (int j = i; j < col; j++) {
                // subarray sum equal k
                Map<Integer, Integer> counter = new HashMap<>();
                counter.put(0, 1);
                int cur = 0;

                for (int k = 0; k < row; k++) {
                    if (i > 0) {
                        cur += A[k][j] - A[k][i-1];
                    } else {
                        cur += A[k][j];
                    }

                    ans += counter.getOrDefault(cur - target, 0);
                    counter.put(cur, counter.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return ans;
    }
}
