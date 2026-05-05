package org.example.chain;

import java.util.Map;

// Link 1: Authentication
public class AuthenticationMiddleware extends Middleware {
    private final Map<String, String> validApiKeys = Map.of(
            "admin_token_123", "ADMIN",
            "user_token_456", "USER"
    );

    @Override
    public boolean check(HttpRequest request) {
        System.out.println("-> [Auth] Verifying API Key...");

        if (request.apiKey() == null || !validApiKeys.containsKey(request.apiKey())) {
            System.out.println("<- [Auth] FAILED: Invalid or missing API Key.");
            return false; // Chain breaks here
        }

        System.out.println("   [Auth] SUCCESS.");
        return checkNext(request); // Proceed to next link
    }
}

