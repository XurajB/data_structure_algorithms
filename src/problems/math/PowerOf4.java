package problems.math;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 */
public class PowerOf4 {
    public static void main(String[] args) {
        System.out.println(isPowerOfFour(16));
    }

    // O(logn)
    private static boolean isPowerOfFour(int num) {
        if (num == 0) {
            return  false;
        }
        while (num % 4 == 0) {
            num = num / 4;
        }
        return num == 1;
    }

    //
    // we can also precompute values, log4(2^31-1) = 15, so we just need to precompute 15 values: 4^0, 4^1.. and put it in a set. O(1)
    //

    // O(1)
    // x = 4^a
    // a = log4(x) = 1/2 log2(x)
    // we need to check if log2(x) is even
    private static boolean isPowerOfFour2(int num) {
        return num > 0 && (Math.log(num) / Math.log(2)) % 2 == 0;
    }
}
