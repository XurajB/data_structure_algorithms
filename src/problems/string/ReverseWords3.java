package problems.string;

/**
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 */
public class ReverseWords3 {
    public static void main(String[] args) {
        System.out.println(reverseWords("Hi this ..is Mary's pen!!"));
    }

    private static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int last = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                s = reverse(s, last, i-1);
                last = i+1;
            }
        }
        s = reverse(s, last, s.length() - 1);
        return s;
    }

    private static String reverse(String s, int start, int end) {
        char[] chars = s.toCharArray();
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
