package problems.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S,
 * then we will replace that occurrence of x with y.  If not, we do nothing.
 *
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position
 * 2 in the original string S, we will replace it with "ffff".
 *
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement
 * operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
 *
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc",
 * indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 */
public class FindAndReplaceInString {
    public static void main(String[] args) {
        String S = "vmokgggqzp";
        int[] indexes = {3,5,1};
        String[] sources = {"kg", "ggq", "mo"};
        String[] targets = {"s","so", "bfr"};
        System.out.println(findReplaceString(S, indexes, sources, targets));
    }

    // O(N * K), k = avg source length
    private static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();

        List<Node> input = new ArrayList<>();
        for (int k = 0; k < indexes.length; k++) {
            input.add(new Node(indexes[k], sources[k], targets[k]));
        }

        input.sort((a, b) -> a.index - b.index);
        int last = 0;
        for (Node node : input) {
            int index = node.index;
            String source = node.source;
            String target = node.target;

            // check if s starts with source at index
            if (S.substring(index).indexOf(source) == 0) {
                // copy every thing from last spot until this-1
                sb.append(S, last, index); // same as s.substring(last, index)
                sb.append(target);
                last = index + source.length();
            }
        }

        // check if we reached end
        if (last != S.length()) {
            sb.append(S.substring(last));
        }

        return sb.toString();
    }

    static class Node {
        int index;
        String source;
        String target;
        Node(int index, String source, String target) {
            this.index = index;
            this.source = source;
            this.target = target;
        }
    }

    ///////////////////
    /// O(N)
    public String findReplaceString2(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Node2> map = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            map.put(indexes[i], new Node2(sources[i], targets[i]));
        }
        int i = 0;
        while (i < S.length()) {
            if (map.containsKey(i)) {
                int k = 0;
                String source = map.get(i).source;
                String target = map.get(i).target;
                while (k < source.length() && S.charAt(i+k) == source.charAt(k)) {
                    k++;
                }
                if (k == source.length()) {
                    sb.append(target);
                    i += source.length();
                } else {
                    sb.append(S.charAt(i));
                    i++;
                }
            } else {
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
    static class Node2 {
        String source;
        String target;
        Node2(String source, String target) {
            this.source = source;
            this.target = target;
        }
    }
}
