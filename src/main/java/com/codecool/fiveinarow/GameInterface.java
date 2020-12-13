/* DO NOT CHANGE THIS INTERFACE! It will be used to test your solution. */

package com.codecool.fiveinarow;


public interface GameInterface {
    int[][] getBoard();
    void setBoard(int[][] board);
    int[] getMove(int player);
    int[] getAiMove(int player);
    void mark(int player, int row, int col);
    boolean hasWon(int player, int howMany);
    boolean isFull();
    void printBoard();
    void printResult(int player);
    void enableAi(int player) throws Exception;
    void play(int howMany);
}
