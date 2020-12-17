package problems.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.
 * Also, if your guess is not in the given wordlist, it will return -1 instead.
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at
 * least one of these guesses was the secret, you pass the testcase.
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.
 * The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
 */
public class GuessTheWord {
    // nLogn + 10*n = nlogn
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> dict = new ArrayList<>(Arrays.asList(wordlist));
        Collections.sort(dict);
        // i < wordlist.length or i < 10 both fine
        for (int i = 0; i < wordlist.length; i++) {

            String word = dict.get(0);
            int match = master.guess(word);
            // we found the match
            if (match == word.length()) {
                return;
            }
            // that word is not our answer, no need to keep it
            dict.remove(0);
            int j = 0;
            // remove all items that have words character match than current word
            // we know that correct answer will meet the match all the time
            while (j < dict.size()) {
                if ((compare(dict.get(j), word) != match)) {
                    dict.remove(j); // converse
                    // we removed a word, so we keep j the same
                } else {
                    j++;
                }
            }
        }

    }
    private int compare(String s1, String s2) {
        int ans = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                ans++;
            }
        }
        return ans;
    }

    interface Master {
        int guess(String word);
    }
}
