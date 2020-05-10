package problems.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands
 * https://leetcode.com/problems/number-of-distinct-islands/
 */
public class NumberOfDistinctIsland {
    public static void main(String[] args) {
        int[][] grid = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        };

        int[][] grid2 = {
                {1,1,0},
                {0,1,1},
                {0,0,0},
                {1,1,1},
                {0,1,0}
        };

        System.out.println(numDistinctIslands(grid2));
    }

    //O(r * c) time and space
    private static int numDistinctIslands(int[][] grid) {
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // dfs from land
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "o"); // origin
                    System.out.println(sb.toString());
                    ans.add(sb.toString());
                }
            }
        }
        return ans.size();
    }

    private static void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        sb.append(dir);
        grid[i][j] = 0; // can also use visited array

        // travel all four sides
        dfs(grid, i + 1, j, sb, "d");
        dfs(grid, i, j + 1, sb, "r");
        dfs(grid, i - 1, j, sb, "u");
        dfs(grid, i, j - 1, sb, "l");

        sb.append("b"); // back
    }
}
