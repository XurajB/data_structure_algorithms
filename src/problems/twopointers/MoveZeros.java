package problems.twopointers;

import java.util.Arrays;

/**
 * Move all zeros to the right and non zeros to the left of the array
 */
public class MoveZeros {
    public static void main(String[] args) {
        int[] elements = {0,5,0,6,9,0,0,0};
        moveElements(elements);
        System.out.println(Arrays.toString(elements));
    }

    // Time: O(N), space: O(1)
    private static void moveElements(int[] elements) {
        int temp;

        int i = 0;
        int j = elements.length - 1;

        while (i < j) {
            if (elements[j] == 0) {
                j--;
            } else {
                if (elements[i] == 0) {
                    temp = elements[j];
                    elements[j] = elements[i];
                    elements[i] = temp;
                    j--;
                } else {
                    i++;
                }
            }
        }
    }
}
