package problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * Input: 5
 * Output
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        List<List<Integer>> ans = generate(5);
        System.out.println(ans);
    }

    // O(r * r)
    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) {
            return ans;
        }
        // initialize first one
        List<Integer> first = new ArrayList<>();
        first.add(1);
        ans.add(first);
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            // first row element
            row.add(1);
            for (int j = 1; j < i; j++) {
                int next = ans.get(i-1).get(j-1) + ans.get(i-1).get(j); // dp
                row.add(next);
            }
            // last row element
            row.add(1);
            ans.add(row);
        }
        return ans;
    }

    // just return the row
    public List<Integer> getRow(int rowIndex) {
        List<Integer> last = Arrays.asList(1,1);

        if (rowIndex == 0) {
            return Arrays.asList(1);
        } else if (rowIndex == 1) {
            return last;
        }

        List<Integer> current = new ArrayList<>();
        for (int i = 2; i <= rowIndex; i++) {
            current = new ArrayList<>();
            current.add(1);
            for (int j = 1; j < i; j++) {
                current.add(last.get(j-1) + last.get(j));
            }
            current.add(1);
            last = current;
        }

        return current;
    }
}
