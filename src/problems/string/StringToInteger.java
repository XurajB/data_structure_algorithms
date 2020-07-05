package problems.string;

/**
 *
 */
public class StringToInteger {
    public static void main(String[] args) {
        System.out.println(myAtoi("-345sdf"));
    }

    private static int myAtoi(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        int i = 0;
        while (str.charAt(i) == ' ') {
            i++;
            if (i == str.length()) { // cases like "   "
                return 0;
            }
        }

        int num = 0;
        int sign = 1; // plus

        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            sign = str.charAt(i) == '+' ? 1 : -1;
            i++;
        }

        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                // check for non numbers
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + (str.charAt(i) - '0');
            i++;
        }

        return sign * num;
    }
}
