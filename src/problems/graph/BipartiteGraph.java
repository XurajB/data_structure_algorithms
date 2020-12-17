package problems.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find out if give graph is bipartite
 * A bipartite graph is a graph where nodes can be separated into 2 groups such that no 2 nodes in the group have an edge.
 * We can prove this by using two colors to color the graph and see if there are any adjacent nodes having the same color.
 *
 * Given an undirected graph, return true if and only if it is bipartite.
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 */
public class BipartiteGraph {
    public static void main(String[] args) {
        // each index is a node, values are its neighbours
        // example: node 0 has neighbours 1,3
        int[][] graph = new int[][] {{1,3},{0,2},{1,3},{0,2}};
        System.out.println(isBipartite(graph));
    }

    // O(V+E)
    // variation of hungarian algorithm
    // edges of undirected graph is given
    // each node is between graph.length - 1
    // by coloring the graph with two colors, we want to see if adjacent nodes have same color

    // for each node
    // If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes.
    // If it has been colored, check if the current color is the same as the color that is going to be used to color it.
    private static boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }
        int n = graph.length;
        int[] colors = new int[n]; // n nodes, 0: haven;t been colored, 1: blue, -1: red
        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1; // color this blue

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next: graph[cur]) {
                    if (colors[next] == 0) { // hasn't been colored
                        colors[next] = -colors[cur]; // color it with a different color
                        queue.offer(next);
                    } else if (colors[next] == colors[cur]) {
                        return false; // they are same color
                    }
                }
            }
        }

        return true;
    }
}
