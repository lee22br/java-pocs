package org.example.bridge;


abstract class Game {
    protected GameConsole console; // The Bridge
    protected String title;

    protected Game(GameConsole console, String title) {
        this.console = console;
        this.title = title;
    }

    abstract void play();
}
