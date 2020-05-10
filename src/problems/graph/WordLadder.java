package problems.graph;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot", "dog", "dot", "lot", "cog", "log");
        System.out.println(ladderLength("hit", "cog", wordList));
    }

    // O(N*M) - N: no of words, M: length of word
    private static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 0;
        }
        int level = 1;
        Set<String> words = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        List<String> list = new ArrayList<>();

        // bfs
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            // go through this level (bfs)
            for (int i = 0; i < size; i++) {
                list.add(queue.peek()); // not needed for solution, just for study
                char[] chars = queue.poll().toCharArray();
                // go through each character in the word
                for (int j = 0; j < chars.length; j++) {
                    char temp = chars[j];
                    // go through a-z and replace character in each position
                    // generating neighbours
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String newWord = String.valueOf(chars);
                        // check if this new word is in provided list
                        if (words.contains(newWord)) {
                            // check if this new word equals the end word
                            if (newWord.equals(endWord)) {
                                return level;
                            }
                            queue.offer(newWord);
                            words.remove(newWord); // or keep a visited set
                        }
                    }
                    chars[j] = temp;
                }
            }
        }
        return 0;
    }
}
