package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 * https://leetcode.com/problems/reorder-data-in-log-files/
 */
public class ReorderDataInLogFiles {
    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        System.out.println(Arrays.toString(reorderLogFiles(logs)));
    }

    // Time: NLogN, Space: N
    private static String[] reorderLogFiles(String[] logs) {
        Comparator<String> comp = (Comparator<String>) (s1, s2) -> {
            int firstSpace = s1.indexOf(" ");
            int secondSpace = s2.indexOf(" ");

            boolean isFirstDigit = Character.isDigit(s1.charAt(firstSpace+1));
            boolean isSecondDigit = Character.isDigit(s2.charAt(secondSpace+1));

            if (!isFirstDigit && !isSecondDigit) {

                int cmp = s1.substring(firstSpace+1).compareTo(s2.substring(secondSpace+1));
                if (cmp != 0) {
                    return cmp;
                }
                return s1.compareTo(s2);
            }
            if (isFirstDigit && isSecondDigit) {
                return 0;
            }
            else if (isFirstDigit) {
                return 1; // 1 means they are higher, we want numbers print after letters
            }
            return -1;
        };

        Arrays.sort(logs, comp);
        return logs;
    }
}
