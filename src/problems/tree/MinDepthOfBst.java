package problems.tree;

import dataStructures.trees.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;

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
        int left = helper(root.left);
        int right = helper(root.right);

        int height = Math.min(left, right);

        return 1 + (height > 0 ? height: Math.max(left, right));
    }

    // depending on tree, it might be beneficial to use bfs or dfs. if the height is larger, then bfs will be a better choice
    /// -- iteration
    public int minDepth2(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        } else {
            stack.add(new Pair(root, 1)); // start with height 1
        }
        int minDepth = Integer.MAX_VALUE;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pollLast();
            root = current.getKey();
            int currentDepth = current.getValue();

            // if leaf, note height
            if ((root.left == null) && (root.right == null)) {
                minDepth = Math.min(minDepth, currentDepth);
            }

            // increment height or level
            if (root.left != null) {
                stack.add(new Pair(root.left, currentDepth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair(root.right, currentDepth + 1));
            }
        }
        return minDepth;
    }
}
