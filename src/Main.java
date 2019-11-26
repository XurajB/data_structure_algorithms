import dataStructures.*;

/**
 * Created by Xuraj on 11/11/2019.
 * Main entry point to the library
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("--------------Arrays");
        main.verifyMyArrays();

        System.out.println("--------------Array List");
        main.verifyMyArrayList();

        System.out.println("--------------Linked List");
        main.verifyLinkedList();

        System.out.println("--------------HashMap");
        main.verifyMyHashMap();

        System.out.println("--------------Stack");
        main.verifyMyStack();

        System.out.println("--------------Queue");
        main.verifyMyQueue();
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
    }
}
