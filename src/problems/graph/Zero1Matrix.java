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
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                for (int[] dir: dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length) {

                        if (matrix[nx][ny] <= matrix[x][y]) {
                            continue;
                        }
                        matrix[nx][ny] = matrix[x][y] + 1;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
        }

        return matrix;
    }
}
