package dataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Xuraj on 11/23/2019.
 *
 * Implementation of Queue using LinkedList. FIFO - first in first out
 */
public class MyQueue<K> implements Iterable<K> {
    private Node first;
    private Node last;
    private int size;

    public MyQueue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public K peek() {
        if (isEmpty()) {
            System.out.println("Empty queue");
            return null;
        }
        return first.value;
    }

    public void enqueue(K value) {
        Node oldLast = last;
        last = new Node(value);
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public K dequeue() {
        if (isEmpty()) {
            System.out.println("Empty queue");
            return null;
        }
        K value = first.value;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return value;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Empty queue");
        } else {
            Node temp = first;
            while (temp != null) {
                System.out.print(temp.value + " ");
                temp = temp.next;
            }
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MyQueueIterator();
    }

    private class MyQueueIterator implements Iterator<K> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            K value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            // not implemented
        }
    }

    private class Node {
        private K value;
        private Node next;
        Node(K value) {
            this.value = value;
        }
    }
}
