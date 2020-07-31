package problems.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 */
public class ShortestPathVisitingAllNodes {
    public static void main(String[] args) {
        int[][] graph = {{1},{0,2,4},{1,3,4},{2},{1,2}};
        System.out.println(shortestPathLength(graph));
    }

    private static int shortestPathLength(int[][] graph) {
        // the trick is we need to represent a path that has already been visited
        // we can use bit mask to represent path
        int n = graph.length;
        int fullMask = (1 << n) - 1; // from question: 1 <= n <= 12

        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        // we may start from any node
        for (int i = 0; i < n; i++) {
            Node node = new Node(i, 1 << i);
            queue.offer(node);
            visited.add(node.toString());
        }

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.mask == fullMask) {
                    return level;
                }
                for (int id: graph[node.id]) {
                    Node next = new Node(id, node.mask | (1 << id));
                    if (visited.contains(next.toString())) {
                        continue;
                    }
                    queue.offer(next);
                    visited.add(next.toString());
                }
            }
            level++;
        }
        return level;
    }

    static class Node {
        int id;
        int mask;
        Node (int id, int mask) {
            this.id = id;
            this.mask = mask;
        }
        public String toString() {
            return id + " " + mask;
        }
    }
}
