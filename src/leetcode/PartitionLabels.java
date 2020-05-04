package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase letters is given.
 * We want to partition this string into as many parts as possible so that each letter appears in at most one part,
 * and return a list of integers representing the size of these parts.
 * https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));
    }

    // O(N)
    private static List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        int[] location = new int[26];

        for (int i = 0; i < s.length(); i++) {
            location[s.charAt(i) - 'a'] = i;
        }

        int lastIndex = 0;
        int anchor = 0;
        for (int i = 0; i < s.length(); i++) {
            lastIndex = Math.max(lastIndex, location[s.charAt(i) - 'a']);
            if (i == lastIndex) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
}
