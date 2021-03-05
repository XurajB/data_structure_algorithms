package problems.string;

/**
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.
 */
public class ShortestWayToFormAString {
    public static void main(String[] args) {
        System.out.println(shortestWay("xyz", "xzyxz"));
    }

    // O(s*t)
    private static int shortestWay(String source, String target) {
        int count = 0;
        int i = 0;
        while (i < target.length()) {
            int temp = i;
            for (int j = 0; j < source.length(); j++) {
                if (i < target.length() && source.charAt(j) == target.charAt(i)) {
                    i++;
                }
            }
            count++;
            if (i == temp) { // no char match
                return -1;
            }
        }
        return count;
    }
}
