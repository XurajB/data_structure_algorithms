package leetcode;

import java.util.PriorityQueue;

/**
 * You have some sticks with positive integer lengths.
 *
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.
 *
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 */
public class MinimumCostToConnectSticks {
    public static void main(String[] args) {
        int[] sticks = new int[] {1,8,3,5};
        System.out.println(connectSticks(sticks));
    }

    // nLogn, n
    private static int connectSticks(int[] sticks) {
        // we are counting total cost, not the size of final stick
        int total = 0;

        if (sticks.length < 3) {
            return total;
        }

        // min heap to maintain minimum
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i: sticks) {
            pq.offer(i);
        }

        // last one is the final stick
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            pq.offer(sum);
            total += sum;
        }

        return total;
    }
}
