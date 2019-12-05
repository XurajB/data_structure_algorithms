package algorithms;

/**
 * Created by Xuraj on 11/27/2019.
 * Various searching algorithms
 */
public class Search {

    // linear search - one by one - O(n)
    public int linearSearch(int[] dataSet, int value) {
        for (int i =0; i< dataSet.length; i++) {
            if (dataSet[i]==value) {
                return i;
            }
        }
        return -1;
    }

    // binary search - o(log n) - that means search is done until n/2 = 1
    // prerequisite - the array needs to be presorted
    public int binarySearch(int[] dataSet, int value) {
        int low = 0;
        int high = dataSet.length - 1;

        while (high >= low) {
            int middle = low + (high - low) / 2;
            if (dataSet[middle] == value) {
                return middle;
            } else if (dataSet[middle] > value) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1; // can't find
    }
}
