package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * Note:
 * You cannot construct tree only from inorder because we won't know root,
 * We need either reorder (first root) and postorder (last) so we can identify root
 * Now we can use inorder to find out left and right subtrees (left and right side of root)
 *
 * You cannot construct tree if you have preorder and postorder. You need inorder + pre/post
 * Only works when we have non-duplicate nodes
 */
public class ConstructBstFromPreorder {
    int[] inorder;
    int[] preorder;
    int preIndex = 0;
    Map<Integer, Integer> map = new HashMap<>();

    // O(nLogn)
    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorder = preorder;
        inorder = new int[preorder.length];
        for (int i = 0; i < preorder.length; i++) {
            inorder[i] = preorder[i];
        }
        Arrays.sort(inorder);

        int i = 0;
        // in order map
        for (int val: inorder) {
            map.put(val, i++);
        }
        return helper(0, inorder.length);
    }

    private TreeNode helper(int l, int r) {
        if (l == r) {
            return null;
        }
        int rootVal = preorder[preIndex];
        TreeNode root = new TreeNode(rootVal);
        // root splits inorder list into left and right subtrees
        int index = map.get(rootVal);
        // for recursion
        preIndex++;
        // build left
        root.left = helper(l, index);
        // build right
        root.right = helper(index+1, r);
        return root;
    }
}
