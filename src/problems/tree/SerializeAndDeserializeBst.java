package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree.
 */
public class SerializeAndDeserializeBst {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                sb.append("x,");
            } else {
                sb.append(root.val).append(",");
                q.offer(root.left);
                q.offer(root.right);
            }
        }

        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        String[] splits = data.split(",");
        if (splits[0].equals("x")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(splits[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = null;
            TreeNode right = null;

            if (!splits[i].equals("x")) {
                left = new TreeNode(Integer.parseInt(splits[i]));
                queue.offer(left);
            }
            i++;
            if (!splits[i].equals(Integer.parseInt(splits[i]))) {
                right = new TreeNode(Integer.parseInt(splits[i]));
                queue.offer(right);
            }
            i++;

            node.left = left;
            node.right = right;
        }

        return root;
    }
}
