package problems.array;

/**
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 */
public class MaximumSwap {
    public static void main(String[] args) {
        System.out.println(maximumSwap(2736));
    }

    private static int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] last = new int[10];
        for (int i = 0; i < digits.length; i++) {
            last[digits[i] - '0'] = i;
        }
        // want to the last pace for each digit because we want to swap with the last position, ex 6996
        for (int i = 0; i < digits.length; i++) {
            // check if there exists any number greater than digits[i] (check from digits[i] to 9)
            // do don't have to start from 9, we can also start from max.. which will improve runtime a little
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (last[k] > i) { // compare index
                    char temp = digits[i];
                    digits[i] = digits[last[k]];
                    digits[last[k]] = temp;

                    return Integer.parseInt(new String(digits));
                }
            }
        }

        return num;
    }
}
