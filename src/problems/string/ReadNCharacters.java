package problems.string;

/**
 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
 */
public class ReadNCharacters {
    // we want to read n number of chars from a file into provided buf and we only have a method that gives us 4 chars at a time
    // we need to return the index. The buf array also needs to be updated with read chars
    char[] temp = new char[4];
    int i = 0;
    public int read(char[] buf, int n) {
        while (n >= 0) {
            int count = read4(temp);
            if (count == 0) {
                break;
            }
            for (int j = 0; j < Math.min(count, n); j++) {
                buf[i++] = temp[j];
            }
            n = n - count;
        }
        return i;
    }

    // stub
    public int read4(char[] buf) {
        return 0; //stub
    }
}
