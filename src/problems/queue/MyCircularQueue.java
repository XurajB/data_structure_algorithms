package problems.queue;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are
 * performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle.
 * It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
 * In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue.
 * But using the circular queue, we can use the space to store new values.
 */

// O(N) space. O(1) for all features
// also includes a follow up question - potential defect - concurrency.
// I implemented for the enQueue method
class MyCircularQueue {
    private int capacity;
    private int[] queue;
    private int headIndex;
    private int count;

    ReentrantLock lock = new ReentrantLock();

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.capacity = k;
        this.count = 0;
        this.headIndex = 0;
        this.queue = new int[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        // insert infront of queue

        lock.lock();
        // try so we can unlock at finally
        try {
            if (count == capacity) {
                return false;
            }
            int nextIndex = (headIndex + count) % capacity;
            queue[nextIndex] = value;
            count++;
        } finally {
            lock.unlock();
        }
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (count == 0) {
            return false;
        }
        headIndex = (headIndex + 1) % capacity;
        count--;

        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (count == 0) {
            return -1;
        }
        return queue[headIndex];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (count == 0) {
            return -1;
        }
        int tail = (headIndex + count - 1) % capacity;
        return queue[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return count == capacity;
    }
}