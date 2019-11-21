package dataStructures;

import java.util.Arrays;

/**
 * Created by Xuraj on 11/11/2019.
 * Implementation of a List
 *
 * Iterator and few other things are missing
 * get can also be done by passing object instead of index
 */
public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;
    private Object[] elements;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(E e) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", size " + i);
        }
        return (E) elements[i];
    }

    public E remove(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", size " + i);
        }
        E valueToReturn = get(i);

        // copy anything after i to the same array starting at position i
        int numMove = size - i - 1; // everything after i
        if (i > 0) {
            System.arraycopy(elements, i + 1, elements, i, numMove);
        }
        elements[--size] = null; // clear last position

        return valueToReturn;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }
}
