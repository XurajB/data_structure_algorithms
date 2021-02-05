package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 */
public class CousinsInBinaryTree {
    // O(N), O(N)
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Map<Integer, Integer> map = new HashMap<>(); // value, parent val
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    map.put(node.left.val, node.val);
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    map.put(node.right.val, node.val);
                    queue.offer(node.right);
                }
                if (map.containsKey(x) && map.containsKey(y)) {
                    return !map.get(x).equals(map.get(y));
                }
            }
        }
        return false;
    }
}
