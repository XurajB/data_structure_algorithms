package problems.bits;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,1};
        System.out.println(singleNumber(nums));
    }

    // o ^ a = a
    // a ^ a = 0
    private static int singleNumber(int[] nums) {
        int a = 0;
        for (int num: nums) {
            a = a ^ num;
        }
        return a;
    }
}
