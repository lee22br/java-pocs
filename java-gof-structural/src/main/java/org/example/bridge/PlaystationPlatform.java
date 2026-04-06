package org.example.bridge;

class PlaystationPlatform implements GameConsole {
    @Override
    public void loadAssets(String gameName) {
        System.out.println("[PS5] Loading proprietary .pkg assets for " + gameName);
    }

    @Override
    public void renderGraphics(String gameName) {
        System.out.println("[PS5] Rendering " + gameName + " using Ray Tracing cores.");
    }

    @Override
    public void executeLogic(String gameName) {
        System.out.println("[PS5] Running bytecode on custom Sony architecture.");
    }
}
