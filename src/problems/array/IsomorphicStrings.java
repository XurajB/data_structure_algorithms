package problems.array;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
    }

    private static boolean isIsomorphic(String s, String t) {
        int[] schar = new int[256];
        int[] tchar = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char s1 = s.charAt(i);
            char t1 = t.charAt(i);

            if (schar[s1] == 0 && tchar[t1] == 0) {
                schar[s1] = t1;
                tchar[t1] = s1;
            } else {
                if (schar[s1] != t1 || tchar[t1] != s1) {
                    return false;
                }
            }
        }

        return true;
    }
}
