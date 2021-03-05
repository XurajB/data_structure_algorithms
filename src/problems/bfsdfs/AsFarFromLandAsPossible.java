package problems.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land,
 * find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 */
public class AsFarFromLandAsPossible {
    // O(n*n)
    // put all 1s in queue, and see how far we can go until we see 0
    public int maxDistance(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        int[][] dirs = {{0, 1}, {1,0}, {-1,0}, {0,-1}};
        int level = -1; // we already have ones in queue

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] cur = queue.poll();
                for (int[] dir: dirs) {
                    int nx = cur[0] + dir[0];
                    int ny = cur[1] + dir[1];
                    if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
            level++;
        }

        return level <= 0 ? -1 : level;
    }
}
