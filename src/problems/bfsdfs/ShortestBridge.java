package problems.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 */
public class ShortestBridge {
    // O(m*n)
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        // dfs - identify first island, visit the island and mark and visited. put all in queue
        // bfs - to find the first thing that can reach the second island
        boolean[][] visited = new boolean[m][n]; // or modify A if allowed
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;
        for (int i = 0; i < m; i++) {
            if (found) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    dfs(A, queue, visited, i, j);
                    found = true;
                    break;
                }
            }
        }
        return bfs(queue, A, visited);
    }

    private int bfs(Queue<int[]> queue, int[][] A, boolean[][] visited) {
        int level = 0;
        int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                for (int[] dir: dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && ny >= 0 && nx < A.length && ny < A[0].length && !visited[nx][ny]) {
                        if (A[nx][ny] == 1) {
                            return level; // note, all initial items in queue has 1. so return here
                        }
                        visited[nx][ny] = true;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private void dfs(int[][] A, Queue<int[]> queue, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= A.length || j >= A[0].length || A[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        queue.offer(new int[] {i, j});
        dfs(A, queue, visited, i+1, j);
        dfs(A, queue, visited, i, j+1);
        dfs(A, queue, visited, i-1, j);
        dfs(A, queue, visited, i, j-1);
    }
}
