package leetcode;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        // implement
    }

    private static List<Integer> rightSideView(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int level = q.size();
            for (int i = 0; i < level; i++) {
                TreeNode node = q.poll();
                if (i == level - 1) {
                    answer.add(node.val);
                }
                if (node != null) {
                    if (node.left != null) {
                        q.offer(node.left);
                    }

                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }
            }

        }
        return answer;
    }
}
