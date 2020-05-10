package problems.threading;

/**
 * This class demonstrates basic thread concepts.
 * The main thread creates a new thread, MessageLoop and waits for it to finish. If the MessageLoop tread takes too long to finish, the main thread interrupts it
 * The MessageLoop thread prints out a series of messages. If interrupted before it has printed all its messages, the MessageLoop thread prints a exit message and exits.
 */

public class SimpleThread {

    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": " + message);
    }

    public static class MessageLoop implements Runnable {
        @Override
        public void run() {
            String importantInfo[] = {
                    "Hi this is tread",
                    "I implement runnable",
                    "The main code is",
                    "in the Run method" };
            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("Interrupted!!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long patience = 10 * 1000; // 10 secs

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");

        while (t.isAlive()) {
            threadMessage("still waiting..");

            // wait maximum of 1 second for MessageLoop to finish
            t.join(1000);
            if ((System.currentTimeMillis() - startTime) > patience && t.isAlive()) {
                threadMessage("Tired of waiting");
                t.interrupt();

                t.join();
            }
        }

        threadMessage("Finally!");
    }
}
