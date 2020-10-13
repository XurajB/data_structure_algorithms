package problems.bfsdfs;

/**
 * In a N x N grid representing a field of cherries, each cell is one of three possible integers.
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass through;
 * -1 means the cell contains a thorn that blocks your way.
 *
 * Your task is to collect maximum number of cherries possible by following the rules below:
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 */
public class CherryPickup {
    public static void main(String[] args) {
        int[][] grid = {
                {0,1,-1},
                {1,0,-1},
                {1,1,1}
        };
        System.out.println(cherryPickup(grid));
    }

    private static int cherryPickup(int[][] grid) {
        int n = grid.length;
        return Math.max(0, cherryPickup(grid, n, 0, 0, 0, 0, new Integer[n][n][n][n])); // return 0 if impossible
    }

    /*
    Instead of having two paths starting from 0,0 and then from N,N
    We can have two person starting from 0,0 and find two paths that collects maximum cherries.
    First person finds the path to collect maximum cherries and mark those cherries collected, then
    the second person finds another path to collect max cherries.
    We want to collectively collect max cherries. So we have to do the traversal at the same time and select maximum global answer.
    There is a potential problem of double counting if both person are at the same spot which we can avoid using code (only collect one if they are at the same spot)
     */
    // dfs
    private static int cherryPickup(int[][] grid, int n, int r1, int c1, int r2, int c2, Integer[][][][] dp) {
        // since we are only going down and to the right, no need to check for < 0
        // if we went out of grid or hit a throne, we discourage this path by returning MIN_VALUE
        if (r1 >= n || r2 >= n || c1 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1) { // n x n matrix
            return Integer.MIN_VALUE;
        }
        // if person 1 reached the bottom right, return what is in there (could be 0 or 1). no -1 because we don't go there
        if (r1 == n - 1 && c1 == n - 1) {
            return grid[r1][c1];
        }
        // if person 2 reached the bottom right, return what's in there (could be 0 or 1)
        if (r2 == n - 1 && c2 == n - 1) {
            return grid[r2][c2];
        }

        // without dp, the solution can go upto O(4^N*N), we are cherry picking 4 times recursively with problem size of N*N
        // we need to preserve the states. We want to track r1,c1 and r2,c2 positions. so we create Integer[][][][] and track all states
        // which will reduce our complexity to N^4
        // dp[r1][c1][r2][c2] will identify each state. If it is null, then it means we haven't computed subanswer for that state. Not null means we can just return its value.
        if (dp[r1][c1][r2][c2] != null) {
            return dp[r1][c1][r2][c2];
        }

        int cherries = grid[r1][c1];
        if (r1 != r2 || c1 != c2) {
            cherries += grid[r2][c2]; // if they are not in the same spot then collect by summing both cells
        }

        // since each person can move only to the bottom or to the right, total cherries is the max of following possibilities:
        //    P1     |      P2
        //   DOWN    |     DOWN
        //   DOWN    |     RIGHT
        //   RIGHT   |     DOWN
        //   RIGHT   |     RIGHT
        cherries += Math.max(
                Math.max(cherryPickup(grid, n, r1 + 1, c1, r2 + 1, c2, dp), cherryPickup(grid, n, r1 + 1, c1, r2, c2 + 1, dp)),
                Math.max(cherryPickup(grid, n, r1, c1 + 1, r2 + 1, c2, dp), cherryPickup(grid, n, r1, c1 + 1, r2, c2 + 1, dp)));
        dp[r1][c1][r2][c2] = cherries;

        return dp[r1][c1][r2][c2];
    }
}
