package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 */
public class DeleteNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // three cases: leaf node, one child, both child
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // find smallest from the right child and recursively delete
            int smallest = findSmallest(root.right);
            root.val = smallest;
            root.right = deleteNode(root.right, smallest); // delete smallest now
        }

        return root;
    }

    private int findSmallest(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }
}
