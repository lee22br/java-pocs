package org.example.observer;

public class PagerDutyAlerter implements ClusterObserver {
    private final String onCallEngineerId;

    public PagerDutyAlerter(String onCallEngineerId) {
        this.onCallEngineerId = onCallEngineerId;
    }

    @Override
    public void onMetricsUpdated(ClusterMetrics metrics) {
        // PagerDuty only cares about critical alerts
        if (metrics.isCritical()) {
            System.out.printf("[PagerDuty] Waking up engineer %s! Node %s is failing!%n",
                    onCallEngineerId, metrics.getNodeId());
        }
    }
}
