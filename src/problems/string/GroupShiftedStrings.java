package problems.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 */
public class GroupShiftedStrings {
    public static void main(String[] args) {
        String[] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println(groupStrings(strings));
    }

    // we need some kind of key to group strings together
    // consider: acf, pru: if we calculate difference between chars: 0,2,3 which can be our key
    // for negative keys like ba: we can add 26 make it positive. az: 0,25 ba: 0,-1 = 0,25
    private static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String string: strings) {
            String key = findKey(string);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(string);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String key: map.keySet()) {
            ans.add(map.get(key));
        }

        return ans;
    }

    private static String findKey(String string) {
        char[] chars = string.toCharArray();
        StringBuilder key = new StringBuilder();
        for (int i = 1; i < chars.length; i++) {
            int diff = chars[i] - chars[i-1];
            key.append(diff < 0 ? diff + 26 : diff);
            key.append(",");
        }
        return key.toString();
    }
}
