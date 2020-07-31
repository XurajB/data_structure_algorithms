package problems.binarysearch;

/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 */
public class GuessNumberHigherOrLower {
    // provided method
    int guess(int n) {
        // impl
        return 0;
    }

    // O(logn)
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low)/2;
            int result = guess(mid);
            if (result == 0) {
                return mid;
            } else if (result == -1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
