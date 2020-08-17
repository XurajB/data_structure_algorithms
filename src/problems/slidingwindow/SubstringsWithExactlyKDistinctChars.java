package problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s and an int k, return an int representing the number of substrings (not unique) of s with exactly k distinct characters.
 * If the given string doesn't have k distinct characters, return 0.
 */
public class SubstringsWithExactlyKDistinctChars {
    public static void main(String[] args) {
        String s = "pqpqs";
        int k = 2;
        System.out.println(substringsWithKDistinctChars(s, k) - substringsWithKDistinctChars(s, k-1));
    }

    // for sliding window, if question is exactly k
    // then use this technique. exactly(k) = atMost(k) - atMost(k-1)

    // for chars
    private static int substringsWithKDistinctChars(String s, int k) {
        int left = 0, right = 0;
        Map<Character, Integer> charCount = new HashMap<>(); // char, count

        int answer = 0;
        while (right < s.length()) {
            int count = charCount.getOrDefault(s.charAt(right), 0) + 1;
            charCount.put(s.charAt(right++), count);

            while (charCount.size() > k) {
                int newCount = charCount.get(s.charAt(left)) - 1;
                charCount.put(s.charAt(left), newCount);
                if (charCount.get(s.charAt(left)) == 0) {
                    charCount.remove(s.charAt(left));
                }
                left++;
            }
            answer += right - left + 1;
        }

        return answer;
    }

    // for integers
    // total contiguous subrray with at most k integers
    // the intuition behind: atMostK(A, K) - atMostK(A, K - 1);
    // We are thinking of similar problem as: SubstringWithAtMostKDistinct
    // how many substrings can we create with at most k.
    // Example: Input: A = [1,2,1,2,3], K = 2. Output: 7
    // atMostK = 12, atMost(k-1) = 5. exactly k = 7
    // atMost(K): [1, 12, 121, 1212, 23], sum: 1 + 2 + 3 + 4 + 2 = 12
    // atMost(k-1): [1,2,1,2,3] = 5
    private static int substringsWithKDistinctChars(int[] A, int k) {
        int left = 0, right = 0;
        Map<Integer, Integer> charCount = new HashMap<>(); // char, count

        int answer = 0;
        while (right < A.length) {
            int count = charCount.getOrDefault(A[right], 0) + 1;
            charCount.put(A[right], count);
            right++;

            while (charCount.size() > k) {
                int newCount = charCount.get(A[left]) - 1;
                charCount.put(A[left], newCount);
                if (charCount.get(A[left]) == 0) {
                    charCount.remove(A[left]);
                }
                left++;
            }
            answer += right - left + 1;
        }

        return answer;
    }
}
