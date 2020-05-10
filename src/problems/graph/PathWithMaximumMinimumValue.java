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
        if (A == null) {
            return 0;
        }
        int r = A.length;
        int c = A[0].length;

        int[][] visited = new int[r][c];
        int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        //bfs
        //using max instead of regular queue so that we get next max path node
        //node[0] = x, node[0] = y, node[2] = value
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[2] - o1[2];
            }
        });

        pq.offer(new int[] {0, 0, A[0][0]});
        visited[0][0] = -1; // mark as visited
        int min = A[0][0];

        while (!pq.isEmpty()) {
            int[] node = pq.poll();

            if (node[2] < min) {
                min = node[2];
            }

            // check if we have reached the end
            if (node[0] == r-1 && node[1] == c-1) {
                return min;
            }

            // go through its neighbours
            for (int[] dir: dirs) {
                int nextR = dir[0] + node[0];
                int nextC = dir[1] + node[1];
                if (nextR < 0 || nextC < 0 || nextR >= r || nextC >= c || visited[nextC][nextR] == -1) {
                    continue;
                }

                pq.offer(new int[] {nextR, nextC, A[nextR][nextC]});
                visited[nextR][nextC] = -1;
            }
        }

        return -1;
    }
}
