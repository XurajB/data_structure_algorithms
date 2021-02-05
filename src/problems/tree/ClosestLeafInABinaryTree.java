package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.*;

/**
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.
 * Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.
 * In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.
 */
public class ClosestLeafInABinaryTree {
    // O(N)
    public int findClosestLeaf(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        dfs(root, map, null);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        for (TreeNode node: map.keySet()) {
            if (node != null && node.val == k) {
                queue.offer(node);
                visited.add(node);
                break;
            }
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                if (map.get(cur).size() == 1) {
                    return cur.val; // leaf node.. or check both children
                }
                for (TreeNode neigh: map.get(cur)) {
                    if (!visited.contains(neigh)) {
                        queue.offer(neigh);
                        visited.add(neigh);
                    }
                }
            }
        }
        return -1;
    }

    private void dfs(TreeNode root, Map<TreeNode, List<TreeNode>> map, TreeNode parent) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(root)) {
            map.put(root, new ArrayList<>());
        }
        if (!map.containsKey(parent)) {
            map.put(parent, new ArrayList<>());
        }
        map.get(parent).add(root);
        map.get(root).add(parent);

        dfs(root.left, map, root);
        dfs(root.right, map, root);
    }
}
