package threading;

/**
 * Class showing DeadLock. Ram and Shyam are great friends but a strict rule of courtesy is that when
 * you bow to a friend, you must remain bowed until your friend has a chance to return the bow. Unfortunately this rule
 * does not account for the possibility that two friends might bow to each other at the same time.
 */

public class DeadLock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower) {
            System.out.println(this.name + ": " + bower.getName() + " has bowed to me ");
            bower.bowBack(bower);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.println(this.name + ": " + bower.getName() + " has bowed back to me ");
        }
    }

    public static void main(String[] args) {
        final Friend ram = new Friend("Ram");
        final Friend shyam = new Friend("Shyam");

        // it is likely that both threads will block when they attempt to invoke bowBack
        new Thread(new Runnable() {
            @Override
            public void run() {
                ram.bow(shyam);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                shyam.bow(ram);
            }
        }).start();
    }
}
