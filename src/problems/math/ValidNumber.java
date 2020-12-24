package problems.math;

/**
 * Validate if a given string can be interpreted as a decimal number.
 */
public class ValidNumber {
    public static void main(String[] args) {
        System.out.println(isNumber("0"));
        System.out.println(isNumber(" 0.1"));
        System.out.println(isNumber("abc"));
        System.out.println(isNumber("2e10"));
        System.out.println(isNumber("-9e-10"));
        System.out.println(isNumber("-1e"));
        System.out.println(isNumber("1.95e-6"));
        System.out.println(isNumber("+-4"));
        System.out.println(isNumber("24124a45e5"));
    }

    // some rules to be a number
    // it can have single .
    // it can have single exp e
    // +/- should only be at index 0 or after e
    // can only have 0-9
    // there needs to be a number before and after e. ex: 3e3 or 4e-4
    // not . after e
    private static boolean isNumber(String s) {
        // trim spaces
        s = s.trim();

        boolean hasDot = false;
        boolean hasExp = false;
        boolean hasNumber = false;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                hasNumber = true;
            } else if (s.charAt(i) == '.') {
                if (hasDot || hasExp) {
                    return false;
                }
                hasDot = true;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else if (s.charAt(i) == 'e') {
                if (hasExp || !hasNumber) {
                    return false;
                }
                hasExp = true;
                hasNumber = false;
            } else {
                return false;
            }
        }

        return hasNumber;
    }
}
