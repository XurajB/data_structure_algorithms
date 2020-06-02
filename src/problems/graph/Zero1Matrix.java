package problems.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * https://leetcode.com/problems/01-matrix/
 */
public class Zero1Matrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };
        int[][] ans = updateMatrix(matrix);
        for (int[] a: ans) {
            System.out.println(Arrays.toString(a));
        }
    }
    // O(r*c)

    // mark 1 as integer max;
    // when doing dfs - if neighbour (prev 1) is higher than current - update neighbour to curr + 1;
    private static int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
                else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] d : dirs) {
                int r = current[0] + d[0];
                int c = current[1] + d[1];
                if (r >= 0 && c >= 0 && r < m && c < n) {
                    int neighbour = matrix[r][c];
                    int curr = matrix[current[0]][current[1]];
                    if (neighbour <= curr) {
                        continue;
                    }
                    queue.add(new int[] {r, c});
                    matrix[r][c] = curr + 1;
                }
            }
        }

        return matrix;
    }
}
