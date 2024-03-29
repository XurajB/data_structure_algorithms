package problems.math;

/**
 * Implement a function to calculate X^N. Both X and N can be positive or negative. You can assume that overflow doesn't happen.
 */
public class PowerOfX {
    public static void main(String[] args) {
        System.out.println(power(2, 10));
    }

    private static double power(double x, int power) {
        // if power is -ve, then it is 1/x^power
        // if power is 0, it is 1
        // if power is +ve, x^power/2 * x^power/2
        // if power is <=0, x is 0, it is undefined
        if (x == 0 && power <= 0) {
            throw new ArithmeticException("Undefined");
        }
        double result = positivePower(Math.abs(x), Math.abs(power));
        // handle -ve power
        if (power < 0) {
            result = 1/result;
        }
        // handle -ve x
        if (x < 0 && power % 2 != 0) {
            result = -result; // power needs to be neg to be neg
        }
        return result;
    }

    // O(logP)
    private static double positivePower(double x, int power) {
        if (power == 0) {
            return 1;
        }
        if (power == 1) {
            return x;
        }
        double halfpower = positivePower(x, power/2);
        if (power % 2 == 0) {
            return halfpower * halfpower;
        } else {
            return x * halfpower * halfpower;
        }
    }


    /////////////////////
    // similar solution
    // log(n)
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
    public double myPow(double x, int n) {
        // if n is -ve min, then converting to pos will be 0 int. so it is better to do conversion now and calc
        long N = n; // convert this to long so we can do -n
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    // iterative

}
