package problems.graph;

import java.util.Comparator;
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

    // O(nLogn), n = number of elements
    // space: n
    private static int maximumMinimumPath(int[][] A) {
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int n = A.length;
        int m = A[0].length;
        int[][] visited = new int[n][m];
        // pq to maintain next max node for max path
        // node[0] = x, node[1] = y, node[2] = value
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]> (){
            @Override
            public int compare(int[] a, int[] b) {
                return b[2] - a[2];
            }
        });

        pq.offer(new int[] {0, 0, A[0][0]});
        visited[0][0] = -1;

        int min = A[0][0];
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            if (node[2] < min) {
                min = node[2];
            }
            // check if we reached end
            if (node[0] == n-1 && node[1] == m-1) {
                return min;
            }
            for (int[] dir: dirs) {
                int nextX = node[0] + dir[0];
                int nextY = node[1] + dir[1];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m || visited[nextX][nextY] == -1) {
                    continue;
                }
                pq.offer(new int[] {nextX, nextY, A[nextX][nextY]});
                visited[node[0]][node[1]] = -1;
            }
        }
        return -1;
    }
}
