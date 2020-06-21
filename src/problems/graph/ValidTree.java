package problems.graph;

import java.util.*;

/**
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 */
public class ValidTree {
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        System.out.println(validTree(5, edges));
    }

    // to detect if they have valid tree, we perform dfs/bfs and see if we encounter same visited node (no cycle)
    // O(N+E), O(N+E)
    private static boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Map<Integer, Integer> parent = new HashMap<>();
        // Use a seen map that also keeps track of the "parent" node that we got to a node from.
        // Then, when we iterate through the neighbours of a node, we ignore the "parent" node as otherwise it'll be
        // detected as a trivial cycle (and we know that the parent node has already been visited by this point anyway).
        // The starting node (0 in this implementation) has no "parent", so put it as -1.
        parent.put(0, -1); // parent of this node (node, parent)

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour: graph.get(node)) {
                if (parent.get(node) == neighbour) {
                    continue;
                }
                if (parent.containsKey(neighbour)) {
                    return false; // already seen - has cycle. (we are also using parent for visited nodes)
                }
                queue.offer(neighbour);
                parent.put(neighbour, node);
            }
        }

        return parent.size() == n; // we visit every node and detected no cycle
    }

    // can be both dfs/bfs
    // O(N) because we return false when E is not N-1
    private static boolean validTree2(int n, int[][] edges) {
        // For the graph to be a valid tree, it must have exactly n - 1 edges.
        // Any less, and it can't possibly be fully connected, any more, and it has to contain cycles.
        // Additionally, if the graph is fully connected and contains exactly n - 1 edges, it can't possibly contain a cycle, and therefore must be a tree!

        // first case
        if (edges.length != n - 1) {
            return false;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        dfs(0, graph, visited);

        // case 2
        return visited.size() == n;
    }

    private static void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        for (int neighbour: graph.get(node)) {
            dfs(neighbour, graph, visited);
        }
    }
}
