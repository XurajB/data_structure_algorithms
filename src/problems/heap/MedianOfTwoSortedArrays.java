package problems.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 */
public class MedianOfTwoSortedArrays {

    // O(log(min(n, m))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int x = nums1.length;
        int y = nums2.length;

        // if nums1 length is greater, switch them we that nums1 is smaller than nums2
        if (x > y) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // we do binary search in the smaller array
        int low = 0;
        int high = x;

        // x: x1 x2 | x3 x4 x5 x6
        // y: y1 y2 y3 y4 y5 | y6 y7 y8
        // divide into two halves: not among themselves but the total length
        // leftMaxX: x2, rightMinX: x3, leftMaxY: y5, rightMinY: y6
        // if we can find partition such that: x2 <= y6 && x3 >= y5
        // this means all elements to the left are smaller than all elements to the right
        // we found the correct partitions. then the median will be odd: max(x2, y5), even: avg(max(x2, y5), min(x3, y6))

        while (low <= high) {
            int partitionX = low + (high - low)/2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            // if partitionX is 0, it means nothing on the left side, use -INF to maxLeftX
            // if partitionX is m, then there is nothing on the right sie, use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            // check the condition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // median partitions an array into two halves
                // we have partitioned array at correct place
                // get max of left elements and min of right elements to get the median in case of even combined array
                // or get max of left for odd combined array size
                if ((x + y) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2.0;
                } else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        return 0.0;
    }

    // O(m+n)
    private static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int[] num = new int[n1+n2];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                num[k] = nums1[i];
                i++;
            } else {
                num[k] = nums2[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            num[k++] = nums1[i++];
        }
        while (j < n2) {
            num[k++] = nums2[j++];
        }

        int mid = num.length/2;
        if (num.length % 2 == 0) {
            return (num[mid] + num[mid-1])/2.0;
        } else {
            return num[mid];
        }
    }

    // (m+n)log(m+n)
    // more efficient is to merge sorted arrays and get median
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> min = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        PriorityQueue<Integer> max = new PriorityQueue<>(); // min heap
        for (int i = 0; i < nums1.length; i++) {
            insert(min, max, nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            insert(min, max, nums2[i]);
        }
        double median = 0.0;
        if (max.size() == min.size()) {
            median = (max.peek() + min.peek())/2.0;
        } else {
            median = (double)max.peek();
        }
        return median;
    }

    private void insert(PriorityQueue<Integer> min, PriorityQueue<Integer> max, int i) {
        max.offer(i);
        min.offer(max.poll());
        if (max.size() < min.size()) {
            max.offer(min.poll());
        }
    }
}
