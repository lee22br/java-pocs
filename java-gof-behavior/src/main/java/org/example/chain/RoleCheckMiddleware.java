package org.example.chain;

public class RoleCheckMiddleware extends Middleware {
    @Override
    public boolean check(HttpRequest request) {
        System.out.println("-> [RoleCheck] Verifying permissions for path: " + request.path());

        //only admin with token123
        if (request.path().startsWith("/admin") && !request.apiKey().equals("admin_token_123")) {
            System.out.println("<- [RoleCheck] FAILED: Insufficient permissions.");
            return false;
        }

        System.out.println("   [RoleCheck] SUCCESS.");
        return checkNext(request);
    }
}
