package interviewbit;

/**
 * Suppose a sorted array A is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). - there cannot be duplicate elements
 * Find the pivot - 4 in this example
 */
public class RotatedArray {
    public static void main(String[] args) {
        int[] a = {4, 5, 6, 7, 1, 2, 3};
        System.out.println(findPivot(a));
    }

    // there are many properties of the pivot, it is the smallest number. or number that is smaller than both left or right elements
    // simple solution is to do the linear search for smallest or find a number that is less than its neighbours - O(N)
    // better solution would be a binary search O(logN) - which can utilize both of these properties
    public static int findPivot(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int n = nums.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int left = (mid - 1 + n) % n, right = (mid + 1) % n; // if they overflow we want to start from the beginning
            if (nums[low] <= nums[high]) { //case 1
                return low;
            } else if (nums[mid] < nums[left]) {
                return mid;
            } else if (nums[mid] >= nums[low]) {
                low = mid + 1;
            } else if (nums[mid] <= nums[high]) {
                high = mid - 1;
            }
        }

        return -1; //this means the array
    }
}
