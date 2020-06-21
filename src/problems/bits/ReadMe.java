package problems.bits;

public class ReadMe {
    /**
     * int = 4 bytes = 32 bits
     * bitwise operators: &, |, ~, ^, >>, <<
     * & - and
     * | - or
     * ~ - not (flips bit)
     * ^ - xor (true if odd number of 1s, opposite of or)
     * >> shift right by 1
     * 1011 >> 1 = 0101 (move bits to right, drop last one)
     * >> shift left by 1
     * 1011 << 1 = 10110 if it supports 5 bits. 0110 if only 4 bits
     */

    /**
     * multiply by 2 = left shift by 1
     * a * 2 == a << 1
     * Divide by 2 = right shift by 1
     * a / 2 == a >> 1
     */

    /**
     * Flip a number
     * Xor with 1
     * Not will flip all bits (10 -> 11111101).
     *
     * You can flip only few bits using not by creating a mask
     *
     */
}
