package problems.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 * https://leetcode.com/problems/integer-to-english-words/
 */
public class IntegerToEnglishWords {
    int B = 1000000000;
    int M = 1000000;
    int T = 1000;
    int H = 100;

    Map<Integer, String> one = new HashMap<>();
    Map<Integer, String> teen = new HashMap<>();
    Map<Integer, String> hund = new HashMap<>();

    public static void main(String[] args) {
        IntegerToEnglishWords words = new IntegerToEnglishWords();
        System.out.println(words.numberToWords(Integer.MAX_VALUE - 1));
    }

    private String numberToWords(int num) {
        one.put(9, "Nine");
        one.put(8, "Eight");
        one.put(7, "Seven");
        one.put(6, "Six");
        one.put(5, "Five");
        one.put(4, "Four");
        one.put(3, "Three");
        one.put(2, "Two");
        one.put(1, "One");
        one.put(0, "");

        teen.put(19, "Nineteen");
        teen.put(18, "Eighteen");
        teen.put(17, "Seventeen");
        teen.put(16, "Sixteen");
        teen.put(15, "Fifteen");
        teen.put(14, "Fourteen");
        teen.put(13, "Thirteen");
        teen.put(12, "Twelve");
        teen.put(11, "Eleven");
        teen.put(10, "Ten");

        hund.put(9, "Ninety");
        hund.put(8, "Eighty");
        hund.put(7, "Seventy");
        hund.put(6, "Sixty");
        hund.put(5, "Fifty");
        hund.put(4, "Forty");
        hund.put(3, "Thirty");
        hund.put(2, "Twenty");

        return convert(num);
    }

    private String convert(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= B) {
            sb.append(convert(num / B)).append(" Billion ").append(convert(num % B));
        } else if (num >= M) {
            sb.append(convert(num / M)).append(" Million ").append(convert(num % M));
        } else if (num >= T) {
            sb.append(convert(num / T)).append(" Thousand ").append(convert(num % T));
        } else if (num >= H) {
            sb.append(convert(num / H)).append(" Hundred ").append(convert(num % H));
        } else if (num >= 20) {
            sb.append(hund.get(num / 10)).append(" ").append(convert(num % 10));
        } else if (num >= 10) {
            sb.append(teen.get(num));
        } else {
            sb.append(one.get(num));
        }

        return sb.toString().trim();
    }
}
