package dataStructures;

/**
 * Created by Xuraj on 11/23/2019.
 *
 * Stack implementation using LinkedList. LIFO - last in first out.
 */
public class MyStack<K> {
    private int size;
    private Node first;

    public MyStack() {
        first = null;
        size = 0;
    }

    private boolean isEmpty() {
        return first == null;
    }

    public void push(K data) {
        Node previous = first;
        first = new Node(data);
        first.next = previous;
        size++;
    }

    public K pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

        K data = first.data;
        first = first.next;
        size--;

        return data;
    }

    public K peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

        return first.data;
    }

    public void print() {
        if (first == null) {
            System.out.println("Empty stack");
        }
        Node temp = first;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private class Node {
        private K data;
        private Node next;

        Node(K data) {
            this.data = data;
        }
    }
}
