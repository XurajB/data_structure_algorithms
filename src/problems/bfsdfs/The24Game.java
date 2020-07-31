package problems.bfsdfs;

/**
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 */
public class The24Game {
    public static void main(String[] args) {
        int[] nums = {};
        System.out.println(judgePoint24(nums));
    }

    private static boolean judgePoint24(int[] nums) {
        double[] a = new double[] {nums[0], nums[1], nums[2], nums[3]};
        return helper(a);
    }

    // O(1), there are 4 cards and we chose two of them which is 12 ways and perform one of 4 operations
    // we have 3 remaining cards, and we chose 2 and perform 4 operations: 6 * 4
    // we have 2 remaining cards, and we chose 2 and perform 4 operations: 2 * 4
    // 12 * 4 * 6 * 4 * 2 * 4 = O(9216) total possibilities
    private static boolean helper(double[] a) {
        if (a.length == 1) {
            return Math.abs(a[0] - 24.0) < 0.001;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                // size went down by 1, because we removed two numbers
                // copy all elements except i, j to d
                double[] d = new double[a.length - 1];
                for (int k = 0, index = 0; k < a.length; k++) {
                    if (k != i && k != j) {
                        d[index++] = a[k];
                    }
                }
                // we have one space left
                for (double num: getCombinations(a[i], a[j])) {
                    d[d.length - 1] = num;
                    if (helper(d)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static double[] getCombinations(double a, double b) {
        return new double[] {a+b, a-b, b-a, a*b, a/b, b/a};
    }
}
