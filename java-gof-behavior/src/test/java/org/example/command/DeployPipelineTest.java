package org.example.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeployPipelineTest {
    private ServerEnvironment prodServer;
    private DeploymentPipeline pipeline;

    @BeforeEach
    void setUp() {
        prodServer = new ServerEnvironment("PROD-DB-NODE-01");
        pipeline = new DeploymentPipeline();
    }

    @Test
    void testStandardExecution() {
        ServerCommand startNode = new StartServerCommand(prodServer);
        ServerCommand deployPatch = new DeployPatchCommand(prodServer, "v1.1.0");

        pipeline.executeCommand(startNode);
        pipeline.executeCommand(deployPatch);

        assertTrue(prodServer.isRunning());
        assertEquals("v1.1.0", prodServer.getApplicationVersion());
    }

    @Test
    void testUndoFunctionality_RollbackSingleStep() {
        pipeline.executeCommand(new DeployPatchCommand(prodServer, "v2.0.0"));
        assertEquals("v2.0.0", prodServer.getApplicationVersion());

        pipeline.rollbackLastStep();

        assertEquals("v1.0.0", prodServer.getApplicationVersion(),
                "Server should be restored to the previous version.");
    }

    @Test
    void testFullPipelineRollback() {
        assertFalse(prodServer.isRunning());
        assertEquals("v1.0.0", prodServer.getApplicationVersion());

        pipeline.executeCommand(new StartServerCommand(prodServer));
        pipeline.executeCommand(new DeployPatchCommand(prodServer, "v1.5.0"));
        pipeline.executeCommand(new DeployPatchCommand(prodServer, "v2.0.0-BETA"));

        assertTrue(prodServer.isRunning());
        assertEquals("v2.0.0-BETA", prodServer.getApplicationVersion());

        //initiate full panic rollback
        pipeline.rollbackAll();

        assertFalse(prodServer.isRunning(), "Server should be stopped again.");
        assertEquals("v1.0.0", prodServer.getApplicationVersion(), "Version should be strictly v1.0.0.");
    }
}
