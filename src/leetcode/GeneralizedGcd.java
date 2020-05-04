package leetcode;

/**
 * The GDC, also called highest common factor (HCF) of N numbers is the largest positive integer that divides all numbers without giving a reminder
 * Write an algorithm to determine GCD of N positive integers.
 */
public class GeneralizedGcd {
    public static void main(String[] args) {
        int[] arr = {2,4,6,8,10};
        System.out.println(generalizedGcd(5,arr));
    }

    private static int generalizedGcd(int n, int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = arr[0];
        for (int i = 1; i < n; i++) {
            result = gcd(arr[i], result);
            if (result == 1) {
                return result;
            }
        }
        return result;
    }

    private static int gcd(int a, int b) {
        int answer = 0;
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
            answer = a;
        }
        return answer;
    }
}
