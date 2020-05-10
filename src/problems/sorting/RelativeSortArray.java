package problems.sorting;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 * https://leetcode.com/problems/relative-sort-array/
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        System.out.println(Arrays.toString(relativeSortArray(arr1, arr2)));
    }

    // O(NLogN), O(N)
    private static int[] relativeSortArray(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n: arr1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int index = 0;
        for (int n: arr2) {
            while (map.get(n) > 0) {
                arr1[index++] = n;
                map.put(n, map.get(n) - 1);
            }
        }

        // remaining elements
        for (int n: map.keySet()) {
            while (map.get(n) > 0) {
                arr1[index++] = n;
                map.put(n, map.get(n) - 1);
            }
        }
        return arr1;
    }
}
