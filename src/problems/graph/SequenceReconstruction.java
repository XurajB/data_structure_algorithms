package problems.graph;

import java.util.*;

/**
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
 * The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104.
 * Reconstruction means building a shortest common supersequence of the sequences in seqs
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 */
public class SequenceReconstruction {
    public static void main(String[] args) {
        List<List<Integer>> seqs = new ArrayList<>();
        seqs.add(Arrays.asList(5,2,6,3));
        seqs.add(Arrays.asList(4,1,5,2));
        System.out.println(sequenceReconstruction(new int[] {4,1,5,2,6,3}, seqs));
    }

    // The question is asking for 3 conditions to be met:
    // 1. Topo sort order exists
    // 2. whether the topo sort order is the only one (aka Hamilton path in Topological sort, see wikipedia)
    // 3. The only topo sort constructed should be equal to org.
    // O(n*m)
    private static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        for (List<Integer> seq: seqs) {
            for (int i = 0; i < seq.size(); i++) {
                graph.putIfAbsent(seq.get(i), new ArrayList<>());
                indegree.putIfAbsent(seq.get(i), 0);

                if (i > 0) {
                    graph.get(seq.get(i-1)).add(seq.get(i));
                    indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
                }
            }
        }

        // we need to be able to construct org from indegree
        if (org.length != indegree.size()) {
            return false;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int key: graph.keySet()) {
            if (indegree.get(key) == 0) {
                q.offer(key);
            }
        }

        // we want to make sure the org is only unique seq we can get
        int index = 0;
        while (!q.isEmpty()) {
            // this means more than one topo sort seq is possible, condition 2
            if (q.size() > 1) {
                return false;
            }
            int cur = q.poll();
            // org needs to the topo sort seq, condition 3
            if (org[index++] != cur) {
                return false;
            }
            for (int next: graph.get(cur)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    q.offer(next);
                }
            }
        }

        return index == org.length; // condition 1
    }
}
