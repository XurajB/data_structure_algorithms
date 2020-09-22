package problems.math;

/**
 * Calculate nth root of a number, with an error of 0.001
 */
public class RootOfNumber {
    public static void main(String[] args) {
        System.out.println(root(7, 3));
    }

    // 1000 * log(x)
    private static double root(double x, int n) {
        // n root x:  Math.pow(i, n) = x. find i
        double lo = 0;
        double high = x;

        while (lo <= high) {
            double mid = lo + (high - lo) / 2.0;

            double lower = Math.pow(mid - 0.001, n);
            double higher = Math.pow(mid + 0.001, n);

            if (x >= lower && x <= higher) {
                // we can return mid, but if we need answer is format x.xxx
                return Math.round(mid * 1000.0)/1000.0;
            } else if (x < lower) {
                high = mid - 0.001;
            } else {
                lo = mid + 0.001;
            }
        }

        return -1;
    }
}
