package problems.string;

/**
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict.
 * If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 */
public class AddBoldTagInString {
    public static void main(String[] args) {
        System.out.println(addBoldTag("abcxyz123", new String[] {"abc", "123"}));
    }

    // O(n * d * l), n = s length, d= dict length, l = max word
    private static String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()]; // mark true for all letters needing bold
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            for (String word: dict) {
                if (s.startsWith(word, i)) {
                    // could be multiple matches with longer words
                    end = Math.max(end, word.length() + i);
                }
            }
            if (i < end) {
                bold[i] = true; //everything until end needs to be bold
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                sb.append(s.charAt(i));
            } else {
                int j = i;
                while (j < s.length() && bold[j]) {
                    j++;
                }
                sb.append("<b>")
                        .append(s, i, j)
                        .append("</b>");
                i = j - 1;
            }
        }

        return sb.toString();
    }
}
