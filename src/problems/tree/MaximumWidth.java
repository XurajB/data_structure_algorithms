package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels.
 * The binary tree has the same structure as a full binary tree, but some nodes are null.
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
 * where the null nodes between the end-nodes are also counted into the length calculation.
 */
public class MaximumWidth {
    public static void main(String[] args) {
        //
    }

    // O(N)
    // we need to index each node so we can calculate width
    // we can use the formula: left node: index * 2, right node: index * 2 + 1
    // left most node will always be 0, rightmost will be max for that level
    private static int widthOfBinaryTree(TreeNode root) {
        int width = 0;
        if (root == null) {
            return width;
        }
        Queue<MyNode> q = new LinkedList<>();
        q.offer(new MyNode(root, 0));
        while (!q.isEmpty()) {
            // get the left and right nodes to calculate width
            MyNode left = q.peek();
            MyNode node = null;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                node = q.poll();
                if (node.treeNode.left != null) {
                    q.offer(new MyNode(node.treeNode.left, node.index * 2));
                }
                if (node.treeNode.right != null) {
                    q.offer(new MyNode(node.treeNode.right, node.index * 2 + 1));
                }
            }
            // calculate width for the current level and note max
            width = Math.max(width, node.index - left.index + 1);
        }
        return width;
    }

    static class MyNode {
        TreeNode treeNode;
        int index;
        MyNode(TreeNode treeNode, int index) {
            this.treeNode = treeNode;
            this.index = index;
        }
    }
}
