package problems.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * A valid IP address consists of exactly four integers (each integer is between 0 and 255) separated by single points.
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIpAddresses {

    // O(1) coz there are only finite combination (<= 255 and 3 dots)
    private void helper(String s, int index, String cur, List<String> ans, int dotCount) {
        if (index == s.length() || dotCount == 3) {
            if (cur.length() == s.length() + 3) {
                ans.add(cur);
            }
            return;
        }
        long num = 0;
        for (int i = index; i < s.length(); i++) {
            num = num*10 + (s.charAt(i)-'0');
            if (num >= 0 && num <= 255) { // need to check both for overflow
                if (index == 0) {
                    helper(s, i+1, num + "", ans, 0);
                } else {
                    helper(s, i+1, cur+"."+num, ans, dotCount+1);
                }
            }
        }
    }

    ///////////////////////

    private int n;
    private String s;
    private LinkedList<String> segments;
    private ArrayList<String> ans;

    public List<String> restoreIpAddresses(String s) {

        this.n = s.length();
        this.s = s;
        segments = new LinkedList<>();
        ans = new ArrayList<>();

        backtrack(-1, 3);

        return ans;

    }

    private void backtrack(int prevPos, int dots) {
        int maxPos = Math.min(n-1, prevPos + 4);
        for (int curPos = prevPos + 1; curPos < maxPos; curPos++) {

            String segment = s.substring(prevPos + 1, curPos + 1);
            if (valid(segment)) {
                segments.add(segment);
                if (dots - 1 == 0) {
                    updateOutput(curPos);
                } else {
                    backtrack(curPos, dots - 1);
                }
                segments.removeLast();
            }
        }
    }

    private void updateOutput(int curPos) {
        String segment = s.substring(curPos + 1, n);
        if (valid(segment)) {
            segments.add(segment);
            ans.add(String.join(".", segments));
            segments.removeLast();
        }
    }

    private boolean valid(String segment) {
        // check if
        // less than or equal to 255
        // the first character cannot be 0 unless if segment is 1 char long
        int m = segment.length();
        if (m > 3) {
            return false;
        }
        if (segment.charAt(0) != '0') {
            return Integer.valueOf(segment) <= 255;
        } else {
            return m == 1;
        }
    }

    private boolean isValid(String s) {
        if (s.length() > 0) {
            int i = Integer.parseInt(s);
            return i >= 1 && i <= 255;
        }
        return false;
    }

    ///////////////////////////////////////////////

    private static List<String> restoreIpAddresses2(String s) {
        List<String> ans = new ArrayList<>();
        int len = s.length();

        for (int i = 1; i <= 3; i++) { //1st cut
            if (len - i > 9) {
                continue; // too long
            }
            for (int j = i + 1; j <= i + 3; j++) { // 2nd cut
                if (len - j > 6) {
                    continue; // too long
                }
                for (int k = j + 1; k <= k + 3 && k < len; k++) { // 3rd cut
                    int a, b, c, d;
                    a = Integer.parseInt(s.substring(0, i));
                    b = Integer.parseInt(s.substring(i, j));
                    c = Integer.parseInt(s.substring(j, k));
                    d = Integer.parseInt(s.substring(k));
                    if (a > 255 || b > 255 || c > 255 || d > 255) {
                        continue; // invalid
                    }
                    String ip = a + "." + b + "." + c + "." + d;
                    if (ip.length() < len + 3) {
                        continue;
                    }
                    ans.add(ip);
                }
            }
        }
        return ans;
    }

    ///////////////////////////
    // backtrack
    //
    public List<String> restoreIpAddresses3(String s) {
        List<String> ans = new ArrayList<>();

        if (s.length() < 4 || s.length() > 12) {
            return ans;
        }

        helper(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void helper(String s, int index, List<Integer> cur, List<String> ans) {
        if (cur.size() > 4) {
            return;
        }
        if (index == s.length()) {
            if (cur.size() == 4) {
                String gen = generateIp(cur);
                if (gen.length() == s.length() + 3) {
                    ans.add(generateIp(cur));
                }
            }
            return;
        }

        int part = 0;
        for (int i = index; i < s.length(); i++) {
            part = part * 10 + (s.charAt(i) - '0');

            if (part <= 255) {
                cur.add(part);
                helper(s, i+1, cur, ans);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private String generateIp(List<Integer> parts) {
        StringBuilder sb = new StringBuilder();
        sb.append(parts.get(0));
        for (int i = 1; i < parts.size(); i++) {
            sb.append(".").append(parts.get(i));
        }
        return sb.toString();
    }
}
