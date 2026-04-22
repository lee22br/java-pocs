package org.example.proxy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseProxyTest {

    private static final String DB_URL = "jdbc:postgresql://production-cluster.local:5432/main_db";

    @Test
    void testVirtualProxy_DoesNotConnectUntilQueryIsExecuted() {
        User standardUser = new User("dev_john", "READ_ONLY");

        SecureDatabaseProxy proxy = new SecureDatabaseProxy(DB_URL, standardUser);

        // Assert: database connection has NOT been created yet
        assertFalse(proxy.isConnectionEstablished(), "Database should not connect on proxy instantiation.");

        String result = proxy.executeQuery("SELECT * FROM users");

        // Assert: connection established
        assertEquals("SUCCESS: Executed [SELECT * FROM users] on " + DB_URL, result);
        assertTrue(proxy.isConnectionEstablished(), "Database must be connected after the first query.");
    }

    @Test
    void testProtectionProxy_BlocksUnauthorizedDropTable() {
        User standardUser = new User("dev_john", "READ_ONLY");
        SecureDatabaseProxy proxy = new SecureDatabaseProxy(DB_URL, standardUser);

        SecurityException exception = assertThrows(SecurityException.class, () -> {
            proxy.executeQuery("DROP TABLE users;");
        });

        // The role read only, access denied
        assertTrue(exception.getMessage().contains("Access Denied"));

        // disconnect
        assertFalse(proxy.isConnectionEstablished());
    }

    @Test
    void testProtectionProxy_AllowsAdminToDropTable() {
        User dbaUser = new User("admin_sarah", "ADMIN");
        SecureDatabaseProxy proxy = new SecureDatabaseProxy(DB_URL, dbaUser);

        // Act
        String result = proxy.executeQuery("DELETE FROM cache_logs WHERE age > 30;");

        // Role admin has permission
        assertTrue(result.contains("SUCCESS"));
        assertTrue(proxy.isConnectionEstablished());
    }
}
