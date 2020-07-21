package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FindLeaves {
    public static void main(String[] args) {
        //
    }
    private List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        getHeight(root, ans);
        return ans;
    }

    private int getHeight(TreeNode root, List<List<Integer>> ans) {
        if (root == null) {
            return -1; // neutralize with 1 + Max(l,r)
        }
        int left = getHeight(root.left, ans);
        int right = getHeight(root.right, ans);

        int height = 1 + Math.max(left, right);
        if (height == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(height).add(root.val);
        return height;
    }
}
