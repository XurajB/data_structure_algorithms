package problems.graph;

/**
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 *
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 *
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 * https://leetcode.com/problems/find-the-town-judge/
 */
public class TownJudge {
    public static void main(String[] args) {
        int[][] trust = {{1,3},{2,3},{3,1}};
        System.out.println(findJudge(3, trust));
    }

    // O(E) - number of trust relationship or edge
    // O(N)
    private static int findJudge(int n, int[][] trust) {
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];

        for (int[] i : trust) {
            int out = i[0];
            int in = i[1];
            outdegree[out]++;
            indegree[in]++;
        }

        for (int i = 1; i <= n; i++) {
            if (outdegree[i] == 0 && indegree[i] == n - 1) { // they don't trust anyone and trusted by everyone
                return i;
            }
        }

        return -1;
    }
}
