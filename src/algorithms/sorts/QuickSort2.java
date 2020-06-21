package algorithms.sorts;

import java.util.Arrays;

/**
 * Using merge sort using partition (similar concept to QuickSort)
 * Similar to Quick Select algorithm
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int[] nums = {1,8,6,4,3,0,7,5,5,0};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private static void quickSort(int[] n, int low, int high) {
        if (low < high) {
            int partition = partition(n, low, high);

            // recursively sort left and right sides after partition
            quickSort(n, low, partition-1);
            quickSort(n, partition+1, high);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        /* This function takes last element as pivot, places the pivot element at its correct position in sorted array, and places all
       smaller (smaller than pivot) to left of pivot and all greater elements to right of pivot */
        int pivot = nums[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }

        swap(nums, i+1, high); // swap it back from high
        return i+1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
