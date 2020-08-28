package problems.array;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 */
public class GasStation {
    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    // O(N^2)
    private static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        int fuel = 0;
        for (int i = 0; i < n; i++) {
            fuel = gas[i];
            for (int j = i + 1; j <= i + n; j++) {
                fuel -= cost[(j-1) % n];
                if (fuel < 0) {
                    // can't reach next station
                    // start from next position
                    break;
                }
                if (j % n == i) {
                    // we reach starting point
                    return i;
                }
                fuel += gas[j % n];
            }
        }
        return -1;
    }

    ///////
    // greedy
    // O(N)
    private static int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;

        int currentTank = 0;
        int totalTank = 0;
        int index = 0; // starting position

        for (int i = 0; i < n; i++) {
            totalTank += gas[i] - cost[i];
            currentTank += gas[i] - cost[i];

            // if current tank is < 0, we can reach next station
            if (currentTank < 0) {
                // start from another index
                index = i + 1;
                // empty tank
                currentTank = 0;
            }
        }

        return totalTank >= 0 ? index : -1;
    }
}
