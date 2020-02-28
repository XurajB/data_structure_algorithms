package leetcode;

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
        List<String> lines = new ArrayList<>();

        if (words == null || words.length == 0) {
            return lines;
        }

        int startIndex = 0;
        int spaceCount = 0;
        int currentCount = 0;

        int i = 0;
        for (i = 0; i < words.length - 1; i++) {
            int size = currentCount + words[i].length() + spaceCount;
            if (size < maxWidth) {
                currentCount = currentCount + words[i].length();
                spaceCount++;
            } else {
                lines.add(getSentence(words, startIndex, i-1, maxWidth, currentCount));
                startIndex = i;
                currentCount = 0;
                spaceCount = 0;
            }
        }


        if (i < words.length) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < words.length - 1; j++) {
                sb.append(words[j]);
                sb.append(" ");
            }
            sb.append(words[words.length - 1]);

            int lastCount = sb.length();
            if (lastCount < maxWidth) {
                addSpace(sb, maxWidth - lastCount);
            }

            lines.add(sb.toString());
        }

        return lines;
    }

    private String getSentence(String[] words, int start, int end, int maxWidth, int count) {
        int totalSpaceCount = maxWidth - count;
        int perWordSpace = totalSpaceCount / (end - start);

        StringBuilder sb = new StringBuilder();

        for (int i = start; i < end; i++) {
            String s = words[i];
            sb.append(s);
            addSpace(sb, perWordSpace);
            //totalSpaceCount = totalSpaceCount - perWordSpace;

        }
        sb.append(words[end]);

        return sb.toString();
    }

    private void addSpace(StringBuilder sb, int count) {
        for (int j = 0; j < count; j++) {
            sb.append(" ");
        }
    }
}
