package org.example.command;

public class DeployPatchCommand implements ServerCommand {
    private final ServerEnvironment server;
    private final String targetVersion;
    private String previousVersion; // Stores state needed for rollback

    public DeployPatchCommand(ServerEnvironment server, String targetVersion) {
        this.server = server;
        this.targetVersion = targetVersion;
    }

    @Override
    public void execute() {
        // Save the current state, for rollback
        this.previousVersion = server.getApplicationVersion();
        server.deployVersion(targetVersion);
    }

    @Override
    public void undo() {
        if (previousVersion != null) {
            System.out.println("-> Rolling back patch...");
            server.deployVersion(previousVersion);
        }
    }
}
