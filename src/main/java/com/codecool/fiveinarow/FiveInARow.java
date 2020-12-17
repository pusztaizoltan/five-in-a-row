package com.codecool.fiveinarow;

public class FiveInARow {

    public static void main(String[] args) throws InterruptedException {
        StartScreen.Gomoku();
        Game game = new Game(19, 19);
        // game.enableAi(1);
        game.enableAi(2);
        game.play(5);
    }


}
