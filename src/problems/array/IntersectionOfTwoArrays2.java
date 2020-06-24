package problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionOfTwoArrays2 {

    // use hash map solution for O(n + m)
    // O(nLogn + mLogm), O(1)
    public int[] intersect(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = nums1.length;
        int j = nums2.length;

        int x = 0, y = 0;

        List<Integer> ans = new ArrayList<>();

        while (x < i && y < j) {
            if (nums1[x] == nums2[y]) {
                ans.add(nums1[x]);
                x++;
                y++;
            } else if (nums1[x] < nums2[y]) {
                x++;
            } else {
                y++;
            }
        }

        int[] answer = new int[ans.size()];
        int p = 0;
        for (int a: ans) {
            answer[p] = a;
            p++;
        }
        return answer;
    }
}
