package problems.tree;

import dataStructures.trees.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * https://www.interviewbit.com/problems/min-depth-of-binary-tree/
 */
public class MinDepthOfBst {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root);
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // what happens when left is null? we still return depth of right
        if (root.left == null) {
            return helper(root.right) + 1;
        }
        if (root.right == null) {
            return helper(root.left) + 1;
        }
        // else
        int left = helper(root.left);
        int right = helper(root.right);
        return Math.min(left, right) + 1;
    }

    // depending on tree, it might be beneficial to use bfs or dfs. if the height is larger, then bfs will be a better choice
    /// -- iteration
    public int minDepth2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left == null && current.right == null) {
                    return level;
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            level++;
        }
        return level;
    }
}
