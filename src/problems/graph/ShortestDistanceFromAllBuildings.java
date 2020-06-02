package problems.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 */
public class ShortestDistanceFromAllBuildings {
    public static void main(String[] args) {
        int[][] grid = {
                {1,0,2,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        System.out.println(shortestDistance(grid));
    }

    // O(buildings * m * n)
    private static int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;

        // total count if this i,j is reachable from all buildings
        int[][] reach = new int[r][c];
        // total distance to reach at i,j from all buildings
        int[][] distance = new int[r][c];

        int totalBuildings = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, reach, distance, i, j);
                    totalBuildings++;
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0 && reach[i][j] == totalBuildings) {
                    minDistance = Math.min(minDistance, distance[i][j]);
                }
            }
        }

        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return minDistance;
    }

    private static void bfs(int[][] grid, int[][] reach, int[][] distance, int x, int y) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        int[][] dirs = {{1,0},{0,1},{0,-1},{-1,0}};
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] pos = q.poll();
                for (int[] dir: dirs) {
                    int newx = pos[0] + dir[0];
                    int newy = pos[1] + dir[1];

                    // check for edges
                    if (newx >= 0 && newy >= 0 && newx < grid.length && newy < grid[0].length && !visited[newx][newy] && grid[newx][newy]==0) {
                        reach[newx][newy]++;
                        distance[newx][newy] += level;
                        q.offer(new int[] {newx, newy});
                        visited[newx][newy] = true;
                    }
                }
            }
            level++;

        }
    }
}
