package dataStructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes’ values. (ie, from left to right, level by level).
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * levels -
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class LevelOrder {
    public static void main(String[] args) {
        // implement
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<List<Integer>> answer = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> elem = new ArrayList<>();
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    elem.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            answer.add(elem);
        }
        return answer;
    }
}
