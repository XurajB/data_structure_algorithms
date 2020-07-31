package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 */
public class ConstructBTFromInorderAndPostorder {
    Map<Integer, Integer> map;
    int[] postorder;
    int[] inorder;
    int postIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        return helper(0, postorder.length-1);
    }

    private TreeNode helper(int left, int right) {
        if (left > right) {
            return null;
        }
        int nodeVal = postorder[postIndex--];
        int index = map.get(nodeVal);
        // simulate postorder
        TreeNode r = helper(index+1, right);
        TreeNode l = helper(left, index-1);
        TreeNode node = new TreeNode(nodeVal);

        node.right = r;
        node.left = l;

        return node;

    }
}
