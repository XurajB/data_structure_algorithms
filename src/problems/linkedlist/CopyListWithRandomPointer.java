package problems.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {
    // hashmap to store old node and key and new node as value
    Map<Node, Node> visited = new HashMap<>();

    // Time: O(N), space: O(N)
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node oldNode = head;
        Node newNode = new Node(oldNode.val);

        // put in hashmap
        visited.put(oldNode, newNode);

        // iterate the list until all nodes are cloned
        while (oldNode != null) {
            // get the clones of the nodes referenced by random and next pointers
            newNode.next = getClonedCopy(oldNode.next);
            newNode.random = getClonedCopy(oldNode.random);

            // move one step ahead in the list
            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return visited.get(head);
    }

    private Node getClonedCopy(Node node) {
        if (node == null) {
            return null;
        }

        if (!visited.containsKey(node)) {
            visited.put(node, new Node(node.val));
        }
        return visited.get(node);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
