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
    Map<String, Integer> distance; // distance from begin word to this word
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

        dict.add(beginWord);
        for (String word: dict) {
            graph.put(word, new ArrayList<>());
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean found = false;
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                int curDistance = distance.get(current);
                List<String> neighbours = getNeighbours(current);

                for (String neighbour: neighbours) {
                    graph.get(current).add(neighbour);
                    if (!distance.containsKey(neighbour)) {
                        distance.put(neighbour, curDistance + 1);
                        if (neighbour.equals(endWord)) {
                            found = true;
                        } else {
                            queue.offer(neighbour);
                        }
                    }
                }
                if (found) {
                    break;
                }
            }
        }
    }

    // try all possible substitutions in every position of current word
    private List<String> getNeighbours(String curr) {
        List<String> neighbours = new ArrayList<>();
        char[] chars = curr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                char temp = chars[i];
                chars[i] = c;
                String newWord = new String(chars);
                if (dict.contains(newWord)) {
                    neighbours.add(newWord);
                }
                chars[i] = temp; // change back to original so we can proceed with other combinations
            }
        }
        return neighbours;
    }

    // step 2 - dfs, backtracking
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
