package problems.binarysearch;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 */
public class FirstBadVersion {
    public static void main(String[] args) {
        System.out.println(firstBadVersion(10));
    }

    private static int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static boolean isBadVersion(int version) {
        if (version >= 4) { //random
            return true;
        }
        return false;
    }
}
