package misc;

import java.util.Stack;

/**
 * The objective of the puzzle is to move the entire stack to another rod, obeying the following simple rules:
 *
 * Only one disk can be moved at a time.
 * Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack or on an empty rod.
 * No larger disk may be placed on top of a smaller disk.
 */
public class TowerOfHanoi {
    public static void main(String[] args) {
        int n = 3; // number of discs

        // solution 1
        move(n, 1, 2, 3);

        // solution 2 - using stack
        // initialize towers
        Tower[] towers = new Tower[3];
        for (int i =0; i < 3; i++) {
            towers[i] = new Tower(i);
        }
        // fill from tower
        for (int i = n-1; i >= 0; i--) {
            towers[0].add(i);
        }
        towers[0].moveDiscs(n, towers[2], towers[1]);

    }

    // recursive solution
    // n = number of discs
    private static void move(int n, int from, int intermediate, int to) {
        if (n == 0) {
            return;
        }

        // move is called in recursion for swapping the n-1 disc from the startPole to the intermediatePole
        move(n-1, from, to, intermediate);
        System.out.println(String.format("Move disc from %s to %s", from, to));
        // move is called in recursion for swapping the n-1 disc from the intermediatePole to the endPole
        move(n-1, intermediate, from, to);
    }

    // solutions using stacks
    private static class Tower {
        private Stack<Integer> disks;
        private int index;

        Tower(int index) {
            this.index = index;
            disks = new Stack<>();
        }

        int getIndex() {
            return index;
        }

        void add(int d) {
            if (!disks.isEmpty() && disks.peek() <= d) {
                System.out.println("Error placing disc - " + d);
            } else {
                disks.push(d);
            }
        }

        void moveTopTo(Tower t) {
            int top = disks.pop();
            t.add(top);
            System.out.println("Move disc " + top + " from " + getIndex() + " to " + t.getIndex());
        }

        void moveDiscs(int n, Tower destination, Tower buffer) {
            if (n > 0) {
                moveDiscs(n-1, buffer, destination);
                moveTopTo(destination);
                moveDiscs(n-1, destination, this);
            }
        }
    }
}
