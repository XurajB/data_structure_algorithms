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
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) return 0;
        queue.offer(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            depth++; // bfs will visit all levels
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return depth;
    }
}
