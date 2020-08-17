package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.HashMap;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 */
public class HouseRobber3 {
    // naive solution
    // this is exponential because function gets called from children and grandchildren recursively, T(d) = T(d-1) + T(d-2)
    private static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // get values from grandchildren, because children is not allowed to get robed if you rob current node
        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }

        // take the path that is maximum
        return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }

    // better solution
    // O(N)
    // the above calculation has a lot of overlaps. if we need to calculate rob(root),
    // then we need rob(root.left), rob(root.right), rob(root.left.left), rob(root.left.right), rob(root.right.left), rob(root.right.right).
    // similarly to get rob(root.left) we also need rob(root.left.left), rob(root.left.right) and so on.
    // naive way to implement dp is use a hash
    private static int rob2(TreeNode root) {
        return helper(root, new HashMap<>());
    }

    private static int helper(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }

        // get values from grandchildren, because children is not allowed to get robed if you rob current node
        int val = 0;
        if (root.left != null) {
            val += helper(root.left.left, map) + helper(root.left.right, map);
        }
        if (root.right != null) {
            val += helper(root.right.left, map) + helper(root.right.right, map);
        }

        val = Math.max(val + root.val, helper(root.left, map) + helper(root.right, map));
        map.put(root, val);

        return val;
    }
}
