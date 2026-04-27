package org.example.observer;

public interface ClusterObserver {
    void onMetricsUpdated(ClusterMetrics metrics);
}
