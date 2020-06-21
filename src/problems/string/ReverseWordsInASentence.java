package problems.string;

/**
 * Reverse words in a sentence
 * what is your name = name your is what
 */
public class ReverseWordsInASentence {
    public static void main(String[] args) {
        System.out.println(reverseSentence("My name is code"));
    }

    // O(N)
    private static String reverseSentence(String sentence) {
        char[] sentenceChars = sentence.toCharArray();
        // reverse whole sentence
        reverse(sentenceChars, 0, sentence.length() - 1);
        // reverse each word
        int start = 0;
        for (int i = 0; i < sentenceChars.length - 1; i++) {
            char c = sentenceChars[i];
            if (sentenceChars[i + 1] == ' ') {
                reverse(sentenceChars, start, i);
                start = i + 2;
            }
        }
        // last part
        reverse(sentenceChars, start, sentenceChars.length - 1);
        return new String(sentenceChars);
    }

    private static void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++; j--;
        }
    }
}
