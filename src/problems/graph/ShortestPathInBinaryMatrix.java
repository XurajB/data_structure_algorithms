package problems.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 *
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 *
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
public class ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        int[][] grid = {{0,1},{1,0}};
        System.out.println(shortestPathBinaryMatrix(grid));
    }

    private static int shortestPathBinaryMatrix(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;

        if (grid[0][0] == 1 || grid[rows-1][columns-1] == 1) {
            return -1;
        }
        int directions[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{-1,-1},{1,1}}; // 4 directions + diagonals
        Queue<int[]> bfsQueue = new LinkedList();
        bfsQueue.add(new int[]{0,0});
        grid[0][0] = 1;
        int pathLen = 0;

        while (!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();
            for (int i = 0; i < size; i++) {
                int[] coordinates = bfsQueue.poll();
                for (int[] direction: directions) {
                    if (coordinates[0] == rows - 1 && coordinates[1] == columns - 1) {
                        return 1 + pathLen; // return here prevents TLE
                    }
                    int x = coordinates[0] + direction[0];
                    int y = coordinates[1] + direction[1];
                    if (x >= 0 && x < rows && y >= 0 && y < columns && grid[x][y] == 0) {
                        bfsQueue.add(new int[]{x, y});
                        grid[x][y] = 1;
                    }
                }
            }
            pathLen++;
        }
        return -1;
    }
}
