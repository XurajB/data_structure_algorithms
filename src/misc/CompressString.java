package misc;

import java.util.Arrays;

/**
 * Implement a method to perform basic string compression using the count of repeated characters
 * Ex: aabcccccaaa = a2b1c5a3
 *
 */

public class CompressString {
    public static void main(String[] args) {
        countChars("aabcccccaaa");
    }

    // if we are allowed to use additional data structure
    // like StringBuilder or StringBuffer
    private static void countChars(String str) {
        char char1 = str.charAt(0);
        StringBuilder answer = new StringBuilder();
        int count = 1;
        for (int a = 1; a < str.length(); a++) {
            if (char1 == str.charAt(a)) {
                count++;
            } else {
                answer.append(char1).append(count);
                char1 = str.charAt(a);
                count = 1;
            }
        }

        answer.append(char1).append(count);
        System.out.println(answer.toString());
    }

    // if we are not allowed to use data structure
    private static void countChars2(String str) {
        int finalCount = finalCount(str);
        char[] answer = new char[finalCount];
        int index = 0;
        if (finalCount >= str.length()) {
            System.out.println("no change");
        } else {
            char char1 = str.charAt(0);
            int count = 1;
            for (int a = 1; a < str.length(); a++) {
                if (char1 == str.charAt(a)) {
                    count++;
                } else {
                    updateArray(char1, count, index, answer);
                    char1 = str.charAt(a);

                    index = index + 1; // for character
                    if (count > 9) { //only works if count is less than 99
                        index = index + 2;
                    }

                    count = 1;

                }
                updateArray(char1, count, index, answer);
            }
        }
        System.out.println(Arrays.toString(answer));
    }

    private static void updateArray(char char1, int count, int index, char[] answer) {

    }

    private static int finalCount(String str) {
        int count = 1;
        char char1 = str.charAt(0);
        int finalCount = 0;

        for (int a = 1; a < str.length(); a++) {
            if (char1 == str.charAt(a)) {
                count++;
            } else {
                finalCount = finalCount + 1 + count;
                count = 1;
            }
        }

        return finalCount + 1 + count;
    }
}
