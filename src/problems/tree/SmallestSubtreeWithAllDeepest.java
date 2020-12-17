package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class SmallestSubtreeWithAllDeepest {
    Map<TreeNode, Integer> depth;
    int max;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        depth = new HashMap<>();
        depth.put(null, -1);

        dfs(root, null);
        max = -1;

        for (Integer d: depth.values()) {
            max = Math.max(max, d);
        }

        return ans(root);

    }

    private void dfs(TreeNode node, TreeNode parent) {
        if (node != null) {
            depth.put(node, depth.get(parent) + 1);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    private TreeNode ans(TreeNode node) {
        if (node == null || depth.get(node) == max) {
            return node;
        }
        TreeNode l = ans(node.left);
        TreeNode r = ans(node.right);

        if (l != null && r != null) {
            return node;
        }

        if (l != null) {
            return l;
        }

        if (r != null) {
            return r;
        }

        return null;
    }
}
