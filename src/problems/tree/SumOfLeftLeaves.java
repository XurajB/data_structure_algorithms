package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the sum of all left leaves in a given binary tree.
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return leftSum(root, false);
    }

    // in order
    // O(N)
    private int leftSum(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }

        int val = 0;
        if (isLeft && node.left == null && node.right == null) {
            val += node.val;
        }

        return val + leftSum(node.left, true) + leftSum(node.right, false);
    }

    // level order
    // O(N)
    private int leftSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { // for not necessary
                TreeNode cur = queue.poll();
                if (cur.left != null && cur.left.left == null && cur.left.right == null) {
                    ans += cur.left.val;
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }
        }
        return ans;
    }
}
