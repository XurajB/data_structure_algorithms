package problems.heap;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Your job is to output the median array for each window in the original array.
 */
public class SlidingWindowMedian {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(medianSlidingWindow(nums, 3)));
    }

    // similar idea from - find median from data stream
    // O(n*k) [it would be nlogk but remove is k]. Use treeset for nlogk
    // O(k)
    private static double[] medianSlidingWindow(int[] nums, int k) {
        double ans[] = new double[nums.length - k + 1];
        // use two heaps
        PriorityQueue<Integer> max = new PriorityQueue<>((a,b) -> b.compareTo(a));
        PriorityQueue<Integer> min = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            // keep both of them balanced
            max.offer(nums[i]);
            min.offer(max.poll());
            if (max.size() < min.size()) {
                max.offer(min.poll());
            }

            if (max.size() + min.size() == k) {
                double median;
                if (max.size() == min.size()) {
                    median = ((double) max.peek() + (double) min.peek()) / 2.0; // avoid overflow
                } else {
                    median = (double) max.peek();
                }
                int pos = i - k + 1;
                ans[pos] = median;

                // remove num at pos to keep size at k
                // remove will cost O(k)
                if (!max.remove(nums[pos])) {
                    min.remove(nums[pos]);
                }
            }
        }

        return ans;
    }
}
