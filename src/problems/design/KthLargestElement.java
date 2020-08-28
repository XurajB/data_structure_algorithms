package problems.design;

import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream.
 * For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 */
public class KthLargestElement {

    // follow up: what if k is not fixed
    // A follow-up with range of numbers in the stream is smaller but the k is now variable. Binary search on a prefix sum over the frequency of the range.

    PriorityQueue<Integer> pq = new PriorityQueue<>(); // adding k in the constructor doesn't actually limit the size to k
    int k;
    // min heap with size K
    // NLogK
    public KthLargestElement(int k, int[] nums) {
        this.k = k;
        for (int num: nums) {
            add(num);
        }
    }
    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}
