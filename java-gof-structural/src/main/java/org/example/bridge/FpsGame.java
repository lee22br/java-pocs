package org.example.bridge;

class FpsGame extends Game {
    public FpsGame(GameConsole console, String title) {
        super(console, title);
    }

    @Override
    void play() {
        console.loadAssets(title);
        console.executeLogic(title);
        console.renderGraphics(title);
        System.out.println("Started playing Game: " + title);
    }
}