package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.Stack;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 *
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 */
public class MergeTwoBinaryTrees {
    public static void main(String[] args) {
        //
    }

    // O(N) n is total nodes to be traversed (min of two trees)
    // O(H), height of the recursion tree (min of two trees)
    private static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        return merge(t1, t2);
    }

    private static TreeNode merge(TreeNode t1, TreeNode t2) {
        // we use t1 as our answer tree
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val = t1.val + t2.val;
        t1.left = merge(t1.left, t2.left);
        t1.right = merge(t1.right, t2.right);

        return t1;
    }

    // -------
    // if not using same t1
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        } else if (t2 == null) {
            return t1;
        } else if (t1 == null) {
            return t2;
        } else {
            TreeNode t = new TreeNode(t1.val + t2.val);
            t.left = mergeTrees3(t1.left, t2.left);
            t.right = mergeTrees3(t1.right, t2.right);
            return t;
        }
    }

    //---------
    // iterative
    // stack or queue, same thing
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1, t2});
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            if (t[0] == null || t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }
}
