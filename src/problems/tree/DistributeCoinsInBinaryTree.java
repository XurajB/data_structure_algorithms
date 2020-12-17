package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)
 * Return the number of moves required to make every node have exactly one coin.
 */
public class DistributeCoinsInBinaryTree {
    // return number of coins given to the parents
    // the helper function returns the amount of coins each node have in access or need. For each node, it will try to balance the amount of coints
    // it will return +ve if there is an access and -ve if current node or its children need coins
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return steps;
    }
    int steps = 0;

    // returns coins of this level
    // post order
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // coin from children
        int coin = dfs(root.left) + dfs(root.right);

        // current node coin
        if (root.val == 0) {
            coin += 1; // need
        } else {
            coin += 1 - root.val; // access
        }

        steps += Math.abs(coin);
        return coin;
    }
}
