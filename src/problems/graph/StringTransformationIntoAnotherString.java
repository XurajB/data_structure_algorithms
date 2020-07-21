package problems.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 * Return true if and only if you can transform str1 into str2.
 */
public class StringTransformationIntoAnotherString {
    public static void main(String[] args) {
        System.out.println(canConvert("aabcc", "ccdee"));
    }

    private static boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        // str1 = "aabcc", str2 = "ccdee"
        // we are just trying to see if the conversion is possible. If we convert a to c first then we have two c in str1 which leads to confusion when it comes to convert to e
        // we can do this if we start from the back, convert c -> e, b -> d, a -> c
        // str1 = "leetcode", str2 = "codeleet" is not possible because if we convert e to o, now that o also maps to d, which is not valid and we can return false right away
        // one char should not map to multiple chars
        // same char should transform to the same char
        //
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < str1.length(); i++) {
            set.add(str2.charAt(i));
            if (map.containsKey(str1.charAt(i))) {
                if (map.get(str1.charAt(i)) != str2.charAt(i)) {
                    return false;
                }
            }
            map.put(str1.charAt(i), str2.charAt(i));
        }

        // if all chars are used from a-z, remaining chars cannot be mapped because a-z are already mapped and remaining will have to be mapped with something else
        return set.size() != 26;
    }
}
