package problems.binarysearch;

import dataStructures.trees.TreeNode;

/**
 * Given a complete binary tree, count the number of nodes.
 * Complete binary tree: All levels are filled except the last level. Last level are filled as far left as possible (filled from left to right)
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 * Count = 6
 */
public class CountCompleteTreeNodes {
    // O(N)
    private int countNodes2(TreeNode root) {
        if (root != null) {
            return 1 + countNodes2(root.left) + countNodes2(root.right);
        }
        return 0;
    }

    // better using complete tree property
    // we know all levels are completely filled except last node.
    // level before last node (let's say d) - total nodes: 2^d - 1
    // total nodes: 2^d - 1 + nodes in last level
    // O(d^2)
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // count depth of the tree
        int depth = computeDepth(root);
        if (depth == 0) {
            return 0;
        }
        // now we need to count the nodes in last level
        // do a binary search to check how many nodes exists
        int low = 1;
        int high = (int) Math.pow(2, depth) - 1;

        while (low <= high) {
            int pivot = low + (high - low) / 2;
            // check if this node exists
            if (exists(pivot, depth, root)) {
                low = pivot + 1;
            } else {
                high = pivot - 1;
            }
        }

        return (int) Math.pow(2, depth) - 1 + low;
        // return (1 << depth) - 1 + low;
    }

    private boolean exists(int id, int d, TreeNode node) {
        int left = 0;
        int right = (int) Math.pow(2, d) - 1;

        for (int i = 0; i < d; i++) {
            int pivot = left + (right - left) / 2;
            if (id <= pivot) {
                node = node.left;
                right = pivot;
            } else {
                node = node.right;
                left = pivot + 1;
            }
        }
        return node != null;
    }

    // since they are all full
    // just go all way to the left
    // O(d)
    private int computeDepth(TreeNode root) {
        TreeNode node = root;
        int d = 0;
        while (node.left != null) {
            node = node.left;
            d++;
        }
        return d;
    }
}
