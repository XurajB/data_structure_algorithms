package problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class StringPermutation2 {
    public static void main(String[] args) {
        List<String> answer = permutation("hel");
        System.out.println(answer);

        List<String> a2 = new ArrayList<>();
        dfs(a2, "hello", "");
        //System.out.println(a2);

        System.out.println(findPerm("hel"));
    }

    // -------
    private static ArrayList<String> findPerm(String s) {
        ArrayList<String> ans = new ArrayList<>();
        dfs2(ans, new StringBuilder(), s, new boolean[s.length()]);
        return ans;
    }

    private static void dfs2(ArrayList<String> ans, StringBuilder current, String s, boolean[] visited) {
        if (current.length() == s.length()) {
            ans.add(current.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.append(s.charAt(i));
                dfs2(ans, current, s, visited);
                visited[i] = false;
                current.deleteCharAt(current.length() - 1);
            }
        }
    }
    // ---------

    public static ArrayList<String> permutation(String s) {
        // The result
        ArrayList<String> res = new ArrayList<>();
        // If input string's length is 1, return {s}
        if (s.length() == 1) {
            res.add(s);
        } else if (s.length() > 1) {
            int lastIndex = s.length() - 1;
            // Find out the last character
            String last = s.substring(lastIndex);
            // Rest of the string
            String rest = s.substring(0, lastIndex);
            // Perform permutation on the rest string and
            // merge with the last character
            res = merge(permutation(rest), last);
        }
        return res;
    }

    /**
     * @param list a result of permutation, e.g. {"ab", "ba"}
     * @param c    the last character
     * @return     a merged new list, e.g. {"cab", "acb" ... }
     */
    public static ArrayList<String> merge(ArrayList<String> list, String c) {
        ArrayList<String> res = new ArrayList<>();
        // Loop through all the string in the list
        for (String s : list) {
            // For each string, insert the last character to all possible positions
            // and add them to the new list
            for (int i = 0; i <= s.length(); ++i) {
                String ps = new StringBuffer(s).insert(i, c).toString();
                res.add(ps);
            }
        }
        return res;
    }

    private static void dfs(List<String> res, String str, String ans) {
        if (str.length() == 0) {
            res.add(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String rest = str.substring(0, i) + str.substring(i + 1);
            dfs(res, rest, ans + ch);
        }
    }
}
