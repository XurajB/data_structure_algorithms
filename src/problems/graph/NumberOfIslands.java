package problems.graph;

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
                {'1','1','0','1','0'},
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
    private static void bfs(char[][] grid, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});

        int[][] dirs = new int[][] {
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] point = q.poll();
                int i = point[0];
                int j = point[1];
                for (int[] dir: dirs) {
                    int new_x = i + dir[0];
                    int new_y = j + dir[1];
                    if (new_x >= 0 && new_y >= 0 && new_x < grid.length && new_y < grid[0].length && grid[new_x][new_y] == '1') {
                        grid[new_x][new_y] = '0';
                        q.offer(new int[] {new_x, new_y});
                    }
                }
            }
        }
    }
}
