package org.example.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionPoolTest {

    @Test
    @DisplayName("Return Singleton Instance")
    void testSingletonInstance() {
        DatabaseConnectionPool instance1 = DatabaseConnectionPool.getInstance();
        DatabaseConnectionPool instance2 = DatabaseConnectionPool.getInstance();

        assertSame(instance1, instance2, "Instances are identical");
    }

    @Test
    @DisplayName("Only one instance")
    void testSingletonThreadSafety() throws InterruptedException {
        int threadCount = 50;
        ExecutorService service = Executors.newFixedThreadPool(threadCount);

        Set<DatabaseConnectionPool> instances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        for (int i = 0; i < threadCount; i++) {
            service.submit(() -> {
                instances.add(DatabaseConnectionPool.getInstance());
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        assertEquals(1, instances.size(), "Only one instance");
    }
}