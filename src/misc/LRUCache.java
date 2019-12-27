package misc;

import java.util.HashMap;

/**
 * Created by Xuraj on 12/26/2019.
 *
 * LRU (least recently used) cache -  eviction policy.
 * Should have efficient insert and lookup - O(1)
 * Other types - FIFO, LIFO, MRU (most recently used)
 *
 * We are using DoublyLinkedList and HashMap. HashMap for fast lookup and insertion,
 * doubly linked list for fast delete.
 *
 * Requirements: Fixed size, recently used items are brought to the front, least used
 * items are removed if out of memory (size).
 */
public class LRUCache<K, V> {
    private HashMap<K, Node> map;
    private Node head, tail;
    private int cacheSize;

    LRUCache(int size) {
        map = new HashMap<>(size);
        this.cacheSize = size;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> myCache = new LRUCache<>(4);
        myCache.put(1, 4);
        myCache.put(2, 6);
        System.out.println(myCache.get(2));
        myCache.put(2, 9);
        System.out.println(myCache.get(2));
        myCache.delete(2);
        System.out.println(myCache.get(2));
        myCache.put(4, 0);
        myCache.put(7, 9);
        myCache.put(6, 9);
        myCache.put(5, 9);
        System.out.println(myCache.get(4));
        System.out.println(myCache.get(1));
        System.out.println(myCache.currentSize());
    }

    private int currentSize() {
        return map.size();
    }

    private void put(K key, V value) {
        // if exists update value, bring to font
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            deleteNode(node);
            addToFront(node);
        } else {
            node = new Node(key, value);
            if (map.size() == cacheSize) {
                // remove least used
                map.remove(tail.key);
                deleteNode(tail);
            }
            // add to front
            addToFront(node);
            map.put(key, node);
        }
    }

    private void delete(K key) {
        Node node = map.get(key);
        if (node != null) {
            deleteNode(node);
            map.remove(key);
        }
    }

    private V get(K key) {
        Node node = map.get(key);
        if (node != null) {
            // remove and move to top
            deleteNode(node);
            addToFront(node);
            return node.value;
        }
        return null;
    }

    private void addToFront(Node node) {
        node.next = head;
        node.previous = null;
        if (head != null) {
            head.previous = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    private void deleteNode(Node node) {
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;
        }
    }

    class Node {
        private Node previous;
        private Node next;
        private V value;
        private K key;

        Node(K key, V value) {
            this.value = value;
            this.key = key;
            previous = null;
            next = null;
        }
    }
}
