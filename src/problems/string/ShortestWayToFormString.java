package problems.string;

/**
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.
 */
public class ShortestWayToFormString {
    public static void main(String[] args) {
        System.out.println(shortestWay("xyz", "xzyxz"));
    }

    // MN
    private static int shortestWay(String source, String target) {
        int i = 0;
        int count = 0;
        while (i < target.length()) {
            int j = 0;
            int k = i;
            while (k < target.length() && j < source.length()) {
                if (target.charAt(k) == source.charAt(j)) {
                    k++;
                }
                j++;
            }
            if (k == i) {
                return -1;
            }
            i = k;
            count++;
        }
        return count;
    }
}
