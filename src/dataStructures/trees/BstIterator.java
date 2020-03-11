package dataStructures.trees;

import java.util.Iterator;
import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * The first call to next() will return the smallest number in BST. Calling next() again will return the next smallest number in the BST, and so on.
 * https://www.interviewbit.com/problems/bst-iterator/
 */
public class BstIterator implements Iterator<Integer> {
    static Stack<TreeNode> stack;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1); // implement
        stack = new Stack<>();
        TreeNode current = root;

        while (current != null) { // or use leftMost(current)
            stack.push(current);
            current = current.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public Integer next() {
        if (!hasNext()) {
            return -1;
        }

        TreeNode node = stack.pop();
        int val = node.val;

        node = node.right;
        while (node != null) { // or use leftMost(node.right)
            stack.push(node);
            node = node.left;
        }

        return val;
    }

    // this method can be shared
    private static void leftMost(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
