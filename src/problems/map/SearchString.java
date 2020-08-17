package problems.map;

/**
 * Find the index where the larger string A contains a target string T
 */
public class SearchString {
    public static void main(String[] args) {
        System.out.println(search("helloab", "ab"));
    }

    // using Rabin karp algorithm
    // rolling hash function
    // O(N)
    private static int search(String str, String target) {
        if (str == null || target == null) {
            return -1;
        }
        if (target.length() > str.length()) {
            return -1;
        }

        int x = 53; // a prime number

        // calculate hash of the target first
        int hashT = 0;
        int hash = 0;

        // also calculate hash for the first target length letters of str
        for (int i = 0; i < target.length(); i++) {
            hashT = hashT * x + target.charAt(i);
            hash = hash * x + str.charAt(i);
        }

        // check if there is a match
        if (hash == hashT && target.equals(str.substring(0, target.length()))) {
            return 0;
        }

        // Every time we slide the window by one character,
        // we add the new character to the hash code and remove the first character from it.

        // calculate x^(target.length - 1) beforehand
        int xPow = 1;
        for (int i = 0; i < target.length() - 1; i++) {
            xPow *= x;
        }

        for (int i = target.length(); i < str.length(); i++) {
            int toRemove = str.charAt(i - target.length());
            hash = ((hash - toRemove * xPow) * x + str.charAt(i));
            if (hash == hashT && target.equals(str.substring(i-target.length() + 1, i+1))) {
                return i - target.length() + 1;
            }
        }

        return -1; //not found
    }
}
