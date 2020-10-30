package problems.backtracking;

public class Readme {
    /*
      All recursive functions should have following:
      - Base case
      - Working towards a base case
      - Recursive step
     */

    // for all recursions (subsets, permutations, combinations)
    // allow same element: dfs(i)
    // no same element: dfs(i+1), no dupe: sort, check if repeated if (i > index && nums[i]!=nums[i-1]) -> dfs(i+1)
    // permutation: loop: 0 - n, no index

    /*
    Complexities:
    Permutations: N!
    Combinations: C(k, N) = N!/(N-k)!k!
    Subsets: 2^N (since each element could be absent or present)
     */
}
