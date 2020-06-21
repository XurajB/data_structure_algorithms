package problems.map;

/**
 * Generate hash function for string
 */
public class HashFunction {
    /*
    Characteristics of a good hash function -
    1. Deterministic - it should return same hash code every time
    2. Evenly distributed - the functions shouldn't be crowded a certain space
    3. Uses all input values - use all input values
    Polynomial function is good
    time: e + mx + ix^2 + tx^2
    making x a prime number is good idea
     */
    public static void main(String[] args) {

    }

    private static int hash(String string) {
        int hash = 0;
        char[] ch = string.toCharArray();
        int x = 53;

        for (int i = 0; i < string.length(); i++) {
            hash = hash * x + ch[i];
        }
        return hash;
    }

}
