package problems.linkedlist;

import dataStructures.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 */
public class IntersectionOfTwoLinkedList {
    // compare nodes not values
    // approach 1: O(n*m)
    private ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode temp = headB;
            while (temp != null) {
                if (headA == temp) {
                    return headA;
                }
                temp = temp.next;
            }
            headA = headA.next;
        }
        return null;
    }

    // approach 2: set, O(N), O(N)
    private ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    // approach 3: two pointers, O(m+n), O(1)
    private ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        // same concept as finding cycle
        ListNode a = headA;
        ListNode b = headB;

        // we will end loop once both become null or in two iteration when there is no intersection
        while (a != b) {
            // when first iteration ends, we reset pointer to the head of another list
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }
}
