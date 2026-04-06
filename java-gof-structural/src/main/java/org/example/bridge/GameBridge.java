package org.example.bridge;

public class GameBridge {

    public static void main (String[] args) {

        GameConsole videoGame = new PlaystationPlatform();

        FpsGame cod = new FpsGame(videoGame, "Call Of Duty Modern War Fare III");

        cod.play();
    }
}
