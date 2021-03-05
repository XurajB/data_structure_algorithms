package problems.array;

/**
 * Non-critical requests for a transation system are routed through a throttling gateway to ensure that the network is not chocked by non-essential requests.
 * The gateway has the following limits:
 * The number of transations in any give seconds cannot exceed 3
 * The number of transactions in any 10 second period cannot exceed 20
 * The number of transaction in any 60 second period cannot exceed 60
 * Calculate total dropped packets
 */
public class ThrottlingGateway {
    private int lostCount(int[] requestTimes) {
        int ans = 0;
        for (int i = 0; i < requestTimes.length; i++) {
            if (i > 2 && requestTimes[i-3] == requestTimes[i]) {
                ans++; // case1
            } else if (i > 19 && requestTimes[i] - requestTimes[i-20] < 10) {
                ans++;
            } else if (i > 59 && requestTimes[i] - requestTimes[i-60] < 60) {
                ans++;
            }
        }
        return ans;
    }
}
