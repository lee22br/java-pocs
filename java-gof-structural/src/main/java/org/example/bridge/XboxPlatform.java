package org.example.bridge;

class XboxPlatform implements GameConsole {
    @Override
    public void loadAssets(String gameName) {
        System.out.println("[Xbox] Loading DirectX-optimized assets for " + gameName);
    }

    @Override
    public void renderGraphics(String gameName) {
        System.out.println("[Xbox] Rendering " + gameName + " with Velocity Architecture.");
    }

    @Override
    public void executeLogic(String gameName) {
        System.out.println("[Xbox] Running on Windows-based VM environment.");
    }
}
