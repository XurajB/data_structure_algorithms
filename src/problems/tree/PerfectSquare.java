package problems.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 */
public class PerfectSquare {
    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }

    // O(n ^ h/2) , h = height of tree
    private static int numSquares(int n) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited.add(0);
        int level = 0;
        while (!q.isEmpty()) {
            level++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int current = q.poll();
                // check with all possible squares
                for (int j = 1; j * j <= n; j++) {
                    int sum = current + j * j;
                    if (sum == n) {
                        return level;
                    } else if (sum > n) {
                        break;
                    } else if (!visited.contains(sum)) {
                        q.offer(sum);
                        visited.add(sum);
                    }
                }
            }
        }
        return level;
    }
}
