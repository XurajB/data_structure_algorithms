package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode smallerHead = new ListNode(-1);
        ListNode biggerHead = new ListNode(-1);
        ListNode small = smallerHead;
        ListNode big = biggerHead;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        small.next = biggerHead.next;
        big.next = null;
        return smallerHead.next;
    }
}
