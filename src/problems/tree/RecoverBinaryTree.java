package problems.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * https://leetcode.com/problems/recover-binary-search-tree/
 */
public class RecoverBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // for O(1) space
    // Morrise traversal: TODO

    static class RecoverTree1 {
        // do in order traversal
        // find which ones are swapped
        // traverse tree and swap them

        // time: O(N), space: O(N)
        public void recoverTree(TreeNode root) {
            if (root == null) {
                return;
            }
            List<Integer> nums = new ArrayList<>();
            inOrder(root, nums);
            int[] swapped = findSwapped(nums);
            recover(root, 2, swapped[0], swapped[1]);
        }

        private void recover(TreeNode node, int count, int x, int y) {
            if (node != null) {
                if (node.val == x || node.val == y) {
                    node.val = node.val == x ? y : x;
                    if (count == 0) {
                        return;
                    }
                }
                recover(node.left, count - 1, x, y);
                recover(node.right, count - 1, x, y);
            }
        }

        // 1 5 3 4 2 6
        private int[] findSwapped(List<Integer> nums) {
            int x = -1;
            int y = -1;
            for (int i = 0; i < nums.size() - 1; i++) {
                if (nums.get(i+1) < nums.get(i)) {
                    y = nums.get(i + 1);
                    if (x == -1) {
                        x = nums.get(i);
                    } else {
                        break;
                    }
                }
            }
            return new int[] {x, y};
        }

        private void inOrder(TreeNode node, List<Integer> nums) {
            if (node != null) {
                inOrder(node.left, nums);
                nums.add(node.val);
                inOrder(node.right, nums);
            }
        }
    }

    // same principal as above, but better space
    // O(N), O(logN) - height for stack
    static class RecoverTree2 {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = new TreeNode(Integer.MIN_VALUE); // to prevent npe while comparing

        public void recoverTree(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root);
            // swap
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

        // bst in-order is left, node, right. so previous value always be smaller than current (sorted order)
        private void inOrder(TreeNode root) {
            if (root != null) {
                inOrder(root.left);

                if (first == null && prev.val > root.val) {
                    first = prev;
                }
                if (first != null && prev.val > root.val) {
                    second = root;
                }
                prev = root;

                inOrder(root.right);
            }
        }
    }
}
