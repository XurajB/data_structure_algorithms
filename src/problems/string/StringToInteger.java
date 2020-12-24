package problems.string;

/**
 * Implement atoi which converts a string to an integer.
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned.
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
