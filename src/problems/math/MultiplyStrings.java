package problems.math;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("10", "20"));
    }

    private static String multiply(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();

        int[] product = new int[n+m];
        for (int i = n-1; i >= 0; i--) {
            int first = num1.charAt(i) - '0';
            for (int j = m-1; j >=0; j--) {
                int second = num2.charAt(j) - '0';
                product[i+j+1] += first * second;
            }
        }

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        for (int i = product.length - 1; i >= 0; i--) {
            int current = product[i] + carry;
            sb.append(current % 10);
            carry = current / 10;
        }

        if (carry > 0) {
            sb.append(carry);
        }

        sb.reverse();

        // remove leading 0s
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}
