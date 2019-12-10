package threading;

import java.util.LinkedList;
import java.util.List;

/**
 * A blocking queue is a queue that blocks when you try to dequeue from it and the queue is empty, or if you try to enqueue items to it and the queue is already full.
 * A thread trying to dequeue from an empty queue is blocked until some other thread inserts an item into the queue.
 * A thread trying to enqueue an item in a full queue is blocked until some other thread makes space in the queue, either by dequeuing one or more items or clearing the queue completely.
 */
public class BlockingQueue {
    private List queue = new LinkedList();
    private int  limit = 10;

    public BlockingQueue(int limit){
        this.limit = limit;
    }

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

}
