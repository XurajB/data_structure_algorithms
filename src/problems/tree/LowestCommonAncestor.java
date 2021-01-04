package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.Arrays;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        //
    }

    //TODO iterative version

    //https://www.youtube.com/watch?v=13m9ZCB8gjw
    private static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);

        // reached leaf
        if (left == null && right == null) {
            return null;
        }

        // root is lca in this case
        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    // LCA in BST
    private static TreeNode lca2(TreeNode root, TreeNode a, TreeNode b) {
        // if both a, b are smaller than root, then go left. if both larger go right.
        // if a, b are on two sides of root, answer is root
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > a.val && curr.val > b.val) {
                curr = curr.left;
            } else if (curr.val < a.val && curr.val < b.val) {
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return null;
    }

    // LCA BST
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
