package org.example.chain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiServerTest {
    private ApiServer server;

    @BeforeEach
    void setUp() {
        server = new ApiServer();

        Middleware auth = new AuthenticationMiddleware();
        Middleware rateLimit = new RateLimitMiddleware(2); // Only 2 requests for test rate limit
        Middleware roleCheck = new RoleCheckMiddleware();

        auth.linkWith(rateLimit).linkWith(roleCheck);

        server.setMiddleware(auth);
    }

    @Test
    void testValidAdminRequest_PassesEntireChain() {
        HttpRequest request = new HttpRequest("admin_token_123", "192.168.0.1", "/admin/dashboard");
        assertTrue(server.processRequest(request));
    }

    @Test
    void testInvalidToken_BreaksAtAuthMiddleware() {
        HttpRequest request = new HttpRequest("hacker_token", "10.0.0.5", "/public/home");
        // Should fail
        assertFalse(server.processRequest(request));
    }

    @Test
    void testValidUserAccessingAdminPath_BreaksAtRoleMiddleware() {
        // Role check will fail.
        HttpRequest request = new HttpRequest("user_token_456", "192.168.0.2", "/admin/settings");
        assertFalse(server.processRequest(request));
    }

    @Test
    void testRateLimitExceeded_BreaksAtRateLimitMiddleware() {
        HttpRequest request = new HttpRequest("user_token_456", "192.168.0.3", "/public/api/data");

        // Request 1: OK
        assertTrue(server.processRequest(request));

        // Request 2: OK (Reaches limit)
        assertTrue(server.processRequest(request));

        // Request 3: Fails at RateLimitMiddleware
        assertFalse(server.processRequest(request));
    }
}
