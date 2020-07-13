package problems.math;

/**
 *
 */
public class CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(3));
    }

    // O(1)
    private static int countNumbersWithUniqueDigits(int n) {
        // f(1): 10 (0,1,2,3..9) there are 10 unique numbers
        // f(2): 9 * 9, because for each number i from 1..9 we can pick j to form a 2-digit number ij and there are 9 uniques from i for j to choose from
        // f(3): f(2) * 8, for each number with unique digits of length 2 ij, we can pick k to form a 3 digit number ijk and there are 8 numbers that are different from i and j
        // f(4): f(3) * 7
        // .. f(10): 9 * 9 * 8 * 7 * 6 * .. * 1
        // f(11) = 0 = f(12) = f(13).. any number with length > 0, cannot have unique digits because there are only 10 to pick from

        if (n == 0) {
            return 1;
        }
        int ans = 10; // start with f(1)
        int uniqueDigits = 9;
        int availableDigits = 9;
        while (n-- > 1 && availableDigits > 0) {
            uniqueDigits = uniqueDigits * availableDigits;
            ans += uniqueDigits;
            availableDigits--;
        }

        return ans;
    }
}
