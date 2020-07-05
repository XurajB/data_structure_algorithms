package problems.string;

/**
 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
 */
public class ReadNCharacters {
    // we want to read n number of chars from a file into provided buf and we only have a method that gives us 4 chars at a time
    // we need to return the index. The buf array also needs to be updated with read chars
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        int index = 0;
        while (true) {
            int curr = read4(buffer);
            int currLen = Math.min(curr, n - index);
            for (int  i = 0; i < currLen; i++) {
                buf[i + index] = buffer[i];
            }
            index += currLen;
            if (index == n || currLen < 4) {
                return index;
            }
        }
    }

    // stub
    public int read4(char[] buf) {
        return 0; //stub
    }
}
