package problems.string;

/**
 * Given a file and assume that you can only read the file using a given method read4, implement a method read to read n characters. Your method read may be called multiple times.
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
 */
public class ReadNCharacters {
    int read4(char[] buf) {
        return 0; // stub
    }

    char[] cache = new char[4];
    int current = 0;
    int prevCap = Integer.MAX_VALUE;

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int index = 0;
        while (n-- > 0) {
            if (current % 4 == 0) {
                prevCap = read4(cache);
                current = 0;
            }
            if (current < prevCap) {
                buf[index++] = cache[current++];
            }
        }

        return index;
    }

}
