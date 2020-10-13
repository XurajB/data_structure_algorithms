package problems.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 * Implement the RecentCounter class:
 *
 * RecentCounter() Initializes the counter with zero recent requests.
 * int ping(int t) Adds a new request at time t, where t represents some time in milliseconds,
 * and returns the number of requests that has happened in the past 3000 milliseconds (including the new request).
 * Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 */
public class RecentCounter {
    Queue<Integer> q;

    public RecentCounter() {
        q = new LinkedList<>();
    }

    public int ping(int t) {
        q.offer(t);
        while (q.peek() < (t - 3000)) {
            q.poll();
        }
        return q.size();
    }

    //// another way
    int[] time = new int[3001];
    public int ping2(int t) {
        int res = 0;
        time[t % 3001] = t;
        for (int i = 0; i < 3001; i++) {
            if (time[i] != 0 && time[i] >= t - 3000) {
                res += 1;
            }
        }
        return res;
    }
}
