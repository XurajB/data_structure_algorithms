package leetcode;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean isOdd = true;
        // bfs
        while (!q.isEmpty()) {
            int level = q.size();
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < level; i++) {
                TreeNode node = q.poll();
                values.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            isOdd = !isOdd;
            if (isOdd) {
                Collections.reverse(values);
            }
            ans.add(values);
        }
        return ans;
    }
}
