package problems.graph;

/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * Find island perimeter
 * https://leetcode.com/problems/island-perimeter/
 */
public class IslandPerimeter {

    // any time we hit an obstacle, add to count
    // time: O(r * c), space: O(1)
    public int islandPerimeter(int[][] grid) {
        int count = 0;
        int[][] dirs = new int[][] {
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    for (int[] dir: dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    ////// dfs
    public int islandPerimeter2(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    return count;
                }
            }
        }
        return count;
    }

    int count = 0;
    private void dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0) {
            count++;
            return;
        }
        if (grid[x][y] == -1) {
            return;
        }
        grid[x][y] = -1;
        dfs(grid, x+1, y);
        dfs(grid, x, y+1);
        dfs(grid, x-1, y);
        dfs(grid, x, y-1);
    }


    //////////
    public int islandPerimeter3(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return dfs2(grid, i, j);
                }
            }
        }
        return 0;
    }

    private int dfs2(int[][] grid, int x, int y) {
        int count = 0;
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == -1) {
            return 0;
        }
        grid[x][y] = -1;
        count += dfs2(grid, x+1, y);
        count += dfs2(grid, x, y+1);
        count += dfs2(grid, x-1, y);
        count += dfs2(grid, x, y-1);

        return count;
    }
}
