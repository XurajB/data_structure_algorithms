package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 */
public class RotateList {
    private ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int size = 1; // including head
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null) {
            size++;
            fast = fast.next;
        }

        // we need slow.next, go upto i > 1
        for (int i = size - k%size; i > 1; i--) {
            slow = slow.next;
        }

        // update current head
        fast.next = head;
        // new head
        head = slow.next;
        // break the link
        slow.next = null;

        return head;
    }
}
