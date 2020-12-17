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
 * We need either preorder (first root) and postorder (last) so we can identify root
 * Now we can use inorder to find out left and right subtrees (left and right side of root)
 *
 * However- for BST - we can create a tree using only preorder, since BSt has lower and upper limit properties
 */
public class ConstructBstFromPreorder {
    int[] inorder;
    int[] preorder;
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

    private TreeNode helper(int left, int right) {
        if (left > right) {
            return null;
        }

        // simulate preorder
        TreeNode node = new TreeNode(preorder[index]);
        // split into two halfs
        int inIndex = map.get(preorder[index++]);
        // build left first (For: inorder + postorder: build right tree first if constructing from postorder, start index from length-1)
        node.left = helper(left, inIndex-1);
        // build right
        node.right = helper(inIndex+1, right);

        return node;
    }

    // ----------- using only preorder
    int[] preorder1;
    int n;
    int index;
    private TreeNode bstFromPreorder2(int[] preorder) {
        this.preorder1 = preorder;
        n = preorder.length;
        index = 0;
        return helper2(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode helper2(int lower, int upper) {
        if (index == n) {
            // we used all elements
            return null;
        }
        int val = preorder1[index++];
        // if it is not within limit then return null
        if (val > upper || val < lower) {
            return null;
        }
        TreeNode node = new TreeNode(val);
        node.left = helper2(lower, val);
        node.right = helper2(val, upper);

        return node;
    }
 }
