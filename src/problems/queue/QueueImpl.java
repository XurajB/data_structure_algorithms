package problems.queue;

/**
 * Implement a queue using array. Circular array in this case
 * See MyQueue for implementation using LinkedList
 */
public class QueueImpl {
    int[] a;
    int front;
    int back;
    int length;

    public QueueImpl(int capacity) {
        a = new int[capacity];
        front = 0;
        back = 0;
        length = 0;
    }

    public void add(int item) throws QueueFullException {
        if (length == a.length) throw new QueueFullException();
        a[back] = item;
        back = (back + 1) % a.length;
        length++;
    }

    public int remove() throws QueueEmptyException {
        if (length == 0) throw new QueueEmptyException();
        int result = a[front];
        front = (front + 1) % a.length;
        length--;
        return result;
    }

    public static class QueueFullException extends Exception {
    }

    public static class QueueEmptyException extends Exception {
    }
}
