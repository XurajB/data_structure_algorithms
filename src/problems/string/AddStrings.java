package problems.string;

/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 * https://leetcode.com/problems/add-strings/
 */
public class AddStrings {
    public static void main(String[] args) {
        System.out.println(addStrings("209", "25"));
    }

    private static String addStrings(String num1, String num2) {
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (l1 >= 0 || l2 >= 0) {
            int a = (l1 >= 0) ? num1.charAt(l1) - '0' : 0;
            int b = (l2 >= 0) ? num2.charAt(l2) - '0' : 0;
            int sum = a + b + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            l1--;
            l2--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
