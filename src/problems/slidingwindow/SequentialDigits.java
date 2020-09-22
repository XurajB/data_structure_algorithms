package problems.slidingwindow;

import java.util.ArrayList;
import java.util.List;

//An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
//Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
public class SequentialDigits {
    public static void main(String[] args) {
        System.out.println(sequentialDigits(100, 300));
    }

    private static List<Integer> sequentialDigits(int low, int high) {
        String sample = "123456789";
        int n = 10;
        List<Integer> nums = new ArrayList<>();

        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        for (int length = lowLen; length < highLen + 1; ++length) {
            for (int start = 0; start < n - length; ++start) {
                int num = Integer.parseInt(sample.substring(start, start + length));
                if (num >= low && num <= high) nums.add(num);
            }
        }
        return nums;
    }
}
