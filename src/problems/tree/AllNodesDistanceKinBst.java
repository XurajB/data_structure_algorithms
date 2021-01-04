package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
public class AllNodesDistanceKinBst {
    public static void main(String[] args) {
    }

    ////////////////
    /// Keep track of parent and BFS
    Map<TreeNode, TreeNode> parent;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parent = new HashMap<>();
        dfs(root, root);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);

        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);

        int dist = 0;
        while (!queue.isEmpty() && dist < K) {
            final int size = queue.size();
            dist++;
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (!seen.contains(node.left) && node.left!=null) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)&& node.right!=null) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return queue.stream()
                .map(n -> n.val)
                .collect(Collectors.toList());
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }


    // O(N), O(N)
    private static List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        // 1. build a undirected graph using treenodes as vertices and parent-child relation as edges
        // 2. do a bfs starting from target to find all vertices at distance k to it
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // build graph O(N)
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        buildGraph(graph, root, null);
        if (!graph.containsKey(target)) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);

        // bfs O(N)
        while (!queue.isEmpty()) {
            int size = queue.size();

            // check if we reached our distance
            if (k == 0) {
                for (int i = 0; i < size; i++) {
                    ans.add(queue.poll().val);
                    return ans;
                }
            }
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                for (TreeNode node: graph.get(current)) {
                    if (visited.contains(node)) {
                        continue;
                    }
                    queue.offer(node);
                    visited.add(node);
                }
            }
            k--;
        }
        return ans;
    }

    private static void buildGraph(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        if (!graph.containsKey(node)) {
            graph.put(node, new ArrayList<>());
            if (parent != null) {
                graph.get(node).add(parent);
                graph.get(parent).add(node);
            }
            buildGraph(graph, node.left, node);
            buildGraph(graph, node.right, node);
        }
    }
}
