package problems;

public class Test {
    public static void main(String[] args) {


        int[] nums = {4,7,9,10};
        System.out.println(missingElement(nums, 3));

    }

    public static int missingElement(int[] nums, int k) {

        int r = 0;
        int l = 0;


        while (r < nums.length - 1 && k > 0) {
            if (nums[r+1] != nums[r] + 1) {
                int diff = nums[r+1] - nums[r] - 1;
                k = k - diff;

                if (k <= 0) {
                    return nums[r] + 1;
                }
            }
            r++;
        }

        return nums[nums.length - 1] + k;

    }
}
