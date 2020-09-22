package problems.math;

public class RLEIterator {

    public static void main(String[] args) {
        RLEIterator iterator = new RLEIterator(new int[] {3,8,0,9,2,5});
        System.out.println(iterator.next(2));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(2));
    }

    int i = 0;
    int[] A;
    public RLEIterator(int[] A) {
        this.A = A;
    }
    public int next(int n) {
        // i < A.length - 1, coz we need to return A[i+1]
        while (i < A.length - 1 && n > A[i]) {
            n = n - A[i];
            i += 2;
        }
        if (i > A.length - 1) {
            return -1;
        }
        A[i] = A[i] - n;
        return A[i+1];
    }
}
