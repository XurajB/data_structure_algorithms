import dataStructures.MyArrayList;
import dataStructures.MyArrays;
import dataStructures.MyLinkedList;

/**
 * Created by Xuraj on 11/11/2019.
 * Main entry point to the library
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.verifyMyArrays();
        System.out.println("--------------");
        main.verifyMyArrayList();
        System.out.println("--------------");
        main.verifyLinkedList();
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
        linkedList.get(2);
        linkedList.remove(2);
        linkedList.printList();
        linkedList.removeByIndex(0);
        linkedList.printList();
    }
}
