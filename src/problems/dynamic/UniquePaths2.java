package problems.dynamic;

/**
 * Given a grid of size m * n, lets assume you are starting at (1,1) and your goal is to reach (m,n). At any instance, if you are on (x,y), you can either go to (x, y + 1) or (x + 1, y).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * https://leetcode.com/problems/unique-paths-ii/
 */
public class UniquePaths2 {

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        System.out.println(uniquePathsWithObstacles(grid));
    }

    // O(nm), O(1)
    private static int uniquePathsWithObstacles(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        if (grid[0][0] == 1) {
            return 0;
        }
        grid[0][0] = 1;

        // fill first row
        for (int i = 1; i < c; i++) {
            grid[0][i] = (grid[0][i] == 0 && grid[0][i-1] == 1 ? 1 : 0);
        }

        // fill first column
        for (int i = 1; i < r; i++) {
            grid[i][0] = (grid[i][0] == 0 && grid[i-1][0] == 1 ? 1 : 0);
        }

        // fill the rest
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {

                if (grid[i][j] == 0) {
                    grid[i][j] = grid[i-1][j] + grid[i][j-1];
                } else {
                    grid[i][j] = 0;
                }
            }
        }

        return grid[r-1][c-1];
    }

}
