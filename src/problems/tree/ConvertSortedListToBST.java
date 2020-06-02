package problems.tree;

import dataStructures.ListNode;
import dataStructures.trees.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class ConvertSortedListToBST {
    private ListNode head;
    public static void main(String[] args) {

    }

    // O(n), O(logn)
    public TreeNode sortedListBst(ListNode head) {
        this.head = head;
        int size = findSize(head);

        return convertToBst(0, size - 1);
    }

    // find size so we know which is head (middle)
    private int findSize(ListNode head) {
        int size = 0;
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    private TreeNode convertToBst(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        // simulate in order traversal
        // start with left node
        TreeNode left = convertToBst(l, mid - 1);
        // process current node
        TreeNode node = new TreeNode(head.val);
        node.left = left;

        head = head.next;

        // process right
        node.right = convertToBst(mid + 1, r);

        return node;
    }
}
