package org.example.chain;

public class RateLimitMiddleware extends Middleware {
    private final int requestPerMinuteLimit;
    private int currentRequests = 0;

    public RateLimitMiddleware(int requestPerMinuteLimit) {
        this.requestPerMinuteLimit = requestPerMinuteLimit;
    }

    @Override
    public boolean check(HttpRequest request) {
        System.out.println("-> [RateLimit] Checking IP request count...");

        if (currentRequests >= requestPerMinuteLimit) {
            System.out.println("<- [RateLimit] FAILED: Too many requests for IP " + request.ipAddress());
            return false; // Chain breaks
        }

        currentRequests++;
        System.out.println("  [RateLimit] SUCCESS. Used quota: " + currentRequests + "/" + requestPerMinuteLimit);
        return checkNext(request);
    }
}
