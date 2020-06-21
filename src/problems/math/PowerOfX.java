package problems.math;

/**
 * Implement a function to calculate X^N. Both X and N can be positive or negative. You can assume that overflow doesn't happen.
 */
public class PowerOfX {
    public static void main(String[] args) {
        System.out.println(power(2, 10));
    }

    private static float power(int x, int power) {
        // if power is -ve, then it is 1/x^power
        // if power is 0, it is 1
        // if power is +ve, x^power/2 * x^power/2
        // if power is <=0, x is 0, it is undefined
        if (x == 0 && power <= 0) {
            throw new ArithmeticException("Undefined");
        }
        int result = positivePower(Math.abs(x), Math.abs(power));
        // handle -ve power
        if (power < 0) {
            result = 1/result;
        }
        // handle -ve x
        if (x < 0 && power % 2 != 0) {
            result = -1 * result; // power needs to be neg to be neg
        }
        return result;
    }

    // O(logP)
    private static int positivePower(int x, int power) {
        if (power == 0) {
            return 1;
        }
        if (power == 1) {
            return x;
        }
        int halfpower = positivePower(x, power/2);
        if (power % 2 == 0) {
            return halfpower * halfpower;
        } else {
            return x * halfpower * halfpower;
        }
    }
}
