package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ZigZag Conversion
 * https://leetcode.com/problems/zigzag-conversion/
 */
public class ZigzagConversion {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    private static String convert(String s, int numRows) {
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        boolean goingDown = false;
        int currentRow = 0;
        for (char c: s.toCharArray()) {
            rows.get(currentRow).append(c);
            // we go down when we reach the top row and go up when we reach bottom row
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currentRow += (goingDown) ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb: rows) {
            result.append(sb);
        }

        return result.toString();
    }
}
