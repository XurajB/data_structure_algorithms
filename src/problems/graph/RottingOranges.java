package problems.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {
    public static void main(String[] args) {
        int[][] grid = new int[][] {{2,1,1}, {1,1,0}, {0,1,1}};
        System.out.println(orangesRotting(grid));
    }

    // time: O(N), space: O(N)
    private static int orangesRotting(int[][] grid) {
        // validations
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        // queue for bfs
        Queue<int[]> queue = new LinkedList<>();
        // number of minutes
        int count = 0;
        // fresh count
        int freshCount = 0;
        // put the position of all rotten oranges in queue
        // and count the number of fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }
        // direction array
        int[][] dirs = new int[][] {{1,0}, {-1, 0}, {0, 1}, {0, -1}};

        // bfs starting from initially rotten oranges
        while (!queue.isEmpty() && freshCount > 0) {
            count++; // counting number of levels
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();

                for (int[] dir: dirs) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];

                    // if x or y is out of bound
                    // or if the orange at x, y is already rotten
                    // or if x,y is empty then we do nothing
                    if (x < 0 || y < 0 || x >= cols || y >= rows || grid[x][y] == 2 || grid[x][y] == 0) {
                        continue;
                    }
                    // mark the orange at x,y as rotten
                    grid[x][y] = 2;
                    // put the newly rotten orange into the queue
                    queue.offer(new int[] {x, y});
                    // decrement fresh count
                    freshCount--;
                }
            }
        }

        if (freshCount == 0) {
            return count;
        }
        return -1;
    }
}