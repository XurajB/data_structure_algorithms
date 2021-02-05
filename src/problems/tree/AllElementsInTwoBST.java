package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 */
public class AllElementsInTwoBST {
    // O(M + N)
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        while (root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
            while (root1 != null) {
                stack1.push(root1);
                root1 = root1.left;
            }
            while (root2 != null) {
                stack2.push(root2);
                root2 = root2.left;
            }
            if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peek().val < stack2.peek().val)) {
                root1 = stack1.pop();
                ans.add(root1.val);
                root1 = root1.right;
            } else {
                root2 = stack2.pop();
                ans.add(root2.val);
                root2 = root2.right;
            }
        }
        return ans;
    }
}
