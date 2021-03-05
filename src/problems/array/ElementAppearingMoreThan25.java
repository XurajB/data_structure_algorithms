package problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.
 * Return that integer.
 */
public class ElementAppearingMoreThan25 {
    public static void main(String[] args) {
        System.out.println(findSpecialInteger(new int[] {1,2,2,6,6,6,6,7,10}));
    }

    // Binary search
    private static int findSpecialInteger(int[] arr) {
        if (arr.length == 1) return arr[0];

        int length = arr.length;
        List<Integer> firstThreeQuarters =
                new ArrayList<>(Arrays.asList(arr[length / 4], arr[length / 2], arr[3 * length / 4]));

        for (Integer elem : firstThreeQuarters) {
            int pos = firstOccurrence(arr, elem);
            if (arr[pos + length / 4] == elem)
                return elem;
        }
        return -1;
    }

    private static int firstOccurrence(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (target > arr[mid])
                start = mid + 1;
            else
                end = mid;
        }
        return end;
    }

    //////
    // O(N)
    private static int findSpecialInteger2(int[] arr) {
        int n = arr.length;
        int t = n / 4;

        for (int i = 0; i < n - t; i++) {
            if (arr[i] == arr[i+t]) {
                return arr[i];
            }
        }

        return -1;
    }
}
