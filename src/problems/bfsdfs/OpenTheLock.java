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
    // substring operations for 4 * size = O(4N) = O(N^2)
    // O(A^N * N^2 + D) = O(10^N * N^2 + D)
    // SPACE: O(A^N + D) for visited and dead sets
    private static int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        q.offer("0000");
        visited.add("0000");
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String s = q.poll();
                if (deads.contains(s)) {
                    continue;
                }
                if (s.equals(target)) {
                    return level;
                }
                StringBuilder sb = new StringBuilder(s);
                for (int j = 0; j < 4; j++) {
                    char c = s.charAt(j);
                    // either you can go +1 or -1 from each position
                    // for +1, if it is 9, goto 0
                    String s1 = sb.substring(0, j) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(j+1);
                    String s2 = sb.substring(0, j) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(j+1);
                    if (!visited.contains(s1) && !deads.contains(s1)) {
                        q.offer(s1);
                        visited.add(s1);
                    }
                    if (!visited.contains(s2) && !deads.contains(s1)) {
                        q.offer(s2);
                        visited.add(s2);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
