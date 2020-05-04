package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have a map that marks the locations of treasure islands. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in.
 * There are other explorers trying to find the treasure. So you must figure out a shortest route to one of the treasure islands
 *
 */
public class TreasureIsland2 {
    public static void main(String[] args) {
        char[][] grid = {
                {'S', 'O', 'O', 'S', 'S'},
                {'D', 'O', 'D', 'O', 'D'},
                {'O', 'O', 'O', 'O', 'X'},
                {'X', 'D', 'D', 'O', 'O'},
                {'X', 'D', 'D', 'D', 'O'}
        };
        System.out.println(findShortestRoute(grid));
    }

    private static int findShortestRoute(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'S') {
                    queue.offer(new int[] {i, j});
                    grid[i][j] = 'D';
                }
            }
        }

        int[][] dirs = new int[][] {
                {1, 0}, {0, -1}, {-1, 0}, {0, 1}
        };
        int level = 1;

        // bfs
        // O(r*c) time and space - multi source bfs
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];

                // travel to four sides
                for (int[] dir: dirs) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];

                    if (nextX >= 0 && nextY >= 0 && nextX < grid.length && nextY < grid[0].length) {
                        if (grid[nextX][nextY] == 'O') {
                            queue.offer(new int[] {nextX, nextY});
                            grid[nextX][nextY] = 'D';
                        } else if (grid[nextX][nextY] == 'X') {
                            return level;
                        }
                    }
                }
            }
            level++;
        }

        return -1;
    }
}
