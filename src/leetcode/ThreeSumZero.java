package leetcode;

import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * https://leetcode.com/problems/3sum/
 * https://www.interviewbit.com/problems/3-sum-zero/
 */
public class ThreeSumZero {

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    // O(N^2)
    private static ArrayList<ArrayList<Integer>> threeSum(int[] nums) {
        // sort the array, iterate through the array and use another two pointers approach
        Arrays.sort(nums);

        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();

        for (int x = 0; x < nums.length; x++) {

            if (x > 0 && nums[x] == nums[x-1]) {
                continue; // skip same result
            }

            int s1 = nums[x];
            int i = x + 1;
            int j = nums.length - 1;

            while (i < j) {
                int s2 = nums[i];
                int s3 = nums[j];
                int sum = s1 + s2 + s3;
                if (sum == 0) {
                    ArrayList<Integer> a = new ArrayList<>();
                    a.add(s1);
                    a.add(s2);
                    a.add(s3);
                    answer.add(a);
                    i++;
                    j--;

                    // The solution set must not contain duplicate triplets.
                    // skip same elements or same results
                    // also prevent from doing answer.contains(list) - skip O(n)
                    while (i < j && nums[i] == nums[i-1]) {
                        i++;
                    }
                    while (i < j && nums[j] == nums[j+1]) {
                        j--;
                    }

                } else if (sum <= 0) {
                    i++;
                } else {
                    j--;
                }

            }
        }
        return answer;
    }
}
