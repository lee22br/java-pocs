package org.example.proxy;

public class SecureDatabaseProxy implements DatabaseConnection {
    private RealDatabaseConnection realConnection;
    private final String databaseUrl;
    private final User currentUser;

    public SecureDatabaseProxy(String databaseUrl, User currentUser) {
        this.databaseUrl = databaseUrl;
        this.currentUser = currentUser;
    }

    /**
     * Virtual Proxy Logic: Lazy Initialization
     */
    private RealDatabaseConnection getConnection() {
        if (realConnection == null) {
            realConnection = new RealDatabaseConnection(databaseUrl);
        }
        return realConnection;
    }

    @Override
    public String executeQuery(String query) {
        String upperQuery = query.trim().toUpperCase();

        // Protection Proxy Logic: Access Control
        if (upperQuery.startsWith("DROP") || upperQuery.startsWith("DELETE")) {
            if (!currentUser.isAdmin()) {
                throw new SecurityException("Access Denied: User '" + currentUser.getUsername()
                        + "' does not have ADMIN privileges to execute destructive queries.");
            }
        }

        // Delegate to the real object
        return getConnection().executeQuery(query);
    }

    public boolean isConnectionEstablished() {
        return realConnection != null;
    }
}
