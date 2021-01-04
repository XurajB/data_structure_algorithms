package problems.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal to B, otherwise, return false.
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and
 * swapping the characters at A[i] and A[j]. For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 */
public class BuddyString {
    public static void main(String[] args) {
        System.out.println(buddyString("abcd", "bacd"));
    }

    private static boolean buddyString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.equals(B)) {
            // we need a duplicate char so we can swap
            // case like aa, aa vs ab, ab
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < A.length(); i++) {
                set.add(A.charAt(i));
            }
            return set.size() < B.length();
        }
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                diff.add(i);
            }
        }
        // only one swap
        if (diff.size() != 2) {
            return false;
        }
        // cd, ef
        return A.charAt(diff.get(0)) == B.charAt(diff.get(1)) &&
                A.charAt(diff.get(1)) == B.charAt(diff.get(0));
    }
}
