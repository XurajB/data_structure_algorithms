package problems.twopointers;
/**
 * 4. Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 6, 6};
        int[] num2 = {2, 4, 7, 8, 9};

        System.out.println(findMedianSortedArrays2(nums1, num2));
    }

    // O(Log(min(m,n))
    private static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int x = nums1.length;
        int y = nums2.length;

        // if nums1 length is greater, switch them we that nums1 is smaller than nums2
        if (x > y) {
            findMedianSortedArrays2(nums2, nums1);
        }

        // we do binary search in the smaller array = o(log(min(m,n))
        int low = 0;
        int high = x;

        while (low <= high) {
            // number of elements on each side should be same
            int partitionX = low + (high - low)/2;
            int partitionY = (x + y + 1) / 2 - partitionX; // +1 because it play well with odd and even length

            // if partitionX is 0, it means nothing on the left side, use -INF to maxLeftX
            // if partitionX is m, then there is nothing on the right sie, use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            // check the condition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // we have paritioned array at correct place
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

    ////--------------- naive
    // O(m+n)
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int m = nums1.length;
        int n = nums2.length;
        int[] merge = new int[m + n];
        int k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                merge[k] = nums1[i];
                i++;
            } else {
                merge[k] = nums2[j];
                j++;
            }
            k++;
        }
        while (i < nums1.length) {
            merge[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            merge[k++] = nums2[j++];
        }
        double median = 0.0;
        if (merge.length % 2 == 0) {
            median = (merge[merge.length/2] + merge[merge.length/2 - 1])/2.0;
        } else {
            median = (double) merge[merge.length/2];
        }
        return median;
    }
}
