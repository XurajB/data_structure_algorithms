package dataStructures;

import dataStructures.trees.MyBinarySearchTree;

/**
 * Created by Xuraj on 11/11/2019.
 * dataStructures.Main entry point to the library
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();

        System.out.println("--------------Arrays");
        test.verifyMyArrays();

        System.out.println("--------------Array List");
        test.verifyMyArrayList();

        System.out.println("--------------Linked List");
        test.verifyLinkedList();

        System.out.println("--------------HashMap");
        test.verifyMyHashMap();

        System.out.println("--------------Stack");
        test.verifyMyStack();

        System.out.println("--------------Queue");
        test.verifyMyQueue();

        System.out.println("--------------BST");
        test.verifyMyBinarySearchTree();
    }

    private void verifyMyArrays() {
        MyArrays arrays = new MyArrays();
        arrays.evaluate();
    }

    private void verifyMyArrayList() {
        MyArrayList<String> myList = new MyArrayList<>();

        myList.add("suraj");
        myList.add("is");
        myList.add("a");
        myList.add("good");
        myList.add("boy");

        System.out.println(myList.get(3));
        String value = myList.remove(3);
        System.out.println(value);

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }

    private void verifyLinkedList() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);
        linkedList.insert(4);

        linkedList.printList();
        System.out.println(linkedList.get(2));
        linkedList.remove(2);
        linkedList.printList();
        linkedList.removeByIndex(0);
        linkedList.printList();
    }

    private void verifyMyHashMap() {
        MyHashMap<String, String> hashMap = new MyHashMap<>(10);
        hashMap.put("USA", "Washington DC");
        hashMap.put("Nepal", "Kathmandu");
        hashMap.put("Canada", "Ottawa");
        hashMap.put("Mexico", "Mexico City");
        hashMap.put("China", "Beijing");

        hashMap.print();
        System.out.println(hashMap.get("Nepal"));
        hashMap.delete("USA");
        hashMap.print();
        hashMap.put("USA", "DC");
        System.out.println("---");
        hashMap.print();
    }

    private void verifyMyStack() {
        MyStack<String> stack = new MyStack<>();

        stack.push("Hello");
        stack.push("My");
        stack.push("Name");
        stack.push("is");
        stack.push("Suraj");

        stack.print();
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        stack.print();

        System.out.println(stack.pop());
        stack.print();
        stack.push("Hi");
        stack.print();
    }

    private void verifyMyQueue() {
        MyQueue<String> queue = new MyQueue<>();

        queue.enqueue("Hello");
        queue.enqueue("My");
        queue.enqueue("Name");
        queue.enqueue("Is");
        queue.enqueue("Suraj");

        queue.print();
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        queue.print();

        System.out.println(queue.dequeue());
        queue.print();
        queue.enqueue("Hi");
        queue.print();
        System.out.println();
    }

    private void verifyMyBinarySearchTree() {
        MyBinarySearchTree<String, Integer> st = new MyBinarySearchTree<>();

        st.put("A", 8);
        st.put("C", 4);
        st.put("E", 12);
        st.put("H", 5);
        st.put("L", 11);
        st.put("M", 9);
        st.put("P", 10);
        st.put("R", 3);
        st.put("S", 0);
        st.put("X", 7);

        for (String s : st.levelOrder())
            System.out.println(s + " " + st.get(s));

        System.out.println();

        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}
