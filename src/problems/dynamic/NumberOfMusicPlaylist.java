package problems.dynamic;

/**
 * Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  You create a playlist so that:
 *
 * Every song is played at least once
 * A song can only be played again only if K other songs have been played
 * Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.
 */
public class NumberOfMusicPlaylist {
    public static void main(String[] args) {
        System.out.println(numMusicPlaylists(3, 3, 1));
    }

    // O(NL), O(NL)
    private static int numMusicPlaylists(int N, int L, int K) {
        int MOD = (int) Math.pow(10, 9) + 7;
        long[][] dp = new long[L+1][N+1];

        dp[0][0] = 1; // let dp[i][j] be number of playlists of length i, with j diff songs
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= N; j++) {
                // two cases.
                // case 1: listen to i-1 songs with j-1 different songs,
                // with additional choices of j songs
                // case 2: listen to i-1 songs with j different songs, then last one is old song
                // with the choices of j or (j-K) since we can't choose old song within K distance
                dp[i][j] = (dp[i-1][j-1] * j) %MOD;
                if (j > K) {
                    // we can also pick repeated songs
                    dp[i][j] = (dp[i][j] + (dp[i-1][j] * (j - K)) %MOD) %MOD;
                }
            }
        }
        return (int) dp[L][N];
    }
}
