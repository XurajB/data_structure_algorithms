package maths;

public class AbsoluteValue {

    public static void main(String[] args) {
        System.out.println("Abs: " + abs(-34));
    }

    private static int abs(int value) {
        return value < 0 ? -value : value;
    }
}
