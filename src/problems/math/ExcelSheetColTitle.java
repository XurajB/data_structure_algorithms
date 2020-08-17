package problems.math;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 */
public class ExcelSheetColTitle {
    public static void main(String[] args) {
        System.out.println(convertToTitle(28));
    }

    private static String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append((char) ((n - 1) % 26 + 'A'));
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }
}
