package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, return a balanced binary search tree with the same node values.
 * A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
 * If there is more than one answer, return any of them.
 */
public class BalanceABST {
    // O(N)
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();

        // in order to collect in sorted order
        collectNodes(root, list);
        // then reconstruct tree starting from the middle
        return helper(list, 0, list.size() - 1);
    }

    private TreeNode helper(List<Integer> list, int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = i + (j-i)/2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = helper(list, i, mid-1);
        node.right = helper(list, mid+1, j);
        return node;
    }

    private void collectNodes(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        collectNodes(root.left, list);
        list.add(root.val);
        collectNodes(root.right, list);
    }
}