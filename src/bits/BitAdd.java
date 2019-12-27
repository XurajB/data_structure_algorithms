package bits;

/**
 * Add two numbers without using +
 */
public class BitAdd {
    public static void main(String[] args) {
        System.out.println(add(20, 20));
        System.out.println(addRecursive(20, 20));
    }

    private static int add(int a, int b) {
        // iterate until there is no carry
        while (b != 0) {
            // carry now contains common
            // set bits of x and y
            int carry = a & b;
            // Sum of bits of x and
            // y where at least one
            // of the bits is not set
            a = a ^ b;
            // Carry is shifted by
            // one so that adding it
            // to x gives the required sum
            b = carry << 1;
        }

        return a;
    }

    private static int addRecursive(int a, int b) {
        if (b == 0) {
            return a;
        }
        return addRecursive(a ^ b, (a & b) << 1);
    }
}
