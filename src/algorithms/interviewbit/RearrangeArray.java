package algorithms.interviewbit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.
 * https://www.interviewbit.com/problems/rearrange-array/
 */
public class RearrangeArray {
    // extra space would be easy but with O(1) space we will need to encode the numbers
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(4, 0, 2, 1, 3));
        System.out.println(arrange(A));
    }

    // encoding: a[i] + (a[a[i]] % n) * n
    // decoding: a[i] / n
    private static ArrayList<Integer> arrange(ArrayList<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.get(i) + (a.get(a.get(i)) % a.size()) * a.size());
        }
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.get(i)/a.size());
        }

        return a;
    }
}
