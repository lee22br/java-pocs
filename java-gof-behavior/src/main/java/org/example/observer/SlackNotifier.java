package org.example.observer;

public class SlackNotifier implements ClusterObserver {
    private final String channelName;

    public SlackNotifier(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void onMetricsUpdated(ClusterMetrics metrics) {
        if (metrics.isCritical()) {
            System.out.printf("[Slack -> %s] URGENT: Node %s is in critical state! CPU: %.2f%%%n",
                    channelName, metrics.getNodeId(), metrics.getCpuUsage());
        } else {
            System.out.printf("[Slack -> %s] Node %s metrics normal.%n",
                    channelName, metrics.getNodeId());
        }
    }
}
