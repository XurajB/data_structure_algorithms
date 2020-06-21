package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths
 */
public class BinaryTreePaths {
    public static void main(String[] args) {

    }

    private List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        constructPath(root, "", ans);
        return ans;
    }

    private void constructPath(TreeNode node, String path, List<String> ans) {
        if (node == null) {
            return;
        }
        path = path + node.val;
        if (node.left == null && node.right == null) { // if leaf
            ans.add(path);
        } else {
            path = path + "->";
            constructPath(node.left, path, ans);
            constructPath(node.right, path, ans);
        }
    }
}
