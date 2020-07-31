package problems.linkedlist;

import dataStructures.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a linked list, determine if it has a cycle in it.
 */
public class LinkedListCycle {
    // O(N), O(N)
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    ///////////
    // O(N), O(1)
    public boolean hasCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // note we are checking fast and fast.next for null
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
