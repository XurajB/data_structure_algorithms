package algorithms.sorts;

/**
 * Created by Xuraj on 11/27/2019.
 * Sorting algorithms
 */
public class Sort {

    // slow sorting. walk through the array and swap out if order elements.
    // worse case: O(n^2) time, we don't need extra memory to run this so O(1)
    public int[] bubbleSort(int[] dataSet) {
        boolean isSorted;
        int inputLength = dataSet.length;

        for (int i =0; i < inputLength; i++) {
            isSorted = true;

            for (int j = 1; j < inputLength - i; j++) {
                if (dataSet[j-1] > dataSet[j]) { // swap < or > for ascending or descending
                    swap(dataSet, j-1, j);
                    isSorted = false;
                }
            }

            if (isSorted) { // so that we get out of the loop to prevent unnecessary processing
                break;
            }
        }

        return dataSet;
    }

    /**
     * All items to the left are smaller (greater) - that means sorted
     * Key is compared with every element to the left and swapped if greater or less
     * Complexity: O(n^2) time, space O(1)
     * @param dataSet unsorted array
     * @return sorted array
     */
    public int[] insertionSort(int[] dataSet) {

        for (int i = 1; i < dataSet.length; i++) {

            int key = dataSet[i];
            int j = i - 1;

            while (j >= 0 && key < dataSet[j]) {
                dataSet[j+1] = dataSet[j];
                j--;
            }
            dataSet[j+1] = key;
        }

        return dataSet;
    }


    /**
     * Merge Sort - Efficient, general purpose comparison based sorting algorithm.
     * It is a divide and conquer algorithm.
     * Complexity: O(nLogn) time, space: O(n)
     * @param dataSet unsorted array
     * @return sorted array
     */
    public int[] mergeSort(int[] dataSet) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(dataSet);
        return mergeSort.returnSorted();
    }

    /**
     * Quick sort - In place, efficient sorting algorithm
     * Divide and conquer. Most practical choice and quick sorting algorithm
     * O(nLogn) - average case - time
     * O(n^2) - worst case (most of the time this is avoided by randomization of the pivot)
     * O(1) - space
     * @param dataSet original unsorted array
     * @return sorted array
     */
    public int[] quickSort(int[] dataSet) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort(dataSet);
        return quickSort.returnSorted();
    }

    private void swap(int[] dataSet, int j, int i) {
        int temp = dataSet[j];
        dataSet[j] = dataSet[i];
        dataSet[i] = temp;
    }
}
