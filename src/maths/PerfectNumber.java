package maths;

/**
 * Created by Xuraj on 12/11/2019.
 * A perfect number is a positive integer that is the sum of its positive divisors except itself.
 * Example: 6 is divisible by 1, 2, 3. 1 + 2 + 3 = 6 so 6 is a perfect number
 *
 * note: anything % 0 is undefined.
 */
public class PerfectNumber {
    public static void main(String[] args) {
        System.out.println(isPerfectNumber(6));
    }

    private static boolean isPerfectNumber(int number) {
        int total = 0;
        for (int  i = 1; i < number; i++) {
            if (number % i == 0) {
                total = total + i;
            }
        }

        return total == number;
    }
}
