package problems.graph.unionfind;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of unique positive integers A, consider the following graph:
 *
 * There are A.length nodes, labelled A[0] to A[A.length - 1];
 * There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 */
public class LargestComponentSizeByCommonFactor {
    int max = 1;

    // union find
    // hashmap is used to convert factor to node index in A for union
    // O(N * sqrroot(max A[i]))
    public int largestComponentSize(int[] A) {
        int n = A.length;
        int[] parents = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            size[i] = 1;
        }
        Map<Integer, Integer> map = new HashMap<>(); // factor, index
        for (int i = 0; i < n; i++) {
            int a = A[i];
            for (int j = 2; j * j <= a; j++) {
                if (a % j == 0) {
                    // like creating graph both ways
                    if (!map.containsKey(j)) {
                        map.put(j, i);
                    } else {
                        union(i, map.get(j), parents, size);
                    }
                    if (!map.containsKey(a/j)) {
                        map.put(a/j, i);
                    } else {
                        union(i, map.get(a/j), parents, size);
                    }
                }
            }
            // a could be a factor too
            if (!map.containsKey(a)) {
                map.put(a, i);
            } else {
                union(i, map.get(a), parents, size);
            }
        }
        return max;
    }

    private int find(int x, int[] parents) {
        if (x == parents[x]) {
            return x;
        }
        parents[x] = find(parents[x], parents);
        return parents[x];
    }

    private void union(int x, int y, int[] parents, int[] size) {
        int px = find(x, parents);
        int py = find(y, parents);

        if (px != py) {
            parents[px] = py;
            size[py] += size[px];
            max = Math.max(max, size[py]);
        }
    }
}
