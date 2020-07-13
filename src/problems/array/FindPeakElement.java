package problems.array;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that nums[-1] = nums[n] = -∞.
 */
public class FindPeakElement {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(findPeakElement(nums));
    }

    // O(N)
    private static int findPeakElement(int[] nums) {
        // using the fact nums[i] ≠ nums[i+1]
        // there are 3 cases: 1. downhill. first element is the peak
        // 2. uphill - last element is the peak
        // 3. peak is in the middle which is the element larger than next element
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i+1]) { // only works when we are travelling linearly
                return i;
            }
        }
        return nums.length - 1; // case 2
    }

    // O(logn)
    private static int findPeakElement2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            // case 2
            if (nums[mid] > nums[mid + 1])
                // peak can be further left
                r = mid; // can't return mid because we could be in case 3.
            // case 1
            else
                l = mid + 1;
        }
        // case 3
        return l;
    }
}
