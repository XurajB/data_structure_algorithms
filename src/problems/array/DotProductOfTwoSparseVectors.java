package problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two sparse vectors, compute their dot product.
 * Implement class SparseVector:
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 * Follow up: What if only one of the vectors is sparse?
 */
public class DotProductOfTwoSparseVectors {
    public static void main(String[] args) {

    }

    // when we see a large sparse matrix, we want to do sparse matrix compression so we can store in memory
    // if one of the vectors is sparse, then compress that vector, the use binary search to find the matched index on other vector
    Map<Integer, Integer> map;

    DotProductOfTwoSparseVectors(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(DotProductOfTwoSparseVectors vec) {
        int product = 0;

        for (int key: map.keySet()) {
            if (vec.map.containsKey(key)) {
                product += map.get(key) * vec.map.get(key);
            }
        }

        return product;
    }
}
