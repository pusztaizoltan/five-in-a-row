package com.codecool.fiveinarow;

import java.util.Scanner;

public class Game implements GameInterface {
    private int[][] board;

    public Game(int nRows, int nCols) {
        this.board = new int[nRows + 1][nCols + 1];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];
        String coordinates;
        int maxRow = board.length - 1;
        int maxCol = board[0].length - 1;

        while (true) {
            System.out.println("Please enter coordinates:");
            coordinates = scanner.nextLine();

            if (coordinates.toLowerCase().equals("quit")) System.exit(0);

            move[0] = Character.getNumericValue(coordinates.charAt(0)) - 9;

            if (coordinates.length() == 2) move[1] = Character.getNumericValue(coordinates.charAt(1));
            else if (coordinates.length() == 3)
                move[1] = Character.getNumericValue(coordinates.charAt(1)) * 10 + Character.getNumericValue(coordinates.charAt(2));

            if (move[0] <= maxRow && move[0] >= 0 && move[1] <= maxCol && move[1] > 0) {
                break;
            } else {
                System.out.println("Try Again!");
            }
        }
        return move;
    }

    public int[] getAiMove(int player) {
        return null;
    }

    public void mark(int player, int row, int col) {
        if (board[row][col] == 0) {
            if (player == 1) board[row][col] = 1;
            if (player == 2) board[row][col] = 2;
        } else {
            System.out.println("Already marked!");
        }
    }

    public boolean hasWon(int player, int howMany) {
        int counter = 0;

        //Horizontal
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == player) counter++;
                else counter = 0;
                if (counter >= howMany) return true;
            }
            counter = 0;
        }

        //Vertical
        for (int i = 1; i < board[i].length; i++) {
            for (int j = 1; j < board.length; j++) {
                if (board[j][i] == player) counter++;
                else counter = 0;
                if (counter >= howMany) return true;
            }
            counter = 0;
        }

        return false;
    }

    public boolean isFull() {
        boolean isFull = true;
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == 0) isFull = false;
            }
        }
        return isFull;
    }

    public void printBoard() {
        int colIndex = 1;
        char rowIndex = 'A';

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 && j == 0) System.out.print("   ");
                else if (i == 0) {
                    System.out.print(colIndex + " ");
                    if (colIndex < 10) System.out.print(" ");
                    colIndex++;
                } else if (j == 0) {
                    System.out.print(rowIndex + "  ");
                    rowIndex++;
                } else if (board[i][j] == 0) System.out.print(".  ");
                else if (board[i][j] == 1) System.out.print("X  ");
                else if (board[i][j] == 2) System.out.print("O  ");
            }
            System.out.println();
        }
    }

    public void printResult(int player) {
    }

    public void enableAi(int player) {
    }

    public void play(int howMany) {
        int[] move;

        while (true) {
            printBoard();
            move = getMove(1);
            mark(1, move[0], move[1]);
            if (isFull()) break;
            if (hasWon(1, howMany)) break;

            printBoard();
            move = getMove(2);
            mark(2, move[0], move[1]);
            if (isFull()) break;
            if (hasWon(2, howMany)) break;
        }
        printBoard();
        System.out.println("Game Over");
    }
}