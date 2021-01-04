package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    // O(N) time
    // O(hln) => number of leaves (l) * max path length (n) * height (height)
    private void constructPath(TreeNode node, String path, List<String> ans) {
        if (node == null) {
            return;
        }
        path = path + node.val;
        if (node.left == null && node.right == null) { // if leaf
            ans.add(path);
        } else {
            path = path + "->"; // JVM will use StringBuilder instead in byte code, need research
            constructPath(node.left, path, ans);
            constructPath(node.right, path, ans);
        }
    }

    // --------
    // iterative
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // use two stacks or use an wrapper object
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<String> pathStack = new Stack<>();
        nodeStack.push(root);
        pathStack.push(Integer.toString(root.val));
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            String path = pathStack.pop();
            if (node.left == null && node.right == null) {
                ans.add(path);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
                pathStack.push(path + "->" +node.left.val);
            }
            if (node.right != null) {
                nodeStack.push(node.right);
                pathStack.push(path + "->" +node.right.val);
            }
        }
        return ans;
    }
}
