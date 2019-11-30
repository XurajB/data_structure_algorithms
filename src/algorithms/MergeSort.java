package algorithms;

/**
 * Created by Xuraj on 11/30/2019.
 * Merge Sort - Efficient, general purpose comparison based sorting algorithm.
 * It is a divide and conquer algorithm.
 * Complexity: O(nLog n) time, space: O(n)
 */
public class MergeSort {

    private int[] dataSet;
    private int[] helper;

    public void sort(int[] dataSet) {
        this.dataSet = dataSet;
        this.helper = new int[dataSet.length];
        mergeSort(0, dataSet.length - 1);
    }

    // call after calling sort function
    public int[] returnSorted() {
        return dataSet;
    }

    private void mergeSort(int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            // sort left array
            mergeSort(low, mid);
            // sort right array
            mergeSort(mid + 1, high);
            // merge them both
            merge(low, mid, high);
        }
    }

    private void merge(int low, int middle, int high) {
        // copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = dataSet[i];
        }

        // pointers
        int i = low; // for left array
        int j = middle + 1; // for right array
        int k = low; // for original array

        // copy the smallest values from either left or right back to original array
        while (i <= middle && j <=high) {
            if (helper[i] <= helper[j]) {
                dataSet[k] = helper[i];
                i++;
            } else {
                dataSet[k] = helper[j];
                j++;
            }
            k++;
        }

        // copy the rest of the left side
        while ( i <= middle) {
            dataSet[k] = helper[i];
            i++;
            k++;
        }

        // since we are doing in place sorting, dataSet already has remaining elements from right side
    }
}
