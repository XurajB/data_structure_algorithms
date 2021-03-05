package problems.math;

public class FactorialTrailingZeros {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(10));
    }

    private static int trailingZeroes(int n) {
        // ans will have trailing zeroes if n has factors of 5s and 2s. 5*2 create 10 which has trailing zeros.
        // to have multiple 0s, we need unique pairs of 2s and 5s.
        // 10! = 1*2*3*4*5*6*7*8*9*10 = 2 factors of 5 (5 and 10). we will have ample factors of 2s so don't worry about 2s (multiple of 2s are higher than 5s, so 5 is dominant)
        // ans = 2
        // recursive - logn
        if (n == 0) {
            return 0;
        }
        int factors = n / 5;
        return factors + trailingZeroes(n/5);
    }

    // iterative
    private static int trailingZeroes2(int n) {
        int count = 0;
        while (n > 0) {
            count += n/5;
            n = n /5;
        }
        return count;
    }
}
