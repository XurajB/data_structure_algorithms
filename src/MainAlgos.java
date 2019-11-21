import algorithms.Algos1;

/**
 * Created by Xuraj on 11/12/2019.
 *
 * Entry point for algorithm samples
 */
public class MainAlgos {

    Algos1 algos1 = new Algos1();

    public static void main(String[] args) {
        MainAlgos algos = new MainAlgos();
        algos.verifyStringReverse();
    }

    private void verifyStringReverse() {
        algos1.reverseString("suraj");
        System.out.println();
        System.out.println(algos1.reverseString2("suraj"));
    }
}
