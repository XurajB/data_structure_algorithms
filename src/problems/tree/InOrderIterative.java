package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * In order traversal iteratively
 */
public class InOrderIterative {
    public static void main(String[] args) {

    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;

        while (node != null || stack.size() > 0) {
            while (node != null) {
                // traverse left tree all the way
                stack.push(node);
                node = node.left;
            }

            node = stack.pop(); // node is null at this point
            System.out.println(node.val);

            // we visited left subtree, now visit right
            node = node.right;
        }
    }
}
