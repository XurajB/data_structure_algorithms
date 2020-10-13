package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion.
 * It is guaranteed that the new value does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 */
public class InsertIntoABst {
    // log(n) / O(h)
    private static TreeNode insertIntoBst(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            root.right = insertIntoBst(root.right, val);
        } else {
            root.left = insertIntoBst(root.left, val);
        }

        return root;
    }

    // iterative
    private static TreeNode insertIntoBst2(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null) {
            if (val > node.val) {
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    return root;
                }
                node = node.right;
            } else {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    return root;
                }
                node = node.left;
            }
        }

        return new TreeNode(val);
    }
}
