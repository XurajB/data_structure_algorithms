package problems.sorting;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Find kth smallest/largest element from a unsorted array;
 * There are many ways we can do this - sorting, heap - they are all O(nlogn)
 * We can use quick select algorithm to do this in O(n)
 */
public class KthLargestElement {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        System.out.println(kthLargest(nums, 2));
    }

    // This algorithm is similar to quick sort, instead of recurring on both sides of pivot, we only recur one side depending on the partitioned element
    // if the partitioned element is larger than k, we only recur left side and vice versa. This reduces complexity from nlogn to n (n^2 worst case)

    // O(N), O(1)
    private static int kthLargest(int[] nums, int k) {
        int n = nums.length;
        // kth largest is (n-k)th smallest
        // this algorithm finds (n-k)th smallest element
        return quickSelect(nums, n-k, 0, n-1);
    }

    private static int quickSelect(int[] nums, int k, int left, int right) {
        // return the k-th smallest element from left to right
        if (left == right) {
            // list only has one element
            return nums[left];
        }

        // select a random pivot index, making this random reduces complexity by a lot
        Random random = new Random();
        int pivot = left + random.nextInt(right - left);
        pivot = partition(nums, left, right, pivot);

        // if pivot is on (N-k)th smallest element
        if (k == pivot) {
            return nums[pivot];
        } else if (k < pivot) { // go to left side
            return quickSelect(nums, k, left, pivot - 1);
        } else {
            return quickSelect(nums, k, pivot + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        // move pivot to end, so we don't over write this
        swap(nums, pivotIndex, right);

        // move all smaller elements to the left
        int leftIndex = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap(nums, leftIndex, i);
                leftIndex++;
            }
        }

        // move pivot back to its final place
        swap(nums, leftIndex, right);

        return leftIndex;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Nlogk
    private static int KthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num: nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }
}
