package org.example.proxy;

public class RealDatabaseConnection implements DatabaseConnection {
    private final String databaseUrl;

    public RealDatabaseConnection(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        connectToDatabase();
    }

    private void connectToDatabase() {
        System.out.println("-> [SYSTEM] Initiating TCP connection to remote DB: " + databaseUrl);
        System.out.println("-> [SYSTEM] Authenticating and allocating connection pool...");
        //simulated network
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        System.out.println("-> [SYSTEM] Connection established successfully.");
    }

    @Override
    public String executeQuery(String query) {
        return "SUCCESS: Executed [" + query + "] on " + databaseUrl;
    }
}
