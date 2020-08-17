package problems.slidingwindow;

/**
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 */
public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        int[] nums = {1,0,1,0,1};
        System.out.println(numSubarraysWithSum(nums, 2));
    }

    private static int numSubarraysWithSum(int[] A, int S) {
        return atMost(A, S) - atMost(A, S-1);
    }

    private static int atMost(int[] A, int s) {
        int start = 0;
        int end = 0;
        int count = 0;
        while (end < A.length) {
            s -= A[end];
            end++;
            while (start < end && s < 0) {
                s += A[start];
                start++;
            }
            count += end - start + 1;
        }
        return count;
    }
}
