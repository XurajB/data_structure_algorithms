package problems.bfsdfs;

import java.util.*;

/**
 * Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the same hostname as startUrl.
 * Return all urls obtained by your web crawler in any order.
 * Your crawler should -
 * Start from the page: startUrl
 * Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
 * Do not crawl the same link twice.
 * Explore only the links that are under the same hostname as startUrl.
 */
public class WebCrawler {
    interface HtmlParser {
        List<String> getUrls(String url);
    }

    // O(N), O(N)
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        String host = getHost(startUrl);

        queue.offer(startUrl);
        visited.add(startUrl);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String url: htmlParser.getUrls(current)) {
                if (url.contains(host) && !visited.contains(url)) {
                    queue.offer(url);
                    visited.add(url);
                }
            }
        }
        return new ArrayList<>(visited);
    }

    private String getHost(String url) {
        String[] splits = url.split("/");
        return splits[2];
    }
}
