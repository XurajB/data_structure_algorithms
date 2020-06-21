package problems.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * Given n and k, return the kth permutation sequence.
 */
public class PermutationSequence {
    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3));
    }

    private static String getPermutation(int n, int k) {
        count = k;
        if (n == 0) {
            return "";
        }
        int[] chars = new int[n];
        for (int i = 0; i < n; i++) {
            chars[i] = i+1;
        }
        permute(n, k, 0, chars);
        return ans;
    }
    static String ans = "";
    static int count = 0;
    private static void permute(int n, int k, int index, int[] nums) {
        if (index == n) {
            if (count-- == 1) {
                ans = Arrays.toString(nums);
                return;
            }
        }
        for (int i = index; i < n; i++) {
            swap(nums, index, i);
            permute(n, k, i+1, nums);
            swap(nums, index, i);
        }
    }

    private static void swap(int[] chars, int i, int j) {
        int temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // O(N)
    public String getPermutation2(int n, int k) {
        List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++) fact[i] = i*fact[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }
}
