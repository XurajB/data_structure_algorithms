package algorithms.sorts;

/**
 * Created by Xuraj on 11/30/2019.
 * Quick sort - In place, efficient sorting algorithm
 * Divide and conquer. Most practical choice and quick sorting algorithm
 * O(nLogn) - average case - time
 * O(n^2) - worst case (most of the time this is avoided by randomization of the pivot)
 * O(1) - space
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
