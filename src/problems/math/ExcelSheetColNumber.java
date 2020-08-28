package problems.math;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 */
public class ExcelSheetColNumber {
    public static void main(String[] args) {
        System.out.println(titleToNumber("AB"));
    }

    // O(N)
    // think of 26 base number system
    private static int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans = ans * 26;
            ans = ans + (s.charAt(i) - 'A' + 1);
        }
        return ans;
    }
}
