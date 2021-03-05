package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 */
public class RemoveLinkedListElements {
    private ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = head; // note
        ListNode last = dummy;
        while (cur != null) {

            if (cur.val != val) {
                last.next = cur;
                last = cur;
            }
            cur = cur.next;
        }

        // update last one
        last.next = cur;

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
