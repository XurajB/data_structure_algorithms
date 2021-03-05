package problems.binarysearch;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Follow up: Do not use any built-in library function such as sqrt.
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }
        long left = 2;
        long right = num/2;
        while (left <= right) {
            long mid = left + (right - left)/2;
            long guess = mid * mid;
            if (guess == num) {
                return true;
            } else if (guess < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
