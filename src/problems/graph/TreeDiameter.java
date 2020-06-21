package problems.graph;

import java.util.*;

/**
 * 6/14/2020
 * Diameter of a Graph: Given a directed graph, find the length of the longest path in the graph.
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
 *
 * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.
 * Each node has labels in the set {0, 1, ..., edges.length}.
 *
 */
public class TreeDiameter {
    public static void main(String[] args) {

    }

    // O(N)
    private static int treeDiameter(int[][] edges) {
        /*
        We can find longest path using two BFSs.
        If we start BFS from any node 'N' and find a node with the longest distance from 'N', it must be an end point of the longest path.
        It can be proved using contradiction.
        So the algorithm reduces to simple two BFSs.
        First BFS to find an end point of the longest path and second BFS from this end point to find the actual longest path.
         */

        // build the graph
        Map<Integer, List<Integer>> graph = new HashMap<>(); // adjacency list
        int n = edges.length + 1; // nodes = edges + 1
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // first dfs to find the end point of longest path from some node - we are using 0 here
        Node start = bfs(graph, 0);
        return bfs(graph, start.value).distance;
    }

    private static Node bfs(Map<Integer, List<Integer>> graph, int start) {
        int n = graph.size();
        int[] distance = new int[n];
        Arrays.fill(distance, -1); // we will also use this as a visited array

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbour: graph.get(current)) {
                if (distance[neighbour] == -1) {
                    distance[neighbour] = distance[start] + 1;
                    queue.offer(neighbour);
                }
            }
        }

        int maxDistance = 0;
        int val = 0;
        for (int i = 0; i < n; i++) {
            if (distance[i] > maxDistance) {
                maxDistance = distance[i];
                val = i;
            }
        }

        return new Node(val, maxDistance);
    }

    static class Node {
        int value;
        int distance;
        Node(int value, int distance) {
            this.value = value;
            this.distance = distance;
        }
    }
}
