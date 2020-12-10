package com.codecool.fiveinarow;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Game implements GameInterface {
    String[] rowNames = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    String[] colNames = "abcdefghijklmnopqrstuvwxyz".split("");
    int nRows;
    int nCols;
    private String[][] board;


    public Game(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.board = emptyBoard(nRows, nCols);
    }

    private String[][] emptyBoard(int nRows, int nCols) {
        String[][] board = new String[nRows][nCols];
        for (int row = 0; row < board.length; row++) {
            Arrays.fill(board[row], ".");               // fill empty cells with zeros
        }
        return board;
    }

    @Override
    public void setBoard(int[][] board) {

    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {
        return null;
    }

    public int[] getAiMove(int player) {
        return null;
    }

    public void mark(int player, int row, int col) {
    }

    public boolean hasWon(int player, int howMany) {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    public void printBoard() {
        System.out.print("   ");
        IntStream.range(0, nCols).forEach(i -> System.out.print( "  " + colNames[i]));
        System.out.print("\n");
        System.out.print("  +");
        IntStream.range(0, nCols).forEach(k ->System.out.print( "---"));
        System.out.print("--+\n");
        for (int i = 0; i < nRows; i++) {
            System.out.print(rowNames[i] + " |");
            Arrays.stream(board[i]).forEach(j -> System.out.print( "  " + j));
            System.out.print("  | " + rowNames[i]);
            System.out.print("\n");
        }
        System.out.print("  +");
        IntStream.range(0, nCols).forEach(k ->System.out.print( "---"));
        System.out.print("--+\n");
        System.out.print("   ");
        IntStream.range(0, nCols).forEach(i -> System.out.print( "  " + colNames[i]));
        System.out.print("\n");

    }

    public void printResult(int player) {
    }

    public void enableAi(int player) {
    }

    public void play(int howMany) {
    }
}
