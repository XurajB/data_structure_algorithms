package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/prison-cells-after-n-days/submissions/
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * Given the initial state of the prison, return the state of the prison after N days
 */
public class PrisonCellsAfterNDays {
    public static void main(String[] args) {
        int[] cells = new int[] {0,1,0,1,1,0,0,1};
        System.out.println(Arrays.toString(prisonAfterNDays(cells, 1000)));
    }

    // O(1), since size of cells is 8. at most states = 2^6 (first and last are omitted) = 64 states
    // regardless size of N, the states are always max 64 because we break when we hit a cycle
    private static int[] prisonAfterNDays(int[] cells, int n) {
        if (cells == null || cells.length == 0 || n <= 0) {
            return cells;
        }

        boolean hasCycle = false;
        int cycle = 0;
        Set<String> cellState = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int[] next = nextDay(cells);
            String state = Arrays.toString(next);
            if (!cellState.contains(state)) {
                cellState.add(state); // store cell state
                cycle++;
            } else { // we hit a cycle
                hasCycle = true;
                break;
            }
            cells = next;
        }
        if (hasCycle) {
            n = n % cycle; // if n is 1000, cycle is hit in 14, 1000%14 = 6, we will get result in 6 more steps
            for (int i = 0; i < n; i++) {
                cells = nextDay(cells);
            }
        }
        return cells;
    }

    private static int[] nextDay(int[] cells) {
        int[] tmp = new int[cells.length];
        for(int i = 1; i < cells.length - 1; i++){
            tmp[i] = cells[i-1] == cells[i+1] ? 1 : 0;
        }
        return tmp;
    }
}
