package problems.math;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 * https://leetcode.com/problems/integer-to-english-words/
 */
public class IntegerToEnglishWords {
    public static void main(String[] args) {
        IntegerToEnglishWords words = new IntegerToEnglishWords();
        System.out.println(words.numberToWords(Integer.MAX_VALUE - 1));
    }

    private int H = 100;
    private int T = 1000;
    private int M = 1000000;
    private int B = 1000000000;

    String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return helper(num).trim();
    }

    // O(N)
    private String helper(int num) {
        StringBuilder ans = new StringBuilder();

        if (num >= B) {
            ans.append(helper(num / B)).append(" Billion ").append(helper(num % B));
        } else if (num >= M) {
            ans.append(helper(num / M)).append(" Million ").append(helper(num % M));
        } else if (num >= T) {
            ans.append(helper(num / T)).append(" Thousand ").append(helper(num % T));
        } else if (num >= H) {
            ans.append(helper(num / H)).append(" Hundred ").append(helper(num % H));
        } else if (num >= 20 && num < 100) {
            ans.append(tens[num / 10]).append(" ").append(ones[num % 10]);
        } else if (num >= 10 && num < 20) {
            ans.append(teens[num % 10]);
        } else {
            ans.append(ones[num]);
        }

        return ans.toString().trim();
    }
}
