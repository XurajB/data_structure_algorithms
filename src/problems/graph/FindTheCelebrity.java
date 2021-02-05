package problems.graph;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1), and among them, there may exist one celebrity.
 * The definition of a celebrity is that all the other n - 1 people know him/her, but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B.
 * You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) which tells you whether A knows B.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party.
 * Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
public class FindTheCelebrity {

    // O(N^2)
    public int findCelebrity(int n) {
        int[] indegree = new int[n];
        int[] outdegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (knows(i, j)) {
                    indegree[j]++;
                    if (i != j) {
                        outdegree[i]++;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (indegree[i] == n) {
                if (outdegree[i] == 0) {
                    return i;
                }
                return -1;
            }
        }

        return -1;
    }

    /////////////////////////////////
    ///////// deduction
    //O(N)
    // a celebrity is the person, who has exactly n-1 edges going in and 0 edges going out
    // we only know nodes, edges are hidden and we can uncover this by calling the api
    // if we know 5 knows 1. if we ask if 5 knows 6, and if the answer is no, the both of them cannot be celebrity
    // 6 is not known so it cannot be celebrity. both of them at least know one other person.
    // so just check if 1 knows 2 if yes, then check if 2 knows remaining and continue. whoever does not know will be the celebrity
    private int findCelebrity2(int n) {
        int candidate = 0;
        for (int i = 0; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        // check if candidate is the celebrity
        // also make sure everyone knows candidate
        for (int i = 0; i < n; i++) {
            if (i == candidate) {
                continue;
            }
            if (knows(candidate, i) || !knows(i, candidate)) {
                return -1;
            }
        }
        return candidate;
    }

    ////////////
    // interface
    boolean knows(int a, int b) {
        return true;
    }
}
