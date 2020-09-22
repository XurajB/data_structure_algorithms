package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given a root of BST and a number num, find the largest key in the tree that is smaller than num
 */
public class LargestSmallerBstKey {
    // this is equivalent of the floor function

    private int findLargestSmallerKey(TreeNode root, int num) {
        if (root == null) {
            return -1;
        }
        if (num <= root.left.val) {
            return findLargestSmallerKey(root.left, num);
        } else {
            int val = findLargestSmallerKey(root.right, num);
            if (val != -1 && val > root.val) {
                return val;
            } else {
                return root.val;
            }
        }
    }

    ////////

    private int findLargestSmallerKey2(TreeNode root, int num) {
        int ans = -1;
        while (root != null) {
            if (num <= root.val) {
                root = root.left;
            } else {
                ans = root.val;
                root = root.right;
            }
        }

        return ans;
    }
}
