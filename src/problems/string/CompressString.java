package problems.string;

/**
 * Implement a method to perform basic string compression using the count of repeated characters
 * Ex: aabcccccaaa = a2b1c5a3
 */

public class CompressString {
    public static void main(String[] args) {
        countChars("aabcccccaaa");
    }

    // if we are allowed to use additional data structure
    // like StringBuilder or StringBuffer
    // O(N)
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
}
