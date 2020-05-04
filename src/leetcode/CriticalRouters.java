package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which,
 * when removed along with associated edges, makes the graph disconnected (or more precisely, increases the number of connected components in the graph).
 * The task is to find all articulation points in the given graph.
 * https://leetcode.com/discuss/interview-question/436073/
 */
public class CriticalRouters {
    public static void main(String[] args) {
        // case 1
        int numNodes = 7;
        int numEdges = 7;
        int[][] edges = new int[][] {
                {0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}
        };
        System.out.println(getCriticalNodes(numNodes, numEdges, edges));
    }

    static int time = 0;
    private static List<Integer> getCriticalNodes(int numNodes, int numEdges, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (edges == null || edges.length == 0) {
            return ans;
        }

        // build graph
        List<Integer>[] graph = buildGraph(numNodes, numEdges, edges);

        // dfs
        boolean[] visited = new boolean[numNodes];
        int[] lowTimes = new int[numNodes];
        int[] visitedTimes = new int[numNodes];
        dfs(graph, ans, visited, visitedTimes, lowTimes, 0, -1);

        return ans;
    }

    // current = current node
    // O(E+V)
    private static void dfs(List<Integer>[] graph, List<Integer> answer, boolean[] visited, int[] visitedTimes, int[] lowTimes, int current, int parent) {
        visited[current] = true;
        visitedTimes[current] = lowTimes[current] = time++;

        for (int neighbour: graph[current]) {
            if (parent == neighbour) continue;
            if (!visited[neighbour]) {
                dfs(graph, answer, visited, visitedTimes, lowTimes, neighbour, current);
                lowTimes[current] = Math.min(lowTimes[current], lowTimes[neighbour]);
                if (lowTimes[neighbour] > visitedTimes[current]) {
                    answer.add(current);
                }
            } else {
                // found back edge
                lowTimes[current] = Math.min(lowTimes[current], visitedTimes[neighbour]);
            }
        }
    }

    private static List<Integer>[] buildGraph(int numNodes, int numEdges, int[][] edges) {
        List<Integer>[] graph = new ArrayList[numNodes];
        // adding edges
        for (int i = 0; i < numEdges; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < numEdges; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a].add(b);
            graph[b].add(a);
        }
        return graph;
    }
}
