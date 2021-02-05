package problems.math;

/**
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 */
public class ConvertToHexadecimal {
    public static void main(String[] args) {
        System.out.println(toHex(-1));
    }

    /**
     * >> is arithmetic right shift. this is signed and will extend the sign bit
     * >>> is logical right shift. this is unsigned shift with zero extension.
     * Example:
     * 1 >> 4: 0
     * -1>> 4: -1 (preserves -ve)
     * 1 >>> 4: 0
     * -1 >>> 4: 268435455
     * >>> on -ve will give 2s complement
     * By logical right shifting, we are moving bits to the right and replacing with 0 on the left
     * How to read 4 bits at a time: by doing num & 15 which will give last 4 bits
     * 17 & 15 will give us 1, 20 & 15 = 4
     * num >> 4 will shift last 4 bits and reduce the num by 4 bits. until it becomes 0
     */
    private static String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();

        while (num != 0) {
            sb.append(hex[num & 15]);
            num = num >>> 4; // logical shift
            // if we used arithmetic shift, then we would have to keep a count variable and check if it not higher than 8
        }

        return sb.reverse().toString();
    }
}
