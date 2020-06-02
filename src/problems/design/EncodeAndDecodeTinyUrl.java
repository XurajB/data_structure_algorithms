package problems.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * TinyURL is a URL shortening service where you enter a URL such as https://something.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 * Design the encode and decode methods for the TinyURL service.
 * https://leetcode.com/problems/encode-and-decode-tinyurl/
 */
public class EncodeAndDecodeTinyUrl {

    Map<String, String> urlMap = new HashMap<>();
    Map<String, String> reverseUrlMap = new HashMap<>();
    private static final String BASE = "http://tinyurl.com/";
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // 26 * 2 + 10 total length

    public static void main(String[] args) {
        EncodeAndDecodeTinyUrl url = new EncodeAndDecodeTinyUrl();
        String key = url.encode("http://helloworld.com/hello");
        System.out.println(key);
        System.out.println(url.decode(key));
    }

    // O(1), O(n)
    private String encode(String longUrl) {
        if (reverseUrlMap.containsKey(longUrl)) {
            return BASE + reverseUrlMap.get(longUrl);
        }

        Random random = new Random();
        StringBuilder key = null;

        // keep generating keys until a unique one is found
        // there are (10 + 26 * 2) ^ 6 different combinations, we can increase 6 to get more combos
        do {
            key = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int r = random.nextInt(CHAR_SET.length());
                key.append(CHAR_SET.charAt(r));
            }
        } while (urlMap.containsKey(key.toString()));

        urlMap.put(key.toString(), longUrl);
        reverseUrlMap.put(longUrl, key.toString());

        return BASE + key.toString();
    }

    private String decode(String shortUrl) {
        return urlMap.get(shortUrl.replace(BASE, ""));
    }
}
