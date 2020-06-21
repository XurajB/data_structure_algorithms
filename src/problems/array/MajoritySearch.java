package problems.array;

/**
 * Given an array of integers, find if there is an integer that occurs greater than 50% of the time.
 */
public class MajoritySearch {
    public static void main(String[] args) {

    }

    // O(N), O(1)
    private static int majoritySearch(int[] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        // first get the first element as candidate
        // cancel out if next element is not that element
        // majority element will have count > 1 at the end
        int candidate = a[0];
        int count = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == candidate) {
                count++;
            } else if (count > 0) {
                count--;
            } else {
                candidate = a[i];
                count = 1;
            }
        }

        // last element will be our candidate. verify by counting
        count  = 0;
        for (int value : a) {
            if (candidate == value) {
                count++;
            }
        }
        if (count >= a.length / 2) {
            return candidate;
        }
        return -1;
    }
}
