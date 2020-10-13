package problems.string;

/**
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX",
 * or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end,
 * return True if and only if there exists a sequence of moves to transform one string to the other.
 */
public class SwapAdjacentInLRString {
    public static void main(String[] args) {
        System.out.println(canTransform("RXXLRXRXL","XRLXXRRLX"));
    }

    private static boolean canTransform(String start, String end) {
        // we are not returning true coz we are going from start to end. not the other way
        // which would be true in that case. like LX, XL
        if (!start.replace("X", "").equals(end.replace("X", ""))) {
            return false;
        }

        int p1 = 0;
        int p2 = 0;

        while (p1 < start.length() && p2 < start.length()) {

            // ignore x on both strings
            while (p1 < start.length() && start.charAt(p1) == 'X') {
                p1++;
            }
            while (p2 < end.length() && end.charAt(p2) == 'X') {
                p2++;
            }

            // if both reach end, return true
            if (p1 == start.length() && p2 == end.length()) {
                return true;
            }
            // false otherwise
            if (p1 == start.length() || p2 == end.length()) {
                return false;
            }
            // now chars should be equal at this point
            // RX converts to XR, XL to LX
            // so ignoring X should lead to same char
            if (start.charAt(p1) != end.charAt(p2)) {
                return false;
            }

            // for cases like: "LXXLXRLXXL" to "XLLXRXLXLX"
            // LX can't go to XL, but above cases will be true
            // when we find L, it can only be moved to left. the correct position is LX
            // so p1 should be greater than p2
            if (start.charAt(p1) == 'L' && p2 > p1) {
                return false;
            }
            if (start.charAt(p1) == 'R' && p1 > p2) {
                return false;
            }
            p1++;
            p2++;

        }
        return true;
    }
}
