package algorithms.interviewbit;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the intersection of two sorted arrays.
 * OR in other words,
 * Given 2 sorted arrays, find all the elements which occur in both the arrays.
 * https://www.interviewbit.com/problems/intersection-of-sorted-arrays/
 */
public class IntersectionOfSortedArrays {
    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        A.add(10000000);
        List<Integer> B = new ArrayList<>();
        B.add(10000000);

        System.out.println(intersect(A, B));
    }

    // O(N)
    private static ArrayList<Integer> intersect(final List<Integer> A, final List<Integer> B) {

        int i = 0;
        int j = 0;

        ArrayList<Integer> answer = new ArrayList<>();

        while (i < A.size() && j < B.size()) {

            if (A.get(i).equals(B.get(j))) {
                answer.add(A.get(i));
                i++;
                j++;
            } else if (A.get(i) > B.get(j)) {
                j++;
            } else {
                i++;
            }

        }

        return answer;

    }
}
