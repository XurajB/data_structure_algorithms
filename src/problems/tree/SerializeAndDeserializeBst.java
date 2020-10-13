package problems.tree;

import dataStructures.trees.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.*;

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
                sb.append(node.val).append(",");
                q.offer(node.left);
                q.offer(node.right);
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
        int i = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            TreeNode left = null;
            TreeNode right = null;

            if (!splits[i].equals("x")) {
                left = new TreeNode(Integer.parseInt(splits[i]));
                q.offer(left);
            }
            i++;
            if (!splits[i].equals("x")) {
                right = new TreeNode(Integer.parseInt(splits[i]));
                q.offer(right);
            }
            i++;

            node.left = left;
            node.right = right;
        }
        return root;
    }

    /////////////////////
    /// recursive
    private String serialize2(TreeNode root) {
        return rSerialize(root);
    }

    // preorder
    private String rSerialize(TreeNode root) {
        if (root == null) {
            return "x,";
        }

        String str = root.val + ",";
        str += rSerialize(root.left);
        str += rSerialize(root.right);

        return str;
    }

    private TreeNode deserialize2(String data) {
        String[] splits = data.split(",");
        Queue<String> dataSplits = new LinkedList<>(Arrays.asList(splits));
        return rDeserialize(dataSplits);
    }

    private TreeNode rDeserialize(Queue<String> dataSplits) {
        if (dataSplits.peek().equals("x")) {
            dataSplits.poll();
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(dataSplits.poll()));
        node.left = rDeserialize(dataSplits);
        node.right = rDeserialize(dataSplits);
        return node;
    }
}
