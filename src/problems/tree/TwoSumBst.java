package problems.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 * https://leetcode.com/problems/two-sum-bsts/
 */
public class TwoSumBst {
    boolean check = false;

    // O(r.length + r2.length) time and space
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> t1 = new HashSet<>();
        inorder(root1, t1);
        inorderWithCheck(root2, t1, target);
        return check;
    }

    private void inorder(TreeNode node, Set<Integer> list) {
        if (node != null) {
            inorder(node.left, list);
            list.add(node.val);
            inorder(node.right, list);
        }
    }

    private void inorderWithCheck(TreeNode node, Set<Integer> set, int target) {
        if (node != null) {
            inorderWithCheck(node.left, set, target);
            int complement = target - node.val;
            if (set.contains(complement)) {
                check = true;
                return;
            }
            inorderWithCheck(node.right, set, target);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
