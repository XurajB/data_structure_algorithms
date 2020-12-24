package problems.array;

/**
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted.
 * More specifically, if x occurs before y in S, then x should occur before y in the returned string.
 * Return any permutation of T (as a string) that satisfies this property.
 */
public class CustomSortString {

    public static void main(String[] args) {
        System.out.println(customSortString("cba", "abcd"));
    }

    // O(S+T), O(T)
    private static String customSortString(String S, String T) {
        int[] count = new int[26];
        for (char c: T.toCharArray()) {
            count[c-'a']++;
        }

        StringBuilder ans = new StringBuilder();
        for (char c: S.toCharArray()) {
            while (count[c-'a']-- > 0) {
                ans.append(c);
            }
        }

        // remaining chars
        for (char c: T.toCharArray()) {
            while (count[c-'a']-- > 0) {
                ans.append(c);
            }
        }

        return ans.toString();
    }
}
