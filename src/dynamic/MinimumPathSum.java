package dynamic;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] input = {
            {1,3,1},
            {1,5,1},
            {4,2,1}
        };

        System.out.println(minPathSum(input)); // answer is 7
    }

    private static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[][] sum = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                sum[i][j] = grid[i][j];

                if (i > 0 && j > 0) {
                    sum[i][j] += Math.min(sum[i-1][j], sum[i][j-1]);
                } else if (i > 0) {
                    sum[i][j] += sum[i-1][j];
                } else if (j > 0) {
                    sum[i][j] += sum[i][j-1];
                }
            }
        }

        return sum[grid.length -1][grid[0].length -1];
    }
}
