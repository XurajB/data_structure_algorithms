package problems.math;

/**
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
 */
public class Rand10 {
    public int rand10() {
        int result = 40;
        while (result >= 40) {
            result = 7 * (rand7() - 1) + (rand7() - 1);
        }
        return result % 10 + 1;
    }

    // dummy
    int rand7() {
        return 0; // dummy
    }
}
