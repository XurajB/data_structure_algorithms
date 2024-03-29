package algorithms.sorts;

/**
 * Created by Xuraj on 11/30/2019.
 * Quick sort - In place, efficient sorting algorithm
 * Divide and conquer. Most practical choice and quick sorting algorithm
 * O(nLogn) - average case - time
 * O(n^2) - worst case (most of the time this is avoided by randomization of the pivot)
 * O(logn) - space (recursion)
 */
public class QuickSort {
    private int[] dataSet;

    public void sort(int[] dataSet) {
        this.dataSet = dataSet;
        quickSort(0, dataSet.length - 1);
    }

    public int[] returnSorted() {
        return dataSet;
    }

    private void quickSort(int low, int high) {
        int i = low;
        int j = high;

        int pivot = dataSet[low + (high - low)/2];

        // divide into two lists
        // the idea is if we sort all left elements of the pivot is smaller than pivot and all elements on right are higher than pivot element,
        // we can say pivot is in the sorted position, rest of the elements may or may not be sorted. we recursively perform quick sort on either sides

        while (i <= j) {
            // if current value from the left is smaller than pivot, goto next element
            while (dataSet[i] < pivot) {
                i++;
            }
            // if current value from the right is larger than pivot, goto next element
            while (dataSet[j] > pivot) {
                j--;
            }

            // if we find a value in the left higher than pivot and if we have a value in the right less than the pivot
            // we swap them
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(low, j);
        }
        if (high > i) {
            quickSort(i, high);
        }
    }

    private void swap(int i, int j) {
        int temp = dataSet[i];
        dataSet[i] = dataSet[j];
        dataSet[j] = temp;
    }
}
