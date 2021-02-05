package problems.graph;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
    }

    // Time: O(V+E), space occupied by queue will be O(W) where W is width of the graph, overall is O(V+E)
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // Hash map to save the visited node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        HashMap<Node, Node> visited = new HashMap<>();

        // Put the first node in the queue
        LinkedList<Node> queue = new LinkedList<Node> ();
        queue.offer(node);
        // Clone the node and put it in the visited dictionary.
        visited.put(node, new Node(node.val, new ArrayList<>()));

        // Start BFS traversal
        while (!queue.isEmpty()) {
            // Pop a node say "n" from the from the front of the queue.
            Node n = queue.poll();
            // Iterate through all the neighbors of the node "n"
            for (Node neighbor: n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // Clone the neighbor and put in the visited, if not present already
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    // Add the newly encountered node to the queue.
                    queue.add(neighbor);
                }
                // Add the clone of the neighbor to the neighbors of the clone node "n".
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        // Return the clone of the node from visited.
        return visited.get(node);
    }

    // dfs
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val));
        dfs(node, map);
        return map.get(node);
    }
    private void dfs(Node node, Map<Node, Node> map) {
        for (Node neigh: node.neighbors) {
            if (!map.containsKey(neigh)) {
                map.put(neigh, new Node(neigh.val));
                dfs(neigh, map);
            }
            map.get(node).neighbors.add(map.get(neigh));
        }
    }
}
