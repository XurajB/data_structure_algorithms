package problems.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 */
public class FindKClosestElements {
    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[] {1,2,3,4,5}, 4, 3));
    }

    // O(N)
    private static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int high = arr.length - 1;

        // we need k elements
        while (high - lo >= k) {
            // if left diff is higher, then move closer to less diff
            if (Math.abs(arr[lo] - x) > Math.abs(arr[high] - x)) {
                lo++;
            } else {
                high--;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = lo; i <= high; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    // since the array is sorted, we can also do binary search to improve complexity
    // for the first two pointer, we do binary search
    // (logn)+k
    private static List<Integer> findClosestElement2(int[] arr, int k, int x) {
        int lo = 0;
        int high = arr.length - k - 1; // range of all possible start of size k + 1
        while (lo <= high) {
            int mid = lo + (high - lo)/2;
            if (Math.abs(arr[mid] - x) > Math.abs(arr[mid+k] - x)) {
                lo = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(arr[lo + i]);
        }
        return res;
    }
}
