package problems.binarysearch;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once. Find this single element that appears only once.
 */
public class SingleElementInASortedArray {
    public static void main(String[] args) {
        int[] nums = {1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate(nums));
    }

    // O(logn)
    public static int singleNonDuplicate(int[] nums) {
        // 1,1,2,3,3,4,4,8,8
        // case1: even mid, nums[mid] == nums[mid+1], go right
        //        or odd mid, nums[mid] != nums[mid+1], go right
        // case2: even mid, nums[mid] != nums[mid+1], go left
        //        or odd mid, nums[mid] == nums[mid+1], go left

        int lo = 0;
        int high = nums.length - 1;

        while (lo < high) {
            int mid = lo + (high-lo)/2;

            if ((mid%2==0 && mid < nums.length -1 && nums[mid] == nums[mid+1]) ||
                    (mid%2 == 1 && mid < nums.length -1 && nums[mid] != nums[mid+1])) {
                lo = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[lo];
    }

    // O(logn)
    private static int singleNonDuplicate2(int[] nums) {
        // we only do binary search on even indexes, if all elements have pairs the even indexes will have the first element in the pair
        // After a single element, it changes to being odd indexes and even indexes not followed by pair
        // so at a given index, we can determine of the single element is on the right or left
        int low = 0;
        int high = nums.length - 1;
        while (low < high) { // notice no equals
            int mid = low + (high - low) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                // we are good so far, the element is on the right side
                low = mid + 2; // make sure mid is even
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
