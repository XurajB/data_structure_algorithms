package dataStructures.trees;

import java.util.LinkedList;

/**
 * Given a binary tree, find its maximum depth.
 * https://www.interviewbit.com/problems/max-depth-of-binary-tree/
 */
public class MaxDepthOfBst {
    public int maxDepth(TreeNode A) {
        return getDepth(A, 0);
    }

    // O(N) time and space
    private int getDepth(TreeNode A, int current) {
        if (A == null) {
            return current;
        }

        return Math.max(getDepth(A.left, current +1), getDepth(A.right, current+1));
    }

    // iterative - O(N), space - O(logN) or depth
    public int maxDepth2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depths = new LinkedList<>();
        if (root == null) return 0;

        stack.add(root);
        depths.add(1);

        int depth = 0, current_depth = 0;
        while(!stack.isEmpty()) {
            root = stack.pollLast();
            current_depth = depths.pollLast();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(root.left);
                stack.add(root.right);
                depths.add(current_depth + 1);
                depths.add(current_depth + 1);
            }
        }
        return depth;
    }
}
