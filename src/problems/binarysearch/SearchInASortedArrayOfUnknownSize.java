package problems.binarysearch;

/**
 * Given an integer array sorted in ascending order, write a function to search target in nums.
 * If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).
 *
 * You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.
 */
public class SearchInASortedArrayOfUnknownSize {
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == 2147483647) {
            return -1;
        }
        // perform binary search every 1000 interval
        for (int i = 0; i <= 100000; i = i + 1000) {
            int lo = i;
            int hi = i + 1000;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (reader.get(mid) == target) {
                    return mid;
                } else if (reader.get(mid) > target || reader.get(mid) == 2147483647) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }

        return -1;
    }

    interface ArrayReader {
        int get(int index);
    }
}
