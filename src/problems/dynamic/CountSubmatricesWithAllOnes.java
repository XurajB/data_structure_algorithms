package problems.dynamic;

import java.util.Stack;

/**
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
 */
public class CountSubmatricesWithAllOnes {
    public static void main(String[] args) {
        int[][] mat = {
                {1,0,1},
                {1,1,0},
                {1,1,0}
        };
        System.out.println(numSubmat(mat));
    }

    // m * n * n
    private static int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int ans = 0;
        int[] height = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1; // height of histogram
                int min = height[j];

                // if min == 0, we don't go through this loop
                // we need min: because for overall matrix 1 1 0, we are at index 1: we have 1 matrix 1x2, from right to left. at index 2 we have 0 matrix from right to left
                for (int k = j; k >= 0 && min > 0; k--) {
                    min = Math.min(min, height[k]);
                    ans += min;
                }

            }
        }

        return ans;
    }

    // O(m * n)
    // count area
    private static int numSubmat2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] height = new int[n];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            Stack<int[]> stack = new Stack<>(); // index, sum
            for (int j = 0; j < n; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
                int sum = 0;
                while (!stack.isEmpty() && height[stack.peek()[0]] >= height[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    sum += height[j] * (j - stack.peek()[0]) + stack.peek()[1];
                } else {
                    sum += height[j] * (j + 1);
                }
                stack.push(new int[] {j, sum});
                ans += sum;
            }
        }

        return ans;
    }
}
