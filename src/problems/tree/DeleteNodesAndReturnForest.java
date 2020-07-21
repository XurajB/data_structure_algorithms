package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 */
public class DeleteNodesAndReturnForest {
    // O(N), O(H + N_to_delete)
    private List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> forest = new ArrayList<>();
        if (root == null) {
            return forest;
        }
        Set<Integer> set = new HashSet<>();
        for (int i: to_delete) {
            set.add(i);
        }
        deleteNodes(root, set, forest);
        // don't forget to add root
        if (!set.contains(root.val)) {
            forest.add(root);
        }
        return forest;
    }

    // post order
    private TreeNode deleteNodes(TreeNode node, Set<Integer> set, List<TreeNode> forest) {
        if (node == null) {
            return null;
        }

        node.left = deleteNodes(node.left, set, forest);
        node.right = deleteNodes(node.right, set, forest);

        // only possible in post order because we coming back from bottom to the top
        if (set.contains(node.val)) {
            if (node.left != null) {
                forest.add(node.left);
            }
            if (node.right != null) {
                forest.add(node.right);
            }
            return null;
        }

        return node;
    }
}
