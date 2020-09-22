package problems.string;

/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 *
 * You may assume the default revision number for each level of a version number to be 0.
 * For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.
 */
public class CompareVersionNumbers {
    public static void main(String[] args) {
        System.out.println(compareVersion("1.2.3", "1.1.3"));
    }

    // O(M+N)
    // if going with split route: make sure to escape .
    // version1.split("\\.")
    private static int compareVersion(String version1, String version2) {
        int i = 0;
        int j = 0;
        while (i < version1.length() || j < version2.length()) {
            int part1 = 0;
            int part2 = 0;
            while (i < version1.length() && version1.charAt(i) != '.') {
                part1 = part1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            while (j < version2.length() && version2.charAt(j) != '.') {
                part2 = part2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            if (part1 != part2) {
                return (part1 > part2) ? 1 : -1;
            }
            i++;
            j++;
        }
        return 0;
    }
}
