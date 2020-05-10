package problems.tree;

/**
 * Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
 * A subtree of a tree is the node which have at least 1 child plus all its descendants.
 * The average value of a subtree is the sum of its values, divided by the number of nodes.
 * https://leetcode.com/discuss/interview-question/349617
 */
public class MaximumAverageNode {

    // N-ary tree node
    private static class TreeNode {
        int val;
        TreeNode[] children;
    }

    double max = Integer.MIN_VALUE;
    TreeNode maxNode = null;
    public TreeNode maximumAverageNode(TreeNode root) {
        if (root == null) {
            return maxNode;
        }
        helper(root);
        return maxNode;
    }

    // int[] = {num, sum}
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }
        int sum = root.val;
        int num = 1;
        // because N-ary tree
        for (TreeNode node: root.children) {
            int[] current = helper(node);
            num += current[0];
            sum += current[1];
        }

        double avg = (double)sum/(double)num;
        if (avg > max) {
            max = avg;
            maxNode = root;
        }

        return new int[] {num, sum};
    }
}
