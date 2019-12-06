package threading;

import java.util.ArrayList;
import java.util.List;

/**
 * Threads communicate primarily by sharing access to fields and the objects reference fiends refer to.
 * This form of communication is efficient but it can have two problems - thread interference and memory consistency error.
 * We use synchronization to solve those issues, but this can lead to thread contention - when two or more threads try to access same resource simultaneously
 * and cause java runtime to execute one or more threads more slowly or even suspend their execution.
 *
 * Thread interference - when multiple threads access shared data.
 * Memory consistency error - when different threads have inconsistent views of what should be the same data
 * The above issues can be fixed by using synchronized keyword
 *
 * Intrinsic Locks - Every object has an intrinsic lock associated with it. By convention, a thread that needs exclusive and consistent access to an object's fields
 * has to acquire the object's intrinsic lock before accessing them and then release when it's done. As long as a thread owns an intrinsic lock, no other thread can acquire the same lock.
 * When a thread invokes a synchronized method, it automatically acquires lock for that method's object and releases it when the method returns.
 *
 * Liveness - A concurrent applications's ability to execute in a timely manner is know as its liveness. Most common liveness problems are - deadlocks, starvation and livelock
 * Deadlock - When two or more threads are blocked forever.
 * Starvation - a situation where a thread is unable to gain regular access to shared resources and is unable to make progress
 * LiveLock - A thread often acts in response to the action of another thread. If the thread's action is also a response to the action of another thread then livelock may result. Like deadlock, livelocked threads
 * are unable to make further progress.
 */

public class Synchronization {

    private int c = 0;
    private String name = "";
    private List<String> nameList = new ArrayList<>();
    private int nameCount = 0;

    // it is not possible for two invocations of synchronized methods on the same object.
    // when one thread is executing a synchronized method for an object - other threads are suspended until first is done with that object
    private synchronized void increment() {
        c++;
    }

    private synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }

    // This method needs to synchronize changes to name and count but also needs to avoid synchronizing invocation of other object's methods (this can lead to problems)
    public void addName(String name) {
        synchronized (this) {
            this.name = name;
            nameCount++;
        }
        nameList.add(name);
    }

}
