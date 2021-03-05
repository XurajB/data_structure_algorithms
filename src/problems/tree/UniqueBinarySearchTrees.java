package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n? (sorted)
 */
public class UniqueBinarySearchTrees {
    // related to n catalan number
    public int numTrees(int n) {
        // if we pick a root, then the total trees we can calculate
        // will be product of num of trees by left side and right side
        // 1 2 3 4 .. i-1 i i+1 .. n
        // total number trees at F(i) = F(i-1) * F(n-i); - which is product of total trees on left subtree and right subtree
        // we can formulate: F(n) = F(i-1) * F(n-i)
        int[] dp = new int[n+1];
        // there is only one unique BST out of a sequence of length 1 (only a root) or nothing (empty tree)
        dp[0] = 1;
        dp[1] = 1;
        // we can use dp: how many trees can be constructed from {2,3,4,5} - it is the same number as {1,2,3,4}
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

    //// top down with memo
    public int numTrees2(int n) {
        Integer[][] memo = new Integer[n][n];
        return helper(0, n-1, memo); // or 1, n and memo size n+1
    }

    private int helper(int left, int right, Integer[][] memo) {
        if (left >= right) {
            return 1;
        }

        if (memo[left][right] != null) {
            return memo[left][right];
        }

        int count = 0;
        for (int i = left; i <= right; i++) {
            count += helper(left, i-1, memo) * helper(i+1, right, memo);
        }

        memo[left][right] = count;
        return count;
    }

    //////////////////
    // Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
    public List<TreeNode> generateTrees(int n) {
        // if we pick a root, then the total trees we can calculate
        // will be product of num of trees by left side and right side
        // we can formulate: F(n) = F(i-1) * F(n-i)
        if (n == 0) {
            return new LinkedList<>();
        }
        return generate(1, n);
    }

    // 1.. n is the in order traversal in BST. so if I pick i as root, left will be 1 to  i-1 and right will be i+1 to n
    private LinkedList<TreeNode> generate(int start, int end) {
        LinkedList<TreeNode> allTrees = new LinkedList<>();
        // base case
        if (start > end) {
            allTrees.add(null); // this mean no child
            return allTrees;
        }

        // pick a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees when i is root
            LinkedList<TreeNode> left = generate(start, i-1);
            // all possible right subtrees
            LinkedList<TreeNode> right = generate(i+1, end);

            // connect left and right trees to root i
            for (TreeNode l: left) {
                for (TreeNode r: right) {
                    TreeNode current = new TreeNode(i); // root
                    current.left = l;
                    current.right = r;
                    allTrees.add(current);
                }
            }
        }

        return allTrees;
    }
}
