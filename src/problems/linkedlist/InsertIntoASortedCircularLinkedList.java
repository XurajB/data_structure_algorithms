package problems.linkedlist;

/**
 * Given a node from a Circular Linked List which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list.
 * The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the circular list.
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.
 *
 * If the list is empty (i.e., given node is null), you should create a new single circular list and return the reference to that single node. Otherwise,
 * you should return the original given node.
 */
public class InsertIntoASortedCircularLinkedList {
    // O(n)
    public Node insert(Node head, int insertVal) {
        // case 1, head is null
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        // case 2, insert location is between min and max (lower than min and higher than max)
        Node max = head;
        while (max.next.val >= max.val && max.next != head) {
            max = max.next;
        }

        Node min = max.next;
        if (insertVal >= max.val || insertVal <= min.val) {
            max.next = new Node(insertVal, min);
        } else {
            // case 3, anywhere else (higher than min and lower than max)
            Node cur = min;
            while (cur.next.val < insertVal) {
                cur = cur.next;
            }
            cur.next = new Node(insertVal, cur.next);
        }

        return head;
    }

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };
}
