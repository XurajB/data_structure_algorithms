package dataStructures;

/**
 * Created by Xuraj on 11/11/2019.
 * Arrays in Java
 */
public class MyArrays {

    public void evaluate() {
        // declaration
        String[] cars;
        char[] reverseArray = new char[10];

        // declaration empty with fixed size
        String[] types = new String[10];

        // declaration with assignment
        String[] models = {"corolla", "camry"};

        // declaration with assignment
        int[] myNum = {1, 2, 6, 0};

        // access elements
        System.out.println(models[1]);
        System.out.println(myNum[2]);

        // update element
        types[1] = "rav4";

        // array length
        System.out.println(myNum.length); // property not a method so O(1)

        // looping through an array
        for (int a: myNum) {
            System.out.println(a);
        }

        // multi-dimensional arrays
        // declaration
        int[][] myNumbers = {{1, 2, 3, 4, 5}, {6, 7, 8, 9}};

        // accessing
        System.out.println(myNumbers[1][3]); // 1 -> array, 3 -> element. prints 9
        System.out.println(myNumbers[0][2]); // prints 3

        // looping
        for (int[] myNumber : myNumbers) {
            for (int aMyNumber : myNumber) {
                System.out.print(aMyNumber);
            }
            System.out.println();
        }
    }
}
