package algorithms;

/**
 * Created by Xuraj on 11/27/2019.
 * Various searching algorithms
 */
public class Search {

    // linear search - one by one - O(n)
    public int linearSearch(int[] dataSet, int value) {
        for (int i =0; i< dataSet.length; i++) {
            if (dataSet[i]==value) {
                return i;
            }
        }
        return -1;
    }


}
