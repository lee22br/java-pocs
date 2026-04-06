package org.example.bridge;

interface GameConsole {

    void loadAssets(String gameName);
    void renderGraphics(String gameName);
    void executeLogic(String gameName);

}
