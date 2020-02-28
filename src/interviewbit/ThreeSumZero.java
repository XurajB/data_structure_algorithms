package interviewbit;

import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * https://www.interviewbit.com/problems/3-sum-zero/
 */
public class ThreeSumZero {
    public static void main(String[] args) {
        System.out.println(threeSum(Arrays.asList(-1, 0, 1, 2, -1, -4)));
    }

    private static ArrayList<ArrayList<Integer>> threeSum(List<Integer> A) {
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        for (int x = 0; x < A.size(); x++) {
            int s1 = A.get(x);
            int i = x + 1;
            int j = A.size() - 1;
            while (i < j) {
                int s2 = A.get(i);
                int s3 = A.get(j);
                int sum = s1 + s2 + s3;
                if (sum == 0) {
                    ArrayList<Integer> a = new ArrayList<>();
                    a.add(s1);
                    a.add(s2);
                    a.add(s3);

                    if (!answer.contains(a)) {
                        answer.add(a);
                    }
                    i++;
                    j--;
                } else if (sum <= 0) {
                    i++;
                } else {
                    j--;
                }

            }
        }
        return answer;

    }
}
