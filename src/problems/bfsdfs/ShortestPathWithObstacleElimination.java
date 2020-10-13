package problems.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles.
 * If it is not possible to find such walk return -1.
 */
public class ShortestPathWithObstacleElimination {
    public static void main(String[] args) {
        int[][] grid = {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        System.out.println(shortestPath(grid, 1));
    }

    // O(k*m*n)
    // we need to maintain separate visited data for each k
    private static int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>(); // x,y,k
        boolean[][][] visited = new boolean[m][n][k+1]; // k+1, coz k is size, this is index
        visited[0][0][0] = true;

        queue.offer(new int[] {0, 0, 0});
        int level = 0;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                if (current[0] == m - 1 && current[1] == n - 1) {
                    return level;
                }

                for (int[] dir: dirs) {
                    int x = current[0] + dir[0];
                    int y = current[1] + dir[1];
                    int nextK = current[2];
                    if (x >= 0 && y >= 0 && x < m && y < n) {
                        if (grid[x][y] == 1) {
                            nextK++;
                        }
                        if (nextK <= k && !visited[x][y][nextK]) {
                            visited[x][y][nextK] = true;
                            queue.offer(new int[] {x, y, nextK});
                        }
                    }
                }
            }
            level++;
        }

        return -1;
    }
}
