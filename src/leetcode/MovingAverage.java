package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *  https://leetcode.com/problems/moving-average-from-data-stream/
 */
public class MovingAverage {
    int sum = 0;
    Queue<Integer> q;
    int size = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        q = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {

        q.offer(val);
        sum += val;
        if (q.size() > size) {
            sum -= q.poll();
        }

        return sum / (double) q.size();
    }
}
