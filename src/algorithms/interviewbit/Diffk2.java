package algorithms.interviewbit;

import java.util.HashSet;

/**
 * Given an array A of integers and another non negative integer k, find if there exists 2 indices i and j such that A[i] - A[j] = k, i != j.
 * https://www.interviewbit.com/problems/diffk-ii/
 */
public class Diffk2 {
    public static void main(String[] args) {
        int[] a = {1,5,3};
        System.out.println(diffPossible(a, 2));
    }

    private static int diffPossible(int[] a, int b) {
        HashSet<Integer> set = new HashSet<>();

        for (int value : a) {
            // a[i] - a[j] = k can be resolved two ways
            // a[i] = k + a[j];
            // a[j] = a[i] - k;
            if (set.contains(value + b) || set.contains(value - b)) {
                return 1;
            } else {
                set.add(value);
            }
        }
        return 0;
    }
}
