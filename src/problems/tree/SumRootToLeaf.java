package problems.tree;

import dataStructures.trees.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

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
        // if question is sum root to leaf: binary numbers (with each nodes only holding 0 or 1)
        // current = (current << 1) | root.val
        if (root.left == null && root.right == null) {
            sum += current;
        } else {
            traverse(root.left, current);
            traverse(root.right, current);
        }
    }

    // -----------
    // iterative
    public int sumNumbers2(TreeNode root) {
        int ret = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            TreeNode node = p.getKey();
            int value = p.getValue();
            if (node != null) {
                value = value * 10 + node.val;
                if (node.left == null && node.right == null) {
                    ret += value;
                }
                queue.add(new Pair(node.left, value));
                queue.add(new Pair(node.right, value));
            }
        }
        return ret;
    }
}
