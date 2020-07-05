package problems.bits;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 */
public class HammingDistance {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }

    private static int hammingDistance(int x, int y) {
        // xor will set bits to 1 if bits are different
        // 001 ^ 100 = 101
        int xor = x ^ y;
        // not we just need to count 1s
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1) { // if last bit is one
                distance++;
            }
            // shit bits to the right by 1
            xor = xor >> 1;
        }
        return distance;
    }

}
