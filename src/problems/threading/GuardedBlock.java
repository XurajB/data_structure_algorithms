package problems.threading;

import java.util.Random;

/**
 * Block that begins by polling a condition that must be true before the block can proceed.
 * Using guarded block to create a producer-consumer application.
 * This application shares data between two threads - producer and consumer. The two threads communicate using
 * a shared object. The consumer thread must not attempt to retrieve the data before the producer thread has delivered it or vice versa
 *
 */
public class GuardedBlock {
    public static void main(String[] args) {
        GuardedBlock guardedBlock = new GuardedBlock();
        guardedBlock.producerConsumerExample();
    }

    private void producerConsumerExample() {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }

    private static class Drop {
        // message sent from producer to consumer
        private String message;
        private boolean empty = true; // true if consumer should wait, false if producer should wait

        synchronized String take() {
            // wait until message is available
            while (empty) {
                try {
                    wait(); // wait for notify
                } catch (InterruptedException e) {
                    // not implemented
                }
            }
            empty = true;
            // notify producer that status has changed
            notifyAll();
            return message;
        }

        synchronized void put(String message) {
            // wait until message has been retrieved
            while (!empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // not implemented
                }
            }
            this.message = message;
            empty = false;
            notifyAll();
        }
    }

    private static class Producer implements Runnable {

        private Drop drop;

        Producer(Drop drop) {
            this.drop = drop;
        }

        @Override
        public void run() {
            String importantMessage[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };
            Random random = new Random();
            for (int i = 0; i < importantMessage.length; i++) {
                drop.put(importantMessage[i]);
            }
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                // not implemented
            }
            drop.put("Done");
        }
    }

    private static class Consumer implements Runnable {
        private Drop drop;

        Consumer(Drop drop) {
            this.drop = drop;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (String message = drop.take(); !message.equals("Done"); message = drop.take()) {
                System.out.println("Message: " + message);
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    // not implemented
                }
            }
        }
    }
}
