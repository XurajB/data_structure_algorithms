package problems.graph.unionfind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c,
 * then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 */
public class NumberOfProvinces {
    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
    }

    // union-find, O(N^2)
    // we are given adjacency matrix
    private static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int pi = find(parents, i);
                    int pj = find(parents, j);

                    if (pi != pj) {
                        count++;
                        parents[pi] = pj;
                    }
                }
            }
        }
        return n - count;
    }

    private static int find(int[] parents, int i) {
        if (parents[i] == i) {
            return i;
        }
        parents[i] = find(parents, parents[i]);
        return parents[i];
    }

    //////////////////////
    /// bfs. O(N^2)
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            count++;
            queue.offer(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int j = 0; j < n; j++) {
                    if (isConnected[cur][j] == 1 && !visited[j]) {
                        visited[j] = true;
                        queue.offer(j);
                    }
                }
            }
        }
        return count;
    }

    /////////////////////
    /// dfs
    public int findCircleNum3(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
}
