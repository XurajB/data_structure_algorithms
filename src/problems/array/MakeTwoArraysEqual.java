package problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays of equal length target and arr.
 * In one step, you can select any non-empty sub-array of arr and reverse it. You are allowed to make any number of steps.
 * Return True if you can make arr equal to target, or False otherwise.
 */
public class MakeTwoArraysEqual {
    // either this or sort both and check if Arrays.equals(target, arr)
    public boolean canBeEqual(int[] target, int[] arr) {
        if (arr.length != target.length) {
            return false;
        }
        Map<Integer, Integer> mapt = new HashMap<>();
        for (int num: target) {
            mapt.put(num, mapt.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> mapa = new HashMap<>();
        for (int num: arr) {
            mapa.put(num, mapa.getOrDefault(num, 0) + 1);
        }
        for (int key: mapt.keySet()) {
            if (!mapa.containsKey(key)) {
                return false;
            }
            if (!mapt.get(key).equals(mapa.get(key))) {
                return false;
            }
        }
        return true;
    }
}
