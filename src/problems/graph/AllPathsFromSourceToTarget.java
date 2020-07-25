package problems.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 */
public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        backtrack(graph, ans, path, graph.length - 1, 0);
        return ans;
    }

    // every time we add a new node, maximum travel from 0 to n-1 will double
    // 2 ^ (N-1) - 1 = O(2^N.N)
    // same for space, to save in ans + recursion will take O(N)
    private void backtrack(int[][] graph, List<List<Integer>> ans, List<Integer> current, int target, int node) {
        if (node == target) {
            ans.add(new ArrayList<>(current));
            return;
        }
        for (int i: graph[node]) {
            current.add(i);
            backtrack(graph, ans, current, target, i);
            current.remove(current.size() -1);
        }
    }

    ///// --- iterative
    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node current = q.poll();
                if (current.id == graph.length - 1) {
                    ans.add(new ArrayList<>(current.nodes));
                    continue;
                }
                for (int node: graph[current.id]) {
                    Node n = new Node(node);
                    n.nodes = new ArrayList<>(current.nodes);
                    n.nodes.add(node);
                    q.offer(n);
                }
            }
        }
        return ans;
    }

    static class Node {
        List<Integer> nodes;
        int id;
        Node(int id) {
            this.id = id;
            nodes = new ArrayList<>();
            nodes.add(id);
        }
    }
}