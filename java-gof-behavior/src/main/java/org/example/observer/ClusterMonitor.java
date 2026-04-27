package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class ClusterMonitor {
    // The list of subscribers
    private final List<ClusterObserver> observers = new ArrayList<>();


    public void attach(ClusterObserver observer) {
        observers.add(observer);
    }

    public void detach(ClusterObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(ClusterMetrics metrics) {
        for (ClusterObserver observer : observers) {
            observer.onMetricsUpdated(metrics);
        }
    }

    // Logic that triggers the state change
    public void updateNodeMetrics(String nodeId, double cpu, double memory) {
        System.out.printf("%n-> System fetched new metrics for %s%n", nodeId);
        ClusterMetrics currentMetrics = new ClusterMetrics(nodeId, cpu, memory);

        // Broadcast the new state to everyone listening
        notifyObservers(currentMetrics);
    }
}