package problems.bfsdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 */
public class StrobogrammaticNumber {
    public static void main(String[] args) {
        System.out.println(findStrobogrammatic(4));
    }

    private static List<String> findStrobogrammatic(int n) {
        return helper(n, n); // index, total
    }

    // during each recursion, we iterate the list and add 5 more things
    // O(5^n/2), O(N)
    private static List<String> helper(int n, int m) {
        if (n == 0) {
            return new ArrayList<>(Arrays.asList("")); // need empty string, for even numbers
        }
        if (n == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8")); //single numbers that are strobogrammatic
        }

        // we are adding numbers on both sides, so n-2
        List<String> next = helper(n-2, m);

        List<String> ans = new ArrayList<>();
        for (String s: next) {
            // we cannot have 0s on front or back
            if (n != m) {
                ans.add("0" + s + "0");
            }
            ans.add("1" + s + "1");
            ans.add("6" + s + "9");
            ans.add("8" + s + "8");
            ans.add("9" + s + "6"); // note
        }

        return ans;
    }
}
