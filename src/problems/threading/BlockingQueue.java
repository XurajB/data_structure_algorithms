package problems.threading;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A blocking queue is a queue that blocks when you try to dequeue from it and the queue is empty, or if you try to enqueue items to it and the queue is already full.
 * A thread trying to dequeue from an empty queue is blocked until some other thread inserts an item into the queue.
 * A thread trying to enqueue an item in a full queue is blocked until some other thread makes space in the queue, either by dequeuing one or more items or clearing the queue completely.
 */
public class BlockingQueue {
    private List<Object> queue = new LinkedList<>();
    private int  limit = 10;

    public BlockingQueue(int limit){
        this.limit = limit;
    }

    // in real situation, the enqueue can be done via network and if dequeue is slow than enqueue, it can easily take up the memory and crash.
    // so blocking queue helps in that as well
    // we are using the class as our lock
    // we can use our own lock and have the thread wait on that lock
    // example class2
    public synchronized void enqueue(Object item) throws InterruptedException  {
        while(this.queue.size() == this.limit) {
            wait();
        }
        if(this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }

    public synchronized Object dequeue() throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }

        return this.queue.remove(0);
    }

    static class BlockingThread2 {
        private List<Object> queue = new LinkedList<>();
        private int  limit = 10;

        Lock lock = new ReentrantLock();

        public BlockingThread2(int limit){
            this.limit = limit;
        }

        public void enqueue(Object item) throws InterruptedException  {
            lock.lock();
            while(this.queue.size() == this.limit) {
                lock.wait();
            }
            if(this.queue.size() == 0) {
                lock.notifyAll();
            }
            queue.add(item);
            lock.unlock();
        }

        public Object dequeue() throws InterruptedException{
            lock.lock();
            while(this.queue.size() == 0){
                lock.wait();
            }
            if(this.queue.size() == this.limit){
                lock.notifyAll();
            }
            Object o = queue.remove(0);
            lock.unlock();
            return o;
        }
    }
}
