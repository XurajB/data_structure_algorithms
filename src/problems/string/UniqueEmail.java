package problems.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Unique Email Addresses
 * https://leetcode.com/problems/unique-email-addresses/
 */
public class UniqueEmail {
    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com", "test.email@leetcode.com"};
        System.out.println(numUniqueEmails(emails));
    }

    private static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String s: emails) {
            StringBuilder sb = new StringBuilder();
            int a = s.indexOf('@');
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '+') {
                    sb.append(s.substring(a));
                    break;
                } else if (c != '.' || i > a) {
                    sb.append(c);
                }
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
