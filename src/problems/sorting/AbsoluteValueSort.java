package problems.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of integers arr, write a function absSort(arr) that sorts the array according to the absolute values of numbers in arr.
 * If two numbers have the same absolute value, sort them according to sign, where -ve numbers come before the positive numbers
 */
public class AbsoluteValueSort {
    public static void main(String[] args) {

    }

    private static int[] absSort(int[] arr) {
        // sort will not take primitive
        Integer[] copy = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        Arrays.sort(copy, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int x = Math.abs(a);
                int y = Math.abs(b);
                if (x==y) {
                    return a < b ? -1 : 1;
                }
                return x-y;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = copy[i];
        }
        return arr;
    }
}
