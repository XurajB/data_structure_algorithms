package problems.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * https://leetcode.com/problems/text-justification/
 */
public class TestJustification {
    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};

        TestJustification testJustification = new TestJustification();
        System.out.println(testJustification.fullJustify(words, 16));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        // start with left being first word
        // findRight - greedily try to go as far as possible until we fill our line
        // justify one line at a time
        //   - If it is one word, just return that word
        //   - If it is the last line, then just seperate each word with one space (left justified)
        //   - Else - calculate the size of each space evenly and if there is a reminder, distribute until it is gone
        int left = 0; // index
        List<String> lines = new ArrayList<>();

        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            lines.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return lines;
    }

    private int findRight(int left, String[] words, int maxWidth) {
        int right = left; // start with left
        int sum = words[right++].length();

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
            sum += 1 + words[right++].length(); // 1 for space
        }

        return right - 1;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        if (right - left == 0) {
            // one word only
            return padResult(words[left], maxWidth);
        }

        boolean isLastLine = right == words.length - 1;
        int totalSpaces = maxWidth - wordsLength(left, right, words);
        int numOfSpaces = right - left;

        String space = isLastLine ? " " : blank(totalSpaces / numOfSpaces);
        int reminder = isLastLine ? 0 : totalSpaces % numOfSpaces;

        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(words[i])
                    .append(space)
                    .append(reminder-- > 0 ? " " : "");
        }

        if (isLastLine) {
            return padResult(sb.toString().trim(), maxWidth);
        }
        return sb.toString().trim();
    }

    private int wordsLength(int left, int right, String[] words) {
        int length = 0;
        for (int i = left; i <= right; i++) {
            length += words[i].length();
        }
        return length;
    }

    // pad result with spaces
    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    private String blank(int length) {
        String blank = new String(new char[length]);
        return blank.replace('\0', ' ');
    }
}
