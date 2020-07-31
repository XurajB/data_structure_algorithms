package problems.sorting;

import java.util.Arrays;

/**
 * Write a function flip(arr, k) that reverses the order of the first k elements in the array arr.
 * Write a function pancakeSort(arr) that sorts and returns the input array. You are allowed to use only the function flip you wrote in the first step in order to make changes in the array.
 */
public class PancakeSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(pancakeSort(new int[] {1,5,4,3,2})));
    }

    private static int[] pancakeSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int maxIndex = findMaxIndex(arr, i);
            flip(arr, maxIndex + 1);
            flip(arr, i + 1);
        }
        return arr;
    }

    private static void flip(int[] arr, int k) {
        int left = 0;
        int right = k - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int findMaxIndex(int[] arr, int k) {
        int maxindex = 0;
        int max = arr[0];
        for (int i = 1; i <= k; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxindex = i;
            }
        }
        return maxindex;
    }
}
