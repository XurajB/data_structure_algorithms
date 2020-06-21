package problems.bits;

/**
 * Manipulate bits
 */
public class BitManipulations {
    public static void main(String[] args) {
        System.out.println(getBit(101100, 2));
        System.out.println(setBit(11101000, 2, 1));
        System.out.println(reverseBits(1001100));
    }

    // get bit at position i
    private static int getBit(int n, int i) {
        // first shift right by i so the bit is at rightmost position
        // then and by 1
        n = n >> i & 1;
        return n;
    }

    // set ith bit to val
    private static int setBit(int n, int i, int val) {
        // if we want to set it with 1 - make a mask at that bit with 1 and perform or
        // if 0 - make a mask with 1 and not it to reverse bits and perform and
        if (val == 1) {
            return (1 << i) | n;
        } else {
            return ~(1 << i) & n;
        }
    }

    private static int swapBits(int n, int i, int j) {
        // only swap if they are not the same
        if (getBit(n, i) != getBit(n, j)) {
            int mask = (1 << i) | (i << j);
            return n ^ mask;
        }
        return n;
    }

    // reverse bits in n
    private static int reverseBits(int n) {
        int i = 0;
        int j = 31; // java has 32 bits integer
        while (i < j) {
            n = swapBits(n, i++, j--);
        }
        return n;
    }
}
