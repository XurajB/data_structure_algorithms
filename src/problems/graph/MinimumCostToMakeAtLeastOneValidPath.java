package problems.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some invalid signs on the cells of the grid which points outside the grid.
 *
 * You will initially start at the upper left cell (0,0).
 * A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid.
 * The valid path doesn't have to be the shortest.
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 * Return the minimum cost to make the grid have at least one valid path.
 */
public class MinimumCostToMakeAtLeastOneValidPath {
    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        System.out.println(minCost(grid));
    }

    // O(ElogV) ~ O(rc * log(rc))
    // O(rc)
    private static int minCost(int[][] grid) {
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}}; //r,l,b,t  index-1 is equivalent to the direction sign (in question)

        PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2) -> p1[2] - p2[2]); // min heap
        queue.offer(new int[] {0, 0, 0}); // start from 0,0 at 0 cost

        int[][] costs = new int[grid.length][grid[0].length];
        for (int[] i: costs) {
            Arrays.fill(i, Integer.MAX_VALUE);
        }
        costs[0][0] = 0; // 0 starting cost

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int cost = current[2];

            for (int i = 0; i < dirs.length; i++) {
                int nr = r + dirs[i][0];
                int nc = c + dirs[i][1];
                // check if next is inside grid
                if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length) {
                    int ncost = cost;
                    if (i != (grid[r][c] - 1)) { // next cell is different direction, which also means next step is not free
                        ncost++;
                    }
                    // take this path only if cost is lower
                    if (ncost < costs[nr][nc]) {
                        // update with minimum cost
                        costs[nr][nc] = ncost;
                        queue.offer(new int[] {nr, nc, ncost});
                    }
                }
            }
        }
        return costs[grid.length-1][grid[0].length-1];
    }
}
