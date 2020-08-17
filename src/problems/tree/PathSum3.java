package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.HashMap;

/**
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 */
public class PathSum3 {
    int count = 0;
    int sum;
    HashMap<Integer, Integer> map = new HashMap<>();

    // since the sum can start from any node and end at any node, we need to consider using prefix sum
    // counting from top to bottom, preorder
    // O(N), O(N)
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        this.sum = sum;
        // preorder because we are summing from root to nodes
        preorder(root, 0);
        return count;
    }

    private void preorder(TreeNode node, int currSum) {
        if (node == null) {
            return;
        }
        // prefix sum
        currSum += node.val;
        // prefix sum = sum starts from the root
        if (currSum == sum) {
            count++;
        }
        // current prefix starts somewhere downward
        // number of times currSum-sum has occured already
        // is the number of times a path with sum sum has occured upto current node
        count += map.getOrDefault(currSum - sum, 0);
        // add current sum into hashmap to use it during the child node processing
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        preorder(node.left, currSum);
        preorder(node.right, currSum);
        // remove current sum from hashmap, so as not to use it during parallel subtree processing
        map.put(currSum, map.get(currSum)-1);
    }
}
