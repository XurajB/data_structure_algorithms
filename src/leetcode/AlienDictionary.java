package leetcode;

import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = {"za","zb","ca","cb"};
        System.out.println(alienOrder(words));
    }

    private static String alienOrder(String[] words) {
        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();
        buildGraph(graph, indegree, words);
        return bfs(graph, indegree);
    }

    // O(V+E) or O(k + n), space: O(V+E)
    private static void buildGraph(Map<Character, Set<Character>> graph, int[] indegree, String[] words) {

        // creating nodes
        for (String word: words) {
            for (Character c: word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        // creating edges
        for (int i = 1; i < words.length; i++) {
            String first = words[i-1];
            String second = words[i];
            int length = Math.min(first.length(), second.length());

            for (int j = 0; j < length; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                if (out != in) {
                    // build edge A -> B
                    if (!graph.get(out).contains(in)) {
                        graph.get(out).add(in);
                        indegree[in - 'a']++;
                    }
                    break;
                }
            }
        }
    }

    private static String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();

        // fill q without dependencies (indegree == 0) as starting point
        for (char c: graph.keySet()) {
            if (indegree[c - 'a'] == 0) {
                q.offer(c);
            }
        }

        while (!q.isEmpty()) {
            char out = q.poll();
            sb.append(out);
            for (char in: graph.get(out)) {
                // this is visited so reduce number of ingrees to this node
                indegree[in - 'a']--;
                // if no dependency then we can traverse (topological sort)
                if (indegree[in - 'a'] == 0) {
                    q.offer(in);
                }
            }
        }

        return sb.length() == graph.size() ? sb.toString() : "";
    }
}
