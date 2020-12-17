package problems.binarysearch;

/**
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all the array by it and sum the result of the division.
 * Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 * Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 * It is guaranteed that there will be an answer.
 */
public class SmallestDivisor {
    public static void main(String[] args) {
        System.out.println(smallestDivisor(new int[] {1,2,5,9}, 6));
    }

    // nlogn
    private static int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = 0;
        for (int num: nums) {
            high = Math.max(high, num);
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = 0;
            for (int num: nums) {
                sum += num / mid + (num % mid == 0 ? 0 : 1);
            }
            if (sum > threshold) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
