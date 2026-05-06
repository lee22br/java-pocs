package org.example.command;

public class StartServerCommand implements ServerCommand {
    private final ServerEnvironment server;

    public StartServerCommand(ServerEnvironment server) {
        this.server = server;
    }

    @Override
    public void execute() {
        server.start();
    }

    @Override
    public void undo() {
        server.stop();
    }
}
