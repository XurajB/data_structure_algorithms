package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 * Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.
 */
public class FlipEquivalentBinaryTree {
    // there are 4 different ways we can flip
    // O(N), N = Min(r1, r2)
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        boolean leftLeft = flipEquiv(root1.left, root2.left);
        boolean leftRight = flipEquiv(root1.left, root2.right);
        boolean rightLeft = flipEquiv(root1.right, root2.left);
        boolean rightRight = flipEquiv(root1.right, root2.right);

        return (leftLeft && rightRight) || (leftRight && rightLeft);
    }

    ////--- iterative
    // O(N), O(N)
    public boolean flipEquivIterative2(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode curr1 = queue.poll();
            TreeNode curr2 = queue.poll();
            if (curr1 == null && curr2 == null) {
                continue;
            }
            if (!isEquals(curr1, curr2)) {
                return false;
            }
            if (isEquals(curr1.left, curr2.left) && isEquals(curr1.right, curr2.right)) {
                queue.offer(curr1.left);
                queue.offer(curr2.left);
                queue.offer(curr1.right);
                queue.offer(curr2.right);
            } else if (isEquals(curr1.left, curr2.right) && isEquals(curr1.right, curr2.left)) {
                queue.offer(curr1.left);
                queue.offer(curr2.right);
                queue.offer(curr1.right);
                queue.offer(curr2.left);
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean isEquals(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else return root1 != null && root2 != null && root1.val == root2.val;
    }
}
