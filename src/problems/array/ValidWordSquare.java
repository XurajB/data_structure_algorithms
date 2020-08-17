package problems.array;

import java.util.Arrays;
import java.util.List;

/**
 * Given a sequence of words, check whether it forms a valid word square.
 * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
 */
public class ValidWordSquare {
    public static void main(String[] args) {
        // if this is valid, the matrix is diagonally symmetrical
        // dty on the bottom = dty on the right, cr=cr, b=b
        String[] words = {
                "abcd",
                "bnrt",
                "crmy",
                "dtye"
        };
        System.out.println(validWordSquare(Arrays.asList(words)));
    }

    // word square have property of being symmetrical matrix
    // it becomes checking symmetric matrix problem
    // so just make sure if char at row is equal to char at col
    private static boolean validWordSquare(List<String> words) {
        if (words == null || words.size() == 0) {
            return true;
        }

        int n = words.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.get(i).length(); j++) {

                if (j >= n || words.get(j).length() <= i) {
                    return false;
                }

                if (words.get(j).charAt(i) != words.get(i).charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
