package problems.math;

import java.util.ArrayList;
import java.util.List;

/**
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively.
 * When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)
 * Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
 */
public class ConfusingNumber2 {
    public static void main(String[] args) {
        System.out.println(confusingNumber(20));
    }

    static int[] digits = {0, 1, 6, 8, 9};
    static int[] rotate = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    private static int confusingNumber(int N) {
        int ans = 0;

        List<Integer> list = new ArrayList<>();
        list.add(0);

        boolean found = false;

        while (!found) {
            List<Integer> temp  = new ArrayList<>();
            for (int n: list) {
                for (int digit : digits) {
                    int next = n * 10 + digit;

                    if (next > N) {
                        found = true;
                        break;
                    }

                    if (next != 0) {
                        temp.add(next);
                    }

                    if (isConfusing(next)) {
                        ans++;
                    }
                }
                if (found) {
                    break;
                }
            }
            list = temp;
        }
        return ans;
    }

    private static boolean isConfusing(int num) {
        int temp = num;
        int rotation = 0;
        while (temp > 0) {
            int d = temp % 10;
            rotation = rotation * 10 + rotate[d];
            temp = temp / 10;
        }
        return rotation != num;
    }
}
