package problems.graph;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * https://leetcode.com/problems/word-ladder-ii/
 */
public class WordLadder2 {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        WordLadder2 wordLadder2 = new WordLadder2();

        List<List<String>> answer = wordLadder2.findLadders("hit", "cog", wordList);
        for (List<String> a: answer) {
            System.out.println(a);
        }
    }

    // global vars so we don't have to pass around methods
    // for readability
    Map<String, List<String>> graph;
    Set<String> dict;
    Map<String, Integer> distance;
    List<List<String>> answer;

    // O(V * E), O(V), V = size of dict, E = avg length of word
    // use bfs to find shortest distance from start to end (build graph)
    // use dfs to find every path with the same distance
    private List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        answer = new ArrayList<>();
        if (wordList == null || wordList.size() == 0) {
            return answer;
        }

        graph = new HashMap<>();
        dict = new HashSet<>(wordList); // for fast lookup

        distance = new HashMap<>();
        distance.put(beginWord, 0); // initialize with 0 distance
        // bfs
        buildGraph(beginWord, endWord); //create a graph with shortest path

        // dfs
        List<String> path = new ArrayList<>();
        path.add(beginWord); // initialize with begin word
        dfs(beginWord, endWord, path);

        return answer;
    }

    private void buildGraph(String beginWord, String endWord) {
        Queue<String> queue = new LinkedList<>(); // for bfs
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            char[] chars = curr.toCharArray();
            // try all possible substitutions in every position of current word
            for (int i = 0; i < chars.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (chars[i] == c) continue;
                    char temp = chars[i];
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (dict.contains(newWord)) {
                        graph.putIfAbsent(curr, new ArrayList<>());
                        graph.get(curr).add(newWord);
                    }
                    chars[i] = temp; // change back to original so we can proceed with other combinations
                }
            }

            // traverse through all neighbour of current node and update distance and queue for next level
            if (graph.containsKey(curr)) {
                for (String neighbor: graph.get(curr)) {
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, distance.get(curr) + 1);
                        if (!neighbor.equals(endWord)) {
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }
    }

    // step 2 - dfs
    private void dfs(String currentWord, String endWord, List<String> path) {
        if (currentWord.equals(endWord)) {
            answer.add(new ArrayList<>(path));
            return;
        }

        if (graph.containsKey(currentWord)) {
            for (String nextWord: graph.get(currentWord)) {
                if (distance.get(nextWord) == distance.get(currentWord) + 1) {
                    path.add(nextWord);
                    dfs(nextWord, endWord, path);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
