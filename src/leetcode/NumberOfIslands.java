package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }

        return count;
    }

    // O(N * M), row, column, space: min(M,N) - because in bfs; stack size will never be 5 in case of 5X5 grid
    private static void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        while (!queue.isEmpty()) {

            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];

            if (isValid(x + 1, y, grid)) {
                queue.offer(new int[] {x+1, y});
                grid[x+1][y] = '0';
            }
            if (isValid(x, y + 1, grid)) {
                queue.offer(new int[] {x, y+1});
                grid[x][y+1] = '0';
            }
            if (isValid(x - 1, y, grid)) {
                queue.offer(new int[] {x-1, y});
                grid[x-1][y] = '0';
            }
            if (isValid(x, y- 1, grid)) {
                queue.offer(new int[] {x, y-1});
                grid[x][y-1] = '0';
            }
        }
    }

    private static boolean isValid(int x, int y, char[][] grid) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == '0') {
            return false;
        }
        return true;
    }
}
