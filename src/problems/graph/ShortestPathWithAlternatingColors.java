package problems.graph;

import java.util.*;

/**
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 * Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node
 * X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 */
public class ShortestPathWithAlternatingColors {
    // n*n
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[][] graph = new int[n][n]; // adjacency matrix
        buildGraph(graph, red_edges, blue_edges, n);

        Queue<int[]> q = new LinkedList<>(); // node, color
        q.offer(new int[] {0, 1});
        q.offer(new int[] {0, -1});

        int len = 0;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        ans[0] = 0;

        Set<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            int size = q.size();

            len++;

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int node = cur[0];
                int color = cur[1];

                int oppColor = -color;

                for (int j = 1; j < n; j++) {
                    if (graph[node][j] == oppColor || graph[node][j] == 0) {
                        if (!visited.contains(j+""+oppColor)) {
                            visited.add(j+""+oppColor);
                            q.offer(new int[] {j, oppColor});
                            if (ans[j] == -1) {
                                ans[j] = len;
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }

    private void buildGraph(int[][] graph, int[][] red, int[][] blue, int n) {
        // mark -n if the edge does not exist
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], -n);
        }

        for (int[] r: red) {
            int from = r[0];
            int to = r[1];

            graph[from][to] = 1; // mark 1 as red
        }

        for (int[] b: blue) {
            int from = b[0];
            int to = b[1];

            if (graph[from][to] == 1) {
                graph[from][to] = 0; // mark 0 as both
            } else {
                graph[from][to] = -1; // mark -1 as blue
            }
        }
    }
}
