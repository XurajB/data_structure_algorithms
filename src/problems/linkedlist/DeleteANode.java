package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 */
public class DeleteANode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val; // assign next val to this
        node.next = node.next.next; // delete next node
    }
}
