package problems.string;

/**
 * Check if a string A is a rotation of another String B
 * atbobc is a rotation of abobcat
 */
public class RotatedString {
    public static void main(String[] args) {
        System.out.println(isRotation("abobcat", "atabobc"));
    }

    // O(N)
    private static boolean isRotation(String s1, String s2) {
        // if we add same string: atbobc + atbobc = atbobcatbobc
        // another string will be substring of that string
        s1 = s1 + s1;
        return s1.contains(s2);
    }
}