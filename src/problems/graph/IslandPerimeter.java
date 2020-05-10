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
}
