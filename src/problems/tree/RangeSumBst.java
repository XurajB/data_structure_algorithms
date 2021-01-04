package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.Stack;

/**
 * Given the root node of a binary search tree, return the sum of values of all nodes with a value in the range [low, high].
 */
public class RangeSumBst {
    // O(H)
    public int rangeSumBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    // 10+7+15
    private int dfs(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        if (root.val >= low && root.val <= high) {
            ans += root.val;
        }
        if (root.val > low) {
            ans += dfs(root.left, low, high);
        }
        if (root.val < high) {
            ans += dfs(root.right, low, high);
        }
        return ans;
    }

    // iterative
    public int rangeSumBST2(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && R >= node.val) {
                    sum += node.val;
                }
                if (node.val > L) {
                    stack.push(node.left);
                }
                if (node.val < R) {
                    stack.push(node.right);
                }
            }
        }
        return sum;
    }
}
