package problems.binarysearch;

/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * Find the kth positive integer that is missing from this array.
 */
public class KthMissingPositiveNumber {
    public static void main(String[] args) {
        System.out.println(findKthPositive(new int[] {2,3,4,7,11}, 5));
    }

    // O(N)
    private static int findKthPositive(int[] arr, int k) {
        if (k <= arr[0] - 1) {
            return k;
        }
        int diff = arr[0] - 1;
        k = k - diff;

        for (int i = 0; i < arr.length - 1; i++) {
            diff = arr[i+1] - arr[i] - 1;

            if (diff >= k) {
                return arr[i] + k;
            }
            k = k - diff;
        }
        return arr[arr.length - 1] + k;
    }

    // O(logN)
    private static int findKthPositive2(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;

        // 2, 3, 4, 7 , 11
        // 1, 2, 3, 4, 5
        // how many numbers missing before 11? 11 - 5 = 6 == missing before arr[i] = arr[i] - i - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] - mid - 1 < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left + k;
    }

    // alternate
    public int findKthPositive3(int[] arr, int k) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] - mid - 1 < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left + k;
    }
}
