package problems.backtracking;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 *
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 */
public class UniquePaths3 {
    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.println(uniquePathsIII(grid));
    }

    // 4^n
    private static int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int valid = 1; // counting the starting index
        int x = -1;
        int y = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    valid++;
                }
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                }
            }
        }
        if (x == -1) {
            return 0;
        }

        backtrack(grid, valid, x, y);
        return count;

    }

    static int count = 0;
    private static void backtrack(int[][] grid, int remain, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] < 0) {
            return;
        }
        if (grid[i][j] == 2 && remain == 0) {
            count++;
            return;
        }

        int temp = grid[i][j];
        grid[i][j] = -2; // mark this as visited
        remain--;

        backtrack(grid, remain, i+1, j);
        backtrack(grid, remain, i, j+1);
        backtrack(grid, remain, i-1, j);
        backtrack(grid, remain, i, j-1);

        // replace
        grid[i][j] = temp;
    }
}