package problems.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * Note: The algorithm should run in linear time and in O(1) space.
 */
public class MajorityElement2 {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] {1,1,1,3,3,2,2,2}));
    }

    // O(N), O(1)
    private static List<Integer> majorityElement(int[] nums) {
        // if majority element > n/2, there can max 1 element
        // if majority element > n/3, there can max 2 element
        // so using boyer-moore voting algorithm and keeping 2 variables
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int num1 = nums[0];
        int num2 = nums[0];
        int count1 = 0;
        int count2 = 0;
        int n = nums.length;
        for (int num: nums) {
            if (num1 == num) {
                count1++;
            } else if (num2 == num) {
                count2++;
            } else if (count1 == 0) {
                num1 = num;
                count1++;
            } else if (count2 == 0) {
                num2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        // check counts for these two nums
        count1 = 0;
        count2 = 0;
        for (int num: nums) {
            if (num1 == num) {
                count1++;
            } else if (num2 == num) {
                count2++;
            }
        }
        if (count1 > n/3) {
            ans.add(num1);
        }
        if (count2 > n/3) {
            ans.add(num2);
        }
        return ans;
    }
}
