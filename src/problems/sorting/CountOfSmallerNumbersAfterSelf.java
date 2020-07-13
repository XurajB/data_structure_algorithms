package problems.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 */
public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
        System.out.println(countSmaller(nums));
    }

    static int[] count;
    private static List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        mergeSort(nums, indexes, 0, nums.length - 1);
        for (int value : count) {
            ans.add(value);
        }
        return ans;
    }

    private static void mergeSort(int[] nums, int[] indexes, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(nums, indexes, start, mid);
            mergeSort(nums, indexes, mid+1, end);

            merge(nums, indexes, start, mid, end);
        }
    }

    private static void merge(int[] nums, int[] indexes, int start, int mid, int end) {
        int i = start; // left index
        int j = mid + 1; // right index
        int sortIndex = 0;

        int rightCount = 0;

        int[] newIndexes = new int[end - start + 1];
        while (i <= mid && j <= end) {
            if (nums[indexes[j]] < nums[indexes[i]]) {
                newIndexes[sortIndex] = indexes[j];
                rightCount++;
                j++;
            } else {
                newIndexes[sortIndex] = indexes[i];
                count[indexes[i]] += rightCount;
                i++;
            }
            sortIndex++;
        }
        while (i <= mid) {
            newIndexes[sortIndex] = indexes[i];
            count[indexes[i]] += rightCount;
            i++;
            sortIndex++;
        }
        while (j <= end) {
            newIndexes[sortIndex] = indexes[j];
            j++;
            sortIndex++;
        }
        for (int x = start; x <= end; x++) {
            indexes[x] = newIndexes[x - start];
        }
    }
}
