package problems.design;

/**
 * Design a RateLimiter class using token bucket algorithm
 */
public class RateLimiter {
    private long capacity;
    private long refillRate;

    private double currentBucketSize;
    private long lastRefillTimeStamp;

    public RateLimiter(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;

        lastRefillTimeStamp = System.currentTimeMillis(); // or System.nanoTime();
        // number of token initially is equal to capacity
        currentBucketSize = capacity;
    }

    public double getCurrentBucketSize() {
        return currentBucketSize;
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();

        if (currentBucketSize > 0) {
            currentBucketSize -= tokens;
            return true;
        }
        // request is throttled as bucket does not have enough tokens
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        // these many tokens accumulated since last refill
        double tokensToAdd = (now - lastRefillTimeStamp) * refillRate /1e6;
        // number of tokens do not exceed max capacity
        currentBucketSize = Math.min(currentBucketSize + tokensToAdd, capacity);
        lastRefillTimeStamp = now;
    }
}
