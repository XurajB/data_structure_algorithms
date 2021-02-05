package problems.graph;

import java.util.PriorityQueue;

/**
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
 * The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
 * A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
 * https://leetcode.com/problems/path-with-maximum-minimum-value/
 */
public class PathWithMaximumMinimumValue {
    public static void main(String[] args) {
        int[][] A = {{5,4,5},{1,2,6},{7,4,6}};
        System.out.println(maximumMinimumPath(A));
    }

    // O(VlogV + E)
    // space: n
    private static int maximumMinimumPath(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        boolean[][] visited = new boolean[m][n];
        heap.offer(new int[] {0, 0, A[0][0]});

        int[][] dr = {{0,1},{1,0},{0,-1},{-1,0}};

        int min = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();

            min = Math.min(min, curr[2]);
            if (curr[0] == m - 1 && curr[1] == n - 1) {
                return min;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i][0];
                int nc = curr[1] + dr[i][1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int ns = A[nr][nc];
                    heap.offer(new int[]{nr, nc, Math.min(curr[2], ns)});
                    visited[nr][nc] = true;
                }
            }
        }
        return 0;
    }
}
