package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, determine if it is a complete binary tree.
 * In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class CheckCompletenessOfBT {
    // BFS
    // we can have c values in each nodes as: c*2, c*2+1
    // and check last node of that level == level.size();
    // or this way is
    public boolean isCompleteTree(TreeNode root) {
        // put all nodes in queue using bfs
        // iterate the queue and if null is found then return if queue.isempty
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.peek() != null) {
            TreeNode node = queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
        while (!queue.isEmpty() && queue.peek() == null) {
            queue.poll();

        }
        return queue.isEmpty();
    }

    ////////////////////////////////
    // DFS
    // if node's index is > total nodes, then false
    public boolean isCompleteTree2(TreeNode root) {
        int total = countNodes(root);
        return helper(root, 1, total);
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private boolean helper(TreeNode root, int idx, int total) {
        if (root == null) {
            return true;
        }
        if (idx > total) {
            return false;
        }
        return helper(root.left, idx * 2, total)
                && helper(root.right, idx * 2 + 1, total);
    }
}
