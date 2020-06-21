package problems.binarysearch;

/**
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * [0,1,3,5,6] -> ans is 3 because we have 3 values that are equal to or greater than 3. 1 is also valid but we want to maximize the answer
 * [0,1,4,5,6] -> ans cannot be 4 because we don't have 4 values that are equal to or greater than 4. The answer is still 3.
 */
public class HIndex2 {
    public static void main(String[] args) {
        int[] n = new int[] {0,1,3,5,6};
        System.out.println(hIndex(n));
    }

    // O(logn)
    // if the array is not sorted sort it first
    private static int hIndex(int[] citations) {
        int n = citations.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (citations[mid] == (n - mid)) {
                // check if we have mid number of values on the right side (sorted)
                // we found the optimal answer
                return citations[mid];
            } else if (citations[mid] > (n-mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // in case of 4, low is at 1, and we know the answer is between 1 and 4
        return n - low;
    }
}
