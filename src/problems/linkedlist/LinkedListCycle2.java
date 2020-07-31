package problems.linkedlist;

import dataStructures.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a linked list, return the node where the cycle begins, if not return null
 */
public class LinkedListCycle2 {
    // O(N), O(N)
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    ///////////---------
    // O(N), O(1)
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        // step 1: find fast and slow intersection point if there is a cycle
        ListNode intersect = getIntersection(head);
        if (intersect == null) {
            return null;
        }
        // step 2: move two pointers from head and intersection until they are equal
        ListNode l1 = head;
        ListNode l2 = intersect;
        while (l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }

    private ListNode getIntersection(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
}
