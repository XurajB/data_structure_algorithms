package problems.heap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Your job is to output the median array for each window in the original array.
 */
public class SlidingWindowMedian {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(medianSlidingWindow2(nums, 3)));
    }

    // similar idea from - find median from data stream
    // O(n*klogk) [it would be nlogk but remove is k]. Use treeset for nlogk
    // O(k)
    private static double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> max = new PriorityQueue<>();
        PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.reverseOrder());

        int n = nums.length;
        double[] ans = new double[n-k+1];

        for (int i = 0; i < nums.length; i++) {
            max.offer(nums[i]);
            min.offer(max.poll());
            if (min.size() > max.size()) {
                max.offer(min.poll());
            }

            if (i-k+1 >= 0) {
                double median = 0.0;
                if (k % 2 == 0) {
                    median = ((double) max.peek() + (double) min.peek())/2.0; // prevent overflow
                } else {
                    median = max.peek();
                }
                ans[i-k+1] = median;

                // this will cost O(k)
                if (!max.remove(nums[i-k+1])) {
                    min.remove(nums[i-k+1]);
                }
            }
        }

        return ans;
    }

    ////////////////////
    // nlogk - using TreeSet.
    // treeSet cannot store duplicates so we will need to store index
    private static double[] medianSlidingWindow2(int[] nums, int k) {
        Comparator<Integer> comparator = (a, b) -> {
            if (nums[a] != nums[b]) {
                return Integer.compare(nums[a], nums[b]);
            } else {
                return a - b;
            }
        };
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> right = new TreeSet<>(comparator);
        double[] res = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            right.add(i);
            left.add(right.pollFirst());
            // balance
            if (left.size() > right.size()) {
                right.add(left.pollFirst());
            }

            double median = 0.0;
            if (left.size() + right.size() == k) {
                if (k % 2 == 0) {
                    median = ((double) nums[left.first()] + nums[right.first()]) / 2;
                } else {
                    median = (double) nums[right.first()];
                }

                int pos = i - k + 1;
                res[pos] = median;

                if (!left.remove(i-k+1)) {
                    right.remove(i-k+1);
                }
            }
        }
        return res;
    }

}
