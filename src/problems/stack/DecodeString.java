package problems.stack;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 */
public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }

    // O(max k * n)
    private static String decodeString(String s) {
        // keep two stacks, one for count and another for string
        Stack<Integer> counts = new Stack<>();
        Stack<String> texts = new Stack<>();

        String res = "";
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                counts.push(num);
            } else if (s.charAt(i) == '[') {
                texts.push(res);
                res = "";
                i++;
            } else if (s.charAt(i) == ']') {
                StringBuilder sb = new StringBuilder(texts.pop());
                int count = counts.pop();
                for (int j = 0; j < count; j++) {
                    sb.append(res);
                }
                res = sb.toString();
                i++;
            } else {
                res += s.charAt(i++);
            }
        }

        return res;
    }

    // recursive
    private static String decodeString2(String s) {
        StringBuilder cur = new StringBuilder();

        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '[') {
                // mark open and close indx, and count
                int begin = i;
                i++;
                int count = 1; // including one we found

                while (count != 0) {
                    if (s.charAt(i) == '[') {
                        count++;
                    } else if (s.charAt(i) == ']') {
                        count--;
                    }
                    i++;
                }

                i--;

                // recursively solve what is inside brackets
                String sub = decodeString(s.substring(begin+1, i));
                for (int k = 0; k < num; k++) {
                    cur.append(sub);
                }
                num = 0;
            } else {
                cur.append(s.charAt(i));
            }
        }
        return cur.toString();
    }
}
