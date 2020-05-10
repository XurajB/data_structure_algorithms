package problems.threading;

/**
 * Java concurrency
 * Two basic units of execution - processes and threads
 * Process - self contained execution environment. A process generally has a complete, private set of basic run-time resources, each process has its own memory space
 * Most implementation of JVM run as a single process but it is possible to create additional processes using a ProcessBuilder. To communicate between processes most OS provides IPC resources such as pipes and sockets.
 * Thread - Sometimes called lightweight process. Creating a new thread requires fewer resources than a process. Threads exist within a process - every process has at least one. Threads share processes's resources
 * including memory and open files. From the application point of view - you start with one thread called the main thread.
 *
 * Interrupts - An interrupt is an indication to a thread that it should stop and do something else. It's upto the developer to decide how a thread responds to an interrupt, but it is very common for the thread to terminate.
 * Joins - The join method allows one thread to wait for the completion of another. Like sleep, join also responds to an interrupt
 */

public class Concurrency {

    public static void main(String[] args) {
        Concurrency concurrency = new Concurrency();
        concurrency.startRunnable();
        concurrency.startThread();
    }

    private void startThread() {
        new HelloThread().start();
    }

    private void startRunnable() {
        Thread hello = new Thread(new HelloRunnable());
        hello.start();
        hello.interrupt();
    }

    // two ways to create Threads
    // by implementing Runnable object
    public static class HelloRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("hello from a runnable!");

            // when a thread is interrupted, it catches the InterruptedException
            for (int i =0; i<10; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted - returning");
                    return;
                }

                System.out.println(i);
            }
        }
    }

    // by extending Thread class
    public static class HelloThread extends Thread {
        @Override
        public void run() {
            System.out.print("hello from a thread!");
        }
    }
}
