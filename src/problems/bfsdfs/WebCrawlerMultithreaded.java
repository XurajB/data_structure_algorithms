package problems.bfsdfs;

import java.util.*;
import java.util.concurrent.*;

/**
 * Given a url startUrl and an interface HtmlParser, implement a Multi-threaded web crawler to crawl all links that are under the same hostname as startUrl.
 *
 * Return all urls obtained by your web crawler in any order.
 *
 * Your crawler should:
 *
 * Start from the page: startUrl
 * Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
 * Do not crawl the same link twice.
 * Explore only the links that are under the same hostname as startUrl.
 */
public class WebCrawlerMultithreaded {
    interface HtmlParser {
        List<String> getUrls(String url);
    }

    // This can be done in number of ways.
    // We can also use concurrenthashmap and spawn as many threads, but this could go out of hand because of too many threads. Can also be done using volatile and synchronized but too many threads issue.
    // Executor service allows us to control the number of threads
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostName = getHost(startUrl);

        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Deque<Future> tasks = new ArrayDeque<>();

        queue.offer(startUrl);

        // Create a thread pool of 4 threads to perform I/O operations.
        ExecutorService executor = Executors.newFixedThreadPool(4, r -> {
            Thread t = new Thread(r);
            // Use daemon threads so the program can exit.
            t.setDaemon(true);
            return t;
        });

        while (true) {
            String url = queue.poll();
            if (url != null) {
                if (getHost(url).equals(hostName) && !visited.contains(url)) {
                    res.add(url);
                    visited.add(url);
                    // Use a thread in thread pool to fetch new URLs and put them into the queue.
                    tasks.add(executor.submit(() -> {
                        List<String> newUrls = htmlParser.getUrls(url);
                        for (String newUrl : newUrls) {
                            queue.offer(newUrl);
                        }
                    }));
                }
            } else {
                if (!tasks.isEmpty()) {
                    // Wait for the next task to complete, which may supply new URLs into the queue.
                    Future nextTask = tasks.poll();
                    try {
                        nextTask.get();
                    } catch (InterruptedException | ExecutionException e) {
                        //
                    }
                } else {
                    // Exit when all tasks are completed.
                    break;
                }
            }
        }
        return res;
    }

    private String getHost(String url) {
        String[] splits = url.split("/");
        return splits[2];
    }

    //////////////////////////////////////////////////////////////////////
    ////////////////////////
    /// below using concurrent map
    public List<String> crawl2(String startUrl, HtmlParser htmlParser) {

        // find hostname
        int index = startUrl.indexOf('/', 7);
        String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;

        // multi-thread
        Crawler crawler = new Crawler(startUrl, hostname, htmlParser);
        crawler.map = new ConcurrentHashMap<>(); // reset result as static property belongs to class, it will go through all of the test cases
        crawler.result = crawler.map.newKeySet();
        Thread thread = new Thread(crawler);
        thread.start();

        crawler.joinThread(thread); // wait for thread to complete
        return new ArrayList<>(crawler.result);
    }

    static class Crawler implements Runnable {
        String startUrl;
        String hostname;
        HtmlParser htmlParser;
        public static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        public static Set<String> result = map.newKeySet();

        public Crawler(String startUrl, String hostname, HtmlParser htmlParser) {
            this.startUrl = startUrl;
            this.hostname = hostname;
            this.htmlParser = htmlParser;
        }

        @Override
        public void run() {
            if (this.startUrl.startsWith(hostname) && !this.result.contains(this.startUrl)) {
                this.result.add(this.startUrl);
                List<Thread> threads = new ArrayList<>();
                for (String s : htmlParser.getUrls(startUrl)) {
                    if(result.contains(s)) continue;
                    Crawler crawler = new Crawler(s, hostname, htmlParser);
                    Thread thread = new Thread(crawler);
                    thread.start();
                    threads.add(thread);
                }
                for (Thread t : threads) {
                    joinThread(t); // wait for all threads to complete
                }
            }
        }

        public static void joinThread(Thread thread) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
