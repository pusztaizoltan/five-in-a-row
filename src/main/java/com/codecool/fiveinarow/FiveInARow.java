package com.codecool.fiveinarow;

public class FiveInARow {

    public static void main(String[] args) {
        Game game = new Game(9, 9);

    //    game.enableAi(1);
    //    game.enableAi(2);
        game.play(3);
    }
}
