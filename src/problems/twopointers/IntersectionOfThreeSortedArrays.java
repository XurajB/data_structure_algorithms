package problems.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
 */
public class IntersectionOfThreeSortedArrays {
    // O(n), O(1). n = total length
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();
        int p1=0;
        int p2=0;
        int p3=0;
        while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) {
            if (arr1[p1] == arr2[p2] && arr1[p1] == arr3[p3]) {
                ans.add(arr1[p1]);
                p1++;
                p2++;
                p3++;
            } else {
                if (arr1[p1] < arr2[p2]) {
                    p1++;
                } else if (arr2[p2] < arr3[p3]) {
                    p2++;
                } else {
                    p3++;
                }
            }
        }
        return ans;
    }
}
