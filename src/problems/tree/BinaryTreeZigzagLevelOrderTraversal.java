package problems.tree;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    // O(N)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean ltr = false;
        // bfs
        while (!q.isEmpty()) {
            int level = q.size();
            LinkedList<Integer> values = new LinkedList<>();
            for (int i = 0; i < level; i++) {
                TreeNode node = q.poll();
                if (ltr) {
                    values.addFirst(node.val);
                } else {
                    values.add(node.val);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ltr = !ltr;
            ans.add(values);
        }
        return ans;
    }
}
