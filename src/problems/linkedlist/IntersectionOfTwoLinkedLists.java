package problems.linkedlist;

import dataStructures.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 */
public class IntersectionOfTwoLinkedLists {
    // O(A + B), O(A)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> nodes = new HashSet<>();
        ListNode pa = headA;
        while (pa != null) {
            nodes.add(pa);
            pa = pa.next;
        }
        if (nodes.isEmpty()) {
            return null;
        }
        ListNode pb = headB;
        while (pb != null) {
            if (nodes.contains(pb)) {
                return pb;
            }
            pb = pb.next;
        }
        return null;
    }

    // O(A + B), O(1)
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode pa = headA;
        ListNode pb = headB;
        while (pa != pb) {
            pa = (pa != null) ? pa.next : headB;
            pb = (pb != null) ? pb.next : headA;
        }
        return pa;
    }
}
