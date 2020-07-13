package problems.bfsdfs;

/**
 * Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
 * You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
 * Return the maximum number of cherries collection using both robots  by following the rules below:
 * From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
 * When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
 * When both robots stay on the same cell, only one of them takes the cherries.
 * Both robots cannot move outside of the grid at any moment.
 * Both robots should reach the bottom row in the grid.
 */
public class CherryPickup2 {
    public static void main(String[] args) {
        int[][] grid = {
                {3,1,1},{2,5,1},{1,5,5},{2,1,1}
        };
        System.out.println(cherryPickup(grid));
    }

    private static int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][][] dp = new Integer[m][n][n]; // they can only move down or diagonal, row stays the same
        return dfs(grid, m, n, 0, 0, n - 1, dp);
    }

    // O(9 * r * c^2) (each can go 3 directions)
    // O(r * c^2)
    private static int dfs(int[][] grid, int m, int n, int r, int c1, int c2, Integer[][][] dp) {

        if (r >= m || c1 < 0 || c1 >= n || c2 < 0 || c2 >= n) {
            return 0; // they both reach bottom or outside of grid
        }

        if (dp[r][c1][c2] != null) {
            return dp[r][c1][c2];
        }

        int cherries = c1 == c2 ? grid[r][c1] : grid[r][c1] + grid[r][c2];

        int ans = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nc1 = c1 + i;
                int nc2 = c2 + j;

                ans = Math.max(ans, dfs(grid, m, n, r + 1, nc1, nc2, dp));
            }
        }

        dp[r][c1][c2] = ans + cherries;

        return dp[r][c1][c2];
    }
}
