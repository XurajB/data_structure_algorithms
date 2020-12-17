package problems.dynamic;

/**
 * There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
 * There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 * Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
 * Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
 */
public class MinimumNumberOfTaps {
    public static void main(String[] args) {
        int[] ranges = {1,2,1,0,2,1,0,1};
        System.out.println(minTaps(7, ranges));
    }

    // O(N)
    private static int minTaps(int n, int[] ranges) {
        // construct the array
        // we need to move the values into the leftmost point so we can water
        // the problem now becomes #JumpGame2. for index i: we have left - right which is i-arr[i] to i+arr[i]
        // left is the index where water can reach up to right
        int[] arr = new int[n + 1];
        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i] == 0) {
                continue;
            }
            int left = Math.max(0, i - ranges[i]); // we don't care about -ve ranges
            arr[left] = Math.min(arr.length - 1, i + ranges[i]);
        }

        // below is same as jump game
        int pumps = 0;
        int curFarthest = 0;
        int maxFarthest = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i > maxFarthest) {
                return -1; // we can't reach this
            }
            maxFarthest = Math.max(maxFarthest, i + arr[i]);
            if (i < arr.length - 1 && i == curFarthest) { // no need to jump at nums.length - 1, eg [2,3,1,1,4]
                // need to move to next
                pumps++;
                curFarthest = maxFarthest;
            }
        }

        return pumps;
    }
}
