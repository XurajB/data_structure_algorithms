package dataStructures;

import java.util.Arrays;

/**
 * Simple implementation of Heap using arrays
 * Heap is like a binary tree where parent is always bigger than children (in Max Heap)
 */
public class MyHeap {
    private static int[] heap = new int[] {3, 4, 3, 90, 87, 34, 23, 5};
    private int itemsInArray;

    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap();
        for (int i = 0; i < heap.length; i++) {
            myHeap.insert(i, heap[i]);
        }
        System.out.println(Arrays.toString(heap));
        myHeap.heapSort();
        System.out.println(Arrays.toString(heap));
    }

    private void insert(int index, int data) {
        heap[index] = data;
        heapify(index);
        itemsInArray++;
    }

    private int pop() {
        if (itemsInArray != 0) {
            int root = heap[0];
            // insert last item in the empty spot
            heap[0] = heap[--itemsInArray];
            // heapify the array
            heapify(0);
            return root;
        }

        return -1; // not found. should be replaced with an object
    }

    private void heapify(int index) {
        int largestChild;
        int root = heap[index];
        while (index < itemsInArray / 2) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            // find largest among two children
            if (rightChildIndex < itemsInArray
                    && heap[leftChildIndex] < heap[rightChildIndex]) {
                largestChild = rightChildIndex;
            } else {
                largestChild = leftChildIndex;
            }

            // swap if root is smaller
            if (root > heap[largestChild]) {
                break;
            }
            heap[index] = heap[largestChild];
        }
    }

    private void heapSort() {
        for (int k = heap.length - 1; k >= 0; k --) {
            insert(k, pop());
        }
    }
}
