package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
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

        // bfs goes from left to right - so elements are already in ltr if same col
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

    //////////////////////////////////////////
    //////////////////////////////////////////
    // dfs, sorting is extra
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Node2> nodes = new ArrayList<>();
        dfs(root, nodes, 0, 0);

        Collections.sort(nodes, (a, b) -> {
            if (a.col == b.col) {
                return a.row - b.row;
            }
            return a.col - b.col;
        });

        int index = nodes.get(0).col;
        List<Integer> line = new ArrayList<>();
        for (Node2 node: nodes) {
            if (node.col == index) {
                line.add(node.node.val);
            } else {
                ans.add(line);
                index = node.col;
                line = new ArrayList<>();
                line.add(node.node.val);
            }
        }
        ans.add(line);
        return ans;
    }

    private void dfs(TreeNode root, List<Node2> nodes, int row, int col) {
        if (root == null) {
            return;
        }
        nodes.add(new Node2(root, row, col));
        dfs(root.left, nodes, row+1, col-1);
        dfs(root.right, nodes, row+1, col+1);
    }
    static class Node2 {
        int row;
        int col;
        TreeNode node;
        Node2(TreeNode node, int row, int col) {
            this.row = row;
            this.col = col;
            this.node = node;
        }
    }
}
