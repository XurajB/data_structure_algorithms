package problems.bfsdfs;

import java.util.PriorityQueue;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col).
 * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 */
public class PathWithMinimumEffort {

    // O(mn*log(mn))
    private static int minimumEffortPath(int[][] heights) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        int n = heights.length;
        int m = heights[0].length;
        Integer[][] minDist = new Integer[n][m];
        minDist[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == n - 1 && cur[1] == m - 1) {
                return cur[2];
            }
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                int effort = Math.max(cur[2], Math.abs(heights[cur[0]][cur[1]] - heights[nx][ny]));
                if (minDist[nx][ny] == null || minDist[nx][ny] > effort) {
                    minDist[nx][ny] = effort;
                    pq.offer(new int[]{nx, ny, minDist[nx][ny]});
                }
            }
        }
        return -1;
    }
}
