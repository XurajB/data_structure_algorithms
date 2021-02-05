package problems.binarysearch;

/**
 * Suppose a sorted array A is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). - there cannot be duplicate elements
 * Find the pivot - 4 in this example
 */
public class RotatedArray {
    public static void main(String[] args) {
        int[] a = {4, 5, 6, 7, 1, 2, 3};
        System.out.println(findPivot(a));
        System.out.println(findPivot2(a));
    }

    // there are many properties of the pivot, it is the smallest number. or number that is smaller than both left or right elements
    // simple solution is to do the linear search for smallest or find a number that is less than its neighbours - O(N)
    // better solution would be a binary search O(logN) - which can utilize both of these properties
    private static int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right-left)/2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }


    /////////////////////////
    private static int findPivot2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int low = 0;
        int high = nums.length - 1;

        // no rotation
        // 1 2 3 4 5
        // vs
        // 4 5 1 2 3
        if (nums[low] < nums[high]) {
            return low;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 5 in above case
            if (nums[mid] > nums[mid + 1]) {
                return mid + 1;
            }

            if (nums[mid] < nums[mid - 1]) {
                return mid;
            }

            // mid is larger than num[0] which
            // means same sorted part 1 2 3 4 5
            if (nums[mid] > nums[0]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
