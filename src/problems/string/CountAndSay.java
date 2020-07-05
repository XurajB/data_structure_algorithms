package problems.string;

/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 *
 * looks at one step ahead. it starts with 1. so it is one 1, so 11. this is equivalent to two 1s, so next is 21 and so on
 */
public class CountAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }

    private static String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            s = next(s);
        }
        return s;
    }

    private static String next(String s) {
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            } else {
                sb.append(count);
                sb.append(c);
                c = s.charAt(i);
                count = 1;
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }
}
