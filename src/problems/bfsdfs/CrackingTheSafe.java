package problems.bfsdfs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class CrackingTheSafe {
    public static void main(String[] args) {
        System.out.println(crackSafe(3, 2));
    }

    // in order to guarantee to open the box, the input password needs to contain all length-n combinations on [0..k-1], total combo: k^n
    // to make the password short as possible, we would make each length-n combo on digits[0..k-1] occur exactly once as a substring of the password
    // the solution is also called: De Bruijn Sequence
    private static String crackSafe(int n, int k) {
        // init password to n repeated 0 as the start node
        String strPwd = String.join("", Collections.nCopies(n, "0"));
        StringBuilder sbPwd = new StringBuilder(strPwd);

        Set<String> visited = new HashSet<>();
        visited.add(strPwd);

        int target = (int) Math.pow(k, n);
        dfs(sbPwd, visited, target, n, k);
        return sbPwd.toString();
    }

    private static boolean dfs(StringBuilder pwd, Set<String> visited, int target, int n, int k) {
        // base case, all n-length combos among digits [0..k-1] are visited
        if (visited.size() == target) {
            return true;
        }

        // to make it shortest string, we reuse the last n part
        String lastDigits = pwd.substring(pwd.length() - n + 1); // last n-1 digits of pwd
        for (char c = '0'; c < ('0' + k); c++) {
            String newComb = lastDigits + c;
            if (!visited.contains(newComb)) {
                visited.add(newComb);
                pwd.append(c);
                if (dfs(pwd, visited, target, n, k)) {
                    return true;
                }
                visited.remove(newComb);
                pwd.deleteCharAt(pwd.length() - 1);
            }
        }

        return false;
    }
}
