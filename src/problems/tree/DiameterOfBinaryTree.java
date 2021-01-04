package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 */
public class DiameterOfBinaryTree {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return ans - 1; // we got nodes, path in between is nodes-1
    }
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        ans = Math.max(ans, left+right+1);
        return Math.max(left, right) + 1;
    }

    // iterative
    public int diameterOfBinaryTree2(TreeNode root) {
        int maxDiameter = 0;
        if(root == null) {
            return maxDiameter;
        }

        Stack<TreeNode> path = new Stack<>();
        // use a hashmap to keep track of node heights as we backtrack
        // since postorder processes child first, we can always access a child's height once we're at a parent
        HashMap<TreeNode, Integer> nodeHeight = new HashMap<>();
        TreeNode current = root;
        TreeNode prev = null;
        while(current != null || !path.isEmpty()) {
            // postorder so go all the way left
            while(current != null) {
                path.push(current);
                current = current.left;
            }
            current = path.peek();
            // do we have a right child? if so process the right child before the current parent
            // we use prev so we don't end up in an infinite loop of processing right child, going back to parent and back to right               // child
            if(current.right != null && current.right != prev) {
                current = current.right;
                continue;
            }
            path.pop();
            // get the left and right heights
            int leftHeight = current.left == null ? 0 : nodeHeight.get(current.left)+1;
            int rightHeight = current.right == null ? 0 : nodeHeight.get(current.right)+1;
            // update the max diameter as we backtrack
            maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
            // process the current node by puttin into the hm so its parents have access to height
            nodeHeight.put(current, Math.max(leftHeight, rightHeight));
            prev = current;
            // need to set current to null otherwise we will keep processing the same node in the while loop on line 31
            current = null;
        }
        return maxDiameter;
    }
}
