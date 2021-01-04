package problems.array;

/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[] {1,2,3,4,5}));
    }

    // O(N), O(1)
    private static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num: nums) {
            if (num <= first) { // lower than first
                first = num;
            } else if (num <= second) { // higher than first, lower than second
                second = num;
            } else { // lower than both, we saw a triplet increasing seq
                return true;
            }
        }
        return false;
    }
}
