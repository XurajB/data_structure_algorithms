package dataStructures;

/**
 * Created by Xuraj on 11/20/2019.
 *
 * Singly linkedlist implementation
 */
public class MyLinkedList {

    private Node head;

    // static so main() has access to it
    // we are assuming data type to be integer but it can be anything
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    // insertion - O(n)
    public MyLinkedList insert(int data) {
        Node newNode = new Node(data);

        if (this.head == null) {
            this.head = newNode;
        } else {
            Node lastNode = this.head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }

        return this;
    }

    // retrieval by index - O(n)
    public int get(int index) {
        Node currentNode = this.head;

        // CASE 1 - index is 0 which is head
        if (index == 0 && currentNode != null) {
            return currentNode.data;
        }

        // CASE 2 - index is greater than 0 and less than size of the list
        int counter = 0;
        while (currentNode.next != null) {
            if (counter == index && currentNode != null) {
                return currentNode.data;
            } else {
                currentNode = currentNode.next;
                counter++;
            }
        }

        if (currentNode == null) {
            System.out.println("index not found");
        }

        return -1;
    }

    // traversal - O(n)
    public void printList() {
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    // deletion by data - O(n)
    public MyLinkedList remove(int data) {
        Node currentNode = this.head;
        Node previousNode = null;

        // CASE 1 - data is in head node
        if (currentNode != null && currentNode.data == data) {
            this.head = currentNode.next;
            System.out.println("removed data " + data);
            return this;
        }

        // CASE 2 - data is somewhere other than head
        while (currentNode != null && currentNode.data != data) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode != null) {
            previousNode.next = currentNode.next;
            System.out.println("removed data " + data);
        }

        // CASE 3 - not found
        if (currentNode == null) {
            System.out.println("not found");
        }

        return this;
    }

    // deletion by index - O(n)
    public MyLinkedList removeByIndex(int index) {
        Node currentNode = this.head;
        Node previousNode = null;

        // CASE 1 - index is 0 which is head
        if (index == 0 && currentNode != null) {
            this.head = currentNode.next;
            System.out.println("removed index " + index);
            return this;
        }

        // CASE 2 - index is greater than 0 or less than size of the list
        int counter = 0;
        while (currentNode != null) {
            if (counter == index) {
                previousNode.next = currentNode.next;
                System.out.println("removed index " + index);
                break;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.next;
                counter++;
            }
        }

        // CASE 3 - index is greater than the size of the list
        if (currentNode == null) {
            System.out.println("index is greater than size of the list");
        }

        return this;
    }
}
