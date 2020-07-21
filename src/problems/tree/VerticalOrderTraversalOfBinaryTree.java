package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */
public class VerticalOrderTraversalOfBinaryTree {
    List<Node> nodes = new ArrayList<>();

    // O(NLogN)
    // O(N)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }

        // if root is at: row, col
        // left child will be: row + 1, col - 1
        // right child will be: row + 1, col + 1
        // construct a list with this value from either bfs or dfs
        // then sort it based on col -> row -> value (lower value should be in front if they have same row and col)
        // generate answer by comparing current col
        dfs(root, 0, 0);

        // value must be sorted if row and col are same.
        Collections.sort(nodes, (n1, n2) -> {
            if (n1.col == n2.col) {
                if (n1.row == n2.row) {
                    return n1.node.val - n2.node.val;
                } else {
                    return n1.row - n2.row;
                }
            } else {
                return n1.col - n2.col;
            }
        });

        int colIndex = nodes.get(0).col;
        List<Integer> curCol = new ArrayList<>();
        for (Node node: nodes) {
            if (colIndex == node.col) {
                curCol.add(node.node.val);
            } else {
                answer.add(curCol);
                colIndex = node.col;
                curCol = new ArrayList<>();
                curCol.add(node.node.val);
            }
        }

        // add remaining col
        answer.add(curCol);
        return answer;
    }

    // it does not matter what traversal method
    private void dfs(TreeNode root, int row, int col) {
        if (root == null) {
            return;
        }
        nodes.add(new Node(root, row, col));
        dfs(root.left, row + 1, col - 1);
        dfs(root.right, row + 1, col + 1);
    }

    static class Node {
        TreeNode node;
        int row;
        int col;
        Node(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
}
