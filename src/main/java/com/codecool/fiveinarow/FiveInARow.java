package com.codecool.fiveinarow;

import java.util.Arrays;

public class FiveInARow {

    public static void main(String[] args) throws Exception {
        Game game = new Game(19, 19); // final
////// TEST /////////////////////////////////////////////////
//        Game game = new Game(11, 11, true); // testline
//        game.printBoard(); //testline
//        int[][] valueBoard = game.evaluateBoard(game.getBoard(), 5, 1, 2);
//        game.printBoard(valueBoard);

//        int[] trace = game.getTrace(game.getBoard(), new int[]{4,3}, "rr", 5);
//        Arrays.stream(trace).forEach(i-> System.out.print(i + " "));

////// END TEST /////////////////////////////////////////////////
        game.enableAi(1); //final
        game.enableAi(2); //final
        game.play(6); //final
    }
}

