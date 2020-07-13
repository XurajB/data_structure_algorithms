package problems.math;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 */
public class GrayCode {
    public static void main(String[] args) {
        System.out.println(grayCode(3));
    }

    private static List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);

        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1;  j >= 0; j--) {
                ans.add(ans.get(j) + (1 << i));
            }
        }

        return ans;
    }
}
