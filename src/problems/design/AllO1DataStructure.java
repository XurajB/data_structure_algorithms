package problems.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implement a data structure supporting the following operations:
 *
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
 * If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 */
public class AllO1DataStructure {
    // head and tail to ensure getMax and getMinKey done in O(1)
    private Node head; // min
    private Node tail; // max
    private Map<Integer, Node> countKeys; // count, keys
    private Map<String, Integer> keyCount; // key , count

    /** Initialize your data structure here. */
    public AllO1DataStructure() {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;

        keyCount = new HashMap<>();
        countKeys = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int oldCount = keyCount.getOrDefault(key, 0);

        int count = oldCount + 1;
        keyCount.put(key, count);

        // add key to new count
        countKeys.putIfAbsent(count, new Node(count));
        Node node = countKeys.get(count);
        node.keys.add(key);

        // remove from old count
        Node oldNode = head;
        if (oldCount > 0) {
            oldNode = countKeys.get(oldCount);
            oldNode.keys.remove(key);
        }

        // add new node to list
        // bring old node in front of node (new hode has higher count)
        if (node.keys.size() == 1) {
            node.next = oldNode.next;
            node.prev = oldNode;
            oldNode.next.prev = node;
            oldNode.next = node;
        }

        // remove old node from list if empty
        if (oldNode != head && oldNode.keys.isEmpty()) {
            oldNode.prev.next = node;
            node.prev = oldNode.prev;
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!keyCount.containsKey(key)) {
            return;
        }

        int oldCount = keyCount.get(key);
        if (oldCount == 1) {
            keyCount.remove(key);
        }

        int count = oldCount - 1;
        keyCount.put(key, count);

        // add key to new count
        Node node = head;
        if (count > 0) {
            countKeys.putIfAbsent(count, new Node(count));
            node = countKeys.get(count);
            node.keys.add(key);
        }

        // remove key from old count
        Node oldNode = countKeys.get(oldCount);
        oldNode.keys.remove(key);

        // add new node to list
        if (node.keys.size() == 1) {
            node.next = oldNode;
            node.prev = oldNode.prev;
            oldNode.prev.next = node;
            oldNode.prev = node;
        }

        // remove old node from list if empty
        if (oldNode != tail && oldNode.keys.isEmpty()) {
            oldNode.next.prev = node;
            node.next = oldNode.next;
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (tail.prev.keys.isEmpty()) {
            return "";
        }
        return tail.prev.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next.keys.isEmpty()) {
            return "";
        }
        return head.next.keys.iterator().next();
    }

    static class Node {
        Node prev;
        Node next;
        Set<String> keys = new HashSet<>();
        int count;

        Node (int count) {
            this.count = count;
        }
    }
}
