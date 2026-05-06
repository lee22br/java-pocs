package org.example.command;

import java.util.Stack;

/**
 * The Invoker: Call the commands
 */
public class DeploymentPipeline {

    // Using a Stack for Undo commands
    private final Stack<ServerCommand> executionHistory = new Stack<>();

    public void executeCommand(ServerCommand command) {
        command.execute();
        executionHistory.push(command);
    }

    public void rollbackLastStep() {
        if (!executionHistory.isEmpty()) {
            ServerCommand lastCommand = executionHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands in history to rollback.");
        }
    }

    public void rollbackAll() {
        System.out.println("=== INITIATING FULL ROLLBACK ===");
        while (!executionHistory.isEmpty()) {
            rollbackLastStep();
        }
        System.out.println("=== ROLLBACK COMPLETE ===");
    }
}
