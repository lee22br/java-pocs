package org.example.chain;

public class ApiServer {
    private Middleware middlewareChain;

    public void setMiddleware(Middleware middlewareChain) {
        this.middlewareChain = middlewareChain;
    }

    public boolean processRequest(HttpRequest request) {
        // Starts chain
        if (middlewareChain.check(request)) {
            System.out.println("=== REQUEST GRANTED ===");
            // Business logic (ex., fetch data from DB)
            return true;
        } else {
            System.out.println("=== REQUEST REJECTED ===");
            return false;
        }
    }
}