package problems.backtracking;

/**
 * We are given N different types of stickers. Each sticker has a lowercase English word on it.
 * You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.
 * You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 * What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.
 */
public class StickersToSpellWord {
    public static void main(String[] args) {
        System.out.println(minStickers(new String[] {"with", "example", "science"}, "thehat"));
    }

    // (2^(target))*stickers
    private static int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] charMap = new int[n][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c: stickers[i].toCharArray()) {
                charMap[i][c-'a']++;
            }
        }

        helper(target, 0, 0, charMap, new int[26]);
        if (count == Integer.MAX_VALUE) {
            return -1;
        }
        return count;
    }

    static int count = Integer.MAX_VALUE;
    private static void helper(String target, int index, int stkrCount, int[][] charMap, int[] charsAvailable) {
        if (stkrCount >= count) { // looking for min count. prune
            return;
        }
        if (index == target.length()) {
            count = Math.min(count, stkrCount);
            return;
        }
        char c = target.charAt(index);
        if (charsAvailable[c-'a'] > 0) {
            charsAvailable[c-'a']--;
            helper(target, index+1, stkrCount, charMap, charsAvailable);
            charsAvailable[c-'a']++;
        } else {
            for (int i = 0; i < charMap.length; i++) {
                if (charMap[i][c-'a'] > 0) {
                    for (int k = 0; k < 26; k++) {
                        charsAvailable[k] += charMap[i][k];
                    }
                    helper(target, index, stkrCount+1, charMap, charsAvailable);
                    for (int k = 0; k < 26; k++) {
                        charsAvailable[k] -= charMap[i][k];
                    }
                }
            }
        }
    }
}
