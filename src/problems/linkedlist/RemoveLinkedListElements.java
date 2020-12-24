package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 */
public class RemoveLinkedListElements {
    private ListNode removeElements(ListNode head, int val) {
        // we use a dummy node to make things simpler if the val is in head and prev node being null
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = head;
        ListNode prev = dummy;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
            } else {
                // there can be consecutive nodes with val, so only update prev here
                prev = current;
            }
            current = current.next;
        }

        return dummy.next;
    }

    // similar
    public ListNode removeElements2(ListNode head, int val) {
        ListNode node = head;
        ListNode prev = null;

        while (node != null) {
            if (node.val == val) {
                if (prev == null) {
                    head = head.next;
                } else {
                    prev.next = node.next;
                }
            } else {
                prev = node;
            }
            node = node.next;
        }

        return head;
    }
}
