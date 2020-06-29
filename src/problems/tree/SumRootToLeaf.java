package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25 (12 + 13)
 */
public class SumRootToLeaf {
    public static void main(String[] args) {
        //
    }
    private int sum = 0;
    // O(N), O(H)
    private int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverse(root, 0);
        return sum;
    }

    // pre order traversal
    private void traverse(TreeNode root, int current) {
        if (root == null) {
            return;
        }
        current = current * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += current;
        } else {
            traverse(root.left, current);
            traverse(root.right, current);
        }
    }
}
