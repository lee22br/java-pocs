package org.example.bridge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameBridgeTest {

    @Mock
    private PlaystationPlatform ps5Mock;

    @Mock
    private XboxPlatform xboxMock;

    @Test
    void testFpsGame_ShouldFollowStandardExecutionFlow() {
        String gameTitle = "Call of Duty";
        FpsGame game = new FpsGame(xboxMock, gameTitle);

        game.play();

        InOrder inOrder = inOrder(xboxMock);

        inOrder.verify(xboxMock).loadAssets(gameTitle);
        inOrder.verify(xboxMock).executeLogic(gameTitle);
        inOrder.verify(xboxMock).renderGraphics(gameTitle);
    }

    @Test
    @DisplayName("Should ensure the game uses Playstation hardware when injected")
    void testGameOnPlaystation() {
        String title = "Battlefield 6";
        FpsGame game = new FpsGame(ps5Mock, title);

        game.play();

        verify(ps5Mock, times(1)).loadAssets(title);
        verify(ps5Mock, times(1)).renderGraphics(title);

        // don't call xbox
        verifyNoInteractions(xboxMock);
    }

    @Test
    @DisplayName("Should ensure the game uses Xbox hardware when injected")
    void testGameOnXbox() {
        String title = "Halo Infinite";
        FpsGame game = new FpsGame(xboxMock, title);

        game.play();

        verify(xboxMock, times(1)).loadAssets(title);
        verify(xboxMock, times(1)).executeLogic(title);

        // don't call ps5
        verifyNoInteractions(ps5Mock);
    }

    @Test
    @DisplayName("Verify platform-specific behavior through the bridge")
    void testPlatformSpecificFlow() {
        // just uses the Bridge.

        GameConsole[] consoles = { ps5Mock, xboxMock };
        String title = "Cross-Platform Game";

        for (GameConsole console : consoles) {
            FpsGame game = new FpsGame(console, title);
            game.play();

            // Verify that each console received the call
            verify(console).loadAssets(title);
        }
    }
}