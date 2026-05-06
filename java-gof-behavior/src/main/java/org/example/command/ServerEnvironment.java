package org.example.command;

/**
 * The Receiver
 */
public class ServerEnvironment {
    private final String serverName;
    private boolean isRunning = false;
    private String applicationVersion = "v1.0.0";

    public ServerEnvironment(String serverName) {
        this.serverName = serverName;
    }

    public void start() {
        isRunning = true;
        System.out.println("[" + serverName + "] Server STARTED.");
    }

    public void stop() {
        isRunning = false;
        System.out.println("[" + serverName + "] Server STOPPED.");
    }

    public void deployVersion(String newVersion) {
        this.applicationVersion = newVersion;
        System.out.println("[" + serverName + "] Deployed version: " + newVersion);
    }

    public boolean isRunning() { return isRunning; }
    public String getApplicationVersion() { return applicationVersion; }
}