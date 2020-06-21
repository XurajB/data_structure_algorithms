package problems.tree;

import dataStructures.trees.TreeNode;

/**
 *  Given a Binary Search Tree that can contain duplicates, find the first occurrence of the number X.
 *  The first occurrence is the first node that we will encounter in an in-order traversal.
 */
public class FirstOccurrenceInBst {
    public static TreeNode findFirstOccurrence(TreeNode root, int target) {
        TreeNode curr = root;
        TreeNode result = null;
        while (curr != null) {
            if (curr.val > target) {
                curr = curr.left;
            } else if (curr.val < target) {
                curr = curr.right;
            } else {
                result = curr;
                curr = curr.left; // another occurrence should be on the left
            }
        }
        return result;
    }
}
