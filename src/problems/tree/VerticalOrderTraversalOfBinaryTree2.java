package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * Same as VerticalOrderTraversalOfBinaryTree - except we don't need to sort by val if row and col of two nodes are equal
 */
public class VerticalOrderTraversalOfBinaryTree2 {
    // O(N), O(N)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        Map<Integer, List<Integer>> colTable = new HashMap<>();
        // bfs
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0));

        // we can either sort the colTable based on col or keep min and max col
        // sort would increase time to O(NLogN)
        int minCol = 0;
        int maxCol = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int col = node.col;
            if (!colTable.containsKey(node.col)) {
                colTable.put(col, new ArrayList<>());
            }
            colTable.get(col).add(node.node.val);
            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol, col);
            if (node.node.left != null) {
                queue.offer(new Node(node.node.left, col - 1));
            }
            if (node.node.right != null) {
                queue.offer(new Node(node.node.right, col + 1));
            }
        }
        for (int i = minCol; i <= maxCol; i++) {
            answer.add(colTable.get(i));
        }
        return answer;
    }

    static class Node {
        TreeNode node;
        int col;
        Node(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }
}
