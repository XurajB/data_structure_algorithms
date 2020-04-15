package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class FindMedianFromDataStream {

    // keep two heaps - min and max
    // min - will store larger numbers than median
    // max - will store smaller numbers than median
    // we will keep the size same or (max+1) to keep them balanced so that median is always on top
    // other approach would be to sort the elements on every addNum and return the middle
    // that is what we are doing with heaps - store smaller half in max heap and other in max heap

    PriorityQueue<Integer> max;
    PriorityQueue<Integer> min;
    public FindMedianFromDataStream() {
        max = new PriorityQueue<>(Collections.reverseOrder());
        min = new PriorityQueue<>();
    }

    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()) { // keep them balanced, and keep smaller in max and larger in min
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        if (max.size() == min.size()) {
            return (max.peek() + min.peek())/2.0;
        } else {
            return max.peek();
        }
    }
}
