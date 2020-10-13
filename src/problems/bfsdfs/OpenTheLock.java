package problems.bfsdfs;

import java.util.*;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 */
public class OpenTheLock {
    public static void main(String[] args) {
        String[] deads = {"0201", "0101", "0102", "1212", "2002"};
        System.out.println(openLock(deads, "0202"));
    }

    // There are N number of elements in dial
    // A is the numbers possible (0-9) in our case
    // D is the size of deadends

    // O(A^N + D) = O(10^N + D)
    // SPACE: O(A^N + D) for visited and dead sets
    private static int openLock(String[] deadends, String target) {
        Set<String> invalid = new HashSet<>(Arrays.asList(deadends));
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer("0000");
        visited.add("0000");
        if (invalid.contains("0000")) {
            return -1;
        }
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                String current = q.poll();
                if (current.equals(target)) {
                    return level;
                }

                for (int i = 0; i < 4; i++) {
                    char[] cur = current.toCharArray();
                    char temp = cur[i];

                    if (cur[i] == '9') {
                        cur[i] = '0';
                    } else {
                        cur[i]++;
                    }

                    String next = new String(cur);
                    if (!visited.contains(next) && !invalid.contains(next)) {
                        visited.add(next);
                        q.offer(next);
                    }
                    cur[i] = temp;
                    if (cur[i] == '0') {
                        cur[i] = '9';
                    } else {
                        cur[i]--;
                    }
                    next = new String(cur);
                    if (!visited.contains(next) && !invalid.contains(next)) {
                        visited.add(next);
                        q.offer(next);
                    }
                }

            }
            level++;
        }

        return -1;
    }
}
