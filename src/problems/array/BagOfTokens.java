package problems.array;

import java.util.Arrays;

/**
 * You have an initial power of P, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).
 *
 * Your goal is to maximize your total score by potentially playing each token in one of two ways:
 *
 * If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
 * If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
 * Each token may be played at most once and in any order. You do not have to play all the tokens.
 *
 * Return the largest possible score you can achieve after playing any number of tokens.
 */
public class BagOfTokens {
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);

        if (tokens.length == 0 || tokens[0] > P) {
            return 0;
        }

        int score = 0;
        int left = 0;
        int right = tokens.length - 1;

        while (left <= right) {
            if (P >= tokens[left]) {
                score++;
                P -= tokens[left];
                left++;
            } else if (right - left > 1) {
                P += tokens[right--];
                score--;
            } else {
                break;
            }
        }

        return score;
    }
}
