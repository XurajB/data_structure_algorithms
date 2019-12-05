import algorithms.Search;
import algorithms.Sort;
import algorithms.Strings;

import java.util.Arrays;

/**
 * Created by Xuraj on 11/12/2019.
 *
 * Entry point for algorithm samples
 */
public class MainAlgos {

    public static void main(String[] args) {
        MainAlgos algos = new MainAlgos();

        System.out.print("--- reverse string");
        algos.verifyStringReverse();

        System.out.println("--- linear search");
        algos.verifyLinearSearch();

        System.out.println("--- binary search");
        algos.verifyBinarySearch();

        System.out.println("--- bubble sort");
        algos.verifyBubbleSort();

        System.out.println("--- insertion sort");
        algos.verifyInsertionSort();

        System.out.println("--- merge sort");
        algos.verifyMergeSort();

        System.out.println("--- quick sort");
        algos.verifyQuickSort();
    }

    private void verifyStringReverse() {
        Strings algos1 = new Strings();
        algos1.reverseString("suraj");
        System.out.println();
        System.out.println(algos1.reverseString2("suraj"));
    }

    private void verifyLinearSearch() {
        int[] dataSet = new int[]{4, 5, 23, 98, 3, 92, -5};
        Search search = new Search();
        System.out.println(search.linearSearch(dataSet, 23));
        System.out.println(search.linearSearch(dataSet, 10));
    }

    private void verifyBinarySearch() {
        int[] dataSet = new int[]{4, 5, 23, 98, 3, 92, -5};
        Search search = new Search();
        System.out.println(search.binarySearch(dataSet, 23));
        System.out.println(search.binarySearch(dataSet, 10));
    }

    private void verifyBubbleSort() {
        int[] dataSet = new int[]{4, 5, 23, 98, 3, 92, -5, -20};
        Sort sort = new Sort();
        System.out.println(Arrays.toString(sort.bubbleSort(dataSet)));
    }

    private void verifyInsertionSort() {
        int[] dataSet = new int[]{4, 5, 23, 98, 3, 92, -5, -20};
        Sort sort = new Sort();
        System.out.println(Arrays.toString(sort.insertionSort(dataSet)));
    }

    private void verifyMergeSort() {
        int[] dataSet = new int[]{4, 5, 23, 98, 3, 92, -5, -20};
        Sort sort = new Sort();
        System.out.println(Arrays.toString(sort.mergeSort(dataSet)));
    }

    private void verifyQuickSort() {
        int[] dataSet = new int[]{4, 5, 23, 98, 3, 92, -5, -20};
        Sort sort = new Sort();
        System.out.println(Arrays.toString(sort.quickSort(dataSet)));
    }
}
