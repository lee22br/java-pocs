package org.example.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClusterMonitorTest {
    private ClusterMonitor monitor;

    @BeforeEach
    void setUp() {
        monitor = new ClusterMonitor();
    }

    @Test
    void testObserversReceiveNotifications() {
        TestObserver testObserver = new TestObserver();
        monitor.attach(testObserver);

        monitor.updateNodeMetrics("k8s-worker-01", 45.0, 60.0); // Normal
        monitor.updateNodeMetrics("k8s-worker-02", 95.5, 80.0); // Critical

        assertEquals(2, testObserver.receivedMetrics.size(),
                "Observer should have received exactly 2 notifications.");

        assertEquals("k8s-worker-01", testObserver.receivedMetrics.get(0).getNodeId());
        assertEquals("k8s-worker-02", testObserver.receivedMetrics.get(1).getNodeId());
    }

    @Test
    void testDynamicDetachment() {

        TestObserver observer1 = new TestObserver();
        TestObserver observer2 = new TestObserver();

        monitor.attach(observer1);
        monitor.attach(observer2);

        monitor.updateNodeMetrics("node-A", 10.0, 10.0);

        // observer1 removed
        monitor.detach(observer1);

        //Second update (Only observer 2 receives)
        monitor.updateNodeMetrics("node-B", 20.0, 20.0);

        assertEquals(1, observer1.receivedMetrics.size(), "Observer 1 was detached, should only have 1 message.");
        assertEquals(2, observer2.receivedMetrics.size(), "Observer 2 remained attached, should have 2 messages.");
    }


    public static class TestObserver implements ClusterObserver {
        final List<ClusterMetrics> receivedMetrics = new ArrayList<>();

        @Override
        public void onMetricsUpdated(ClusterMetrics metrics) {
            receivedMetrics.add(metrics);
        }
    }
}