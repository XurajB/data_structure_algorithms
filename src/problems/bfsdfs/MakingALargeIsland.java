package problems.bfsdfs;

import java.util.*;

/**
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 */
public class MakingALargeIsland {
    // (m*n)^2
    public int largestIsland(int[][] grid) {
        int n = grid.length;

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;

                    ans = Math.max(ans, getSize(grid, i, j, new boolean[n][n]));
                    if (ans == n*n) {
                        return ans;
                    }
                    grid[i][j] = 0;
                }
            }
        }

        return ans == Integer.MIN_VALUE ? n * n : ans;
    }

    private int getSize(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || visited[i][j] || grid[i][j] == 0) {
            return 0;
        }

        visited[i][j] = true;
        int count = 1;
        count += getSize(grid, i+1, j, visited);
        count += getSize(grid, i, j+1, visited);
        count += getSize(grid, i-1, j, visited);
        count += getSize(grid, i, j-1, visited);

        return count;
    }

    ///////
    /////
    // (m*n)^2, optimized
    public int largestIsland2(int[][] grid) {
        int n = grid.length;

        // dfs every island and give it a index and save into hashmap
        int index = 3; // start index
        int ans = 0;
        Map<Integer, Integer> area = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area.put(index, dfs(grid, i, j, index));
                    ans = Math.max(ans, area.get(index));
                    index++;
                }
            }
        }

        // traverse every cell with 0 and see if we can connect to make it biggest
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int cur = 1;
                    Set<Integer> seen = new HashSet<>();
                    for (int[] next: move(i, j, grid.length)) {
                        index = grid[next[0]][next[1]];
                        if (index > 1 && !seen.contains(index)) {
                            seen.add(index);
                            cur += area.get(index);
                        }
                    }
                    ans = Math.max(ans, cur);
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, int index) {
        int area = 0;
        grid[i][j] = index;
        for (int[] next: move(i, j, grid.length)) {
            if (grid[next[0]][next[1]] == 1) {
                area += dfs(grid, next[0], next[1], index);
            }
        }
        return area+1;
    }

    private List<int[]> move(int i, int j, int n) {
        List<int[]> ans = new ArrayList<>();
        if (valid(i+1, j, n)) {
            ans.add(new int[] {i+1, j});
        }
        if (valid(i, j+1, n)) {
            ans.add(new int[] {i, j+1});
        }
        if (valid(i-1, j, n)) {
            ans.add(new int[] {i-1, j});
        }
        if (valid(i, j-1, n)) {
            ans.add(new int[] {i, j-1});
        }
        return ans;
    }

    public boolean valid(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
