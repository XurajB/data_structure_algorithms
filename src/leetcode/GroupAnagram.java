package leetcode;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 * https://leetcode.com/problems/group-anagrams/
 */
public class GroupAnagram {
    public static void main(String[] args) {
        String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> ans = groupAnagrams(strs);
        for (List<String> list: ans) {
            System.out.println(list);
        }
    }

    // O(KN) - k max number of char, N number of items in strs, O(KN) space
    // we can also sort each string and check if it is in the map. but that would increase complexity by: nLogn: total would be KNLogN
    private static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] chars = new char[26];
            for (char c: str.toCharArray()) { //we can also sort characters instead of char array
                chars[c-'a']++;
            }
            String s = String.valueOf(chars);
            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
