package com.codecool.fiveinarow;

import java.util.Arrays;
import java.util.Scanner;

public class Game implements GameInterface {
    private int[][] board;
    boolean player1AI = false; // added for AI
    boolean player2AI = false; // added for AI
    AI ai; // added for AI
    int howMany; // added for AI

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

            if (move[0] <= maxRow && move[0] >= 0 && move[1] <= maxCol && move[1] > 0 && isValidMark(move)) {
                break;
            } else {
                System.out.println("Try Again!");
            }
        }
        return move;
    }


    public void mark(int player, int row, int col) {
        board[row][col] = player;
    }

    public boolean hasWon(int player, int howMany) {
        int counter = 0;
        int counter2 = 0;

        //Horizontal check
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == player) counter++;
                else counter = 0;
                if (counter >= howMany) return true;
            }
            counter = 0;
        }

        //Vertical check
        for (int i = 1; i < board[0].length; i++) {
            for (int j = 1; j < board.length; j++) {
                if (board[j][i] == player) counter++;
                else counter = 0;
                if (counter >= howMany) return true;
            }
            counter = 0;
        }

        //Transverse check
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == player) {
                    counter++;
                    counter2++;
                    for (int k = 1; k < board.length - i && k < board[i].length - j; k++) {
                        if (board[i + k][j + k] == player) counter++;
                        else counter = 0;
                        if (counter >= howMany) return true;

                        if (i - k > 0 && board[i - k][j + k] == player) counter2++;
                        else counter2 = 0;
                        if (counter2 >= howMany) return true;
                    }
                    counter = 0;
                    counter2 = 0;
                }
            }
        }
        return false;
    }

    public boolean isFull() {
        boolean isFull = true;
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    isFull = false;
                    break;
                }
            }
        }
        return isFull;
    }

    public void printBoard() {
        System.out.println();
        int colIndex = 1;
        char rowIndex = 'A';

        for (int i = 0; i < board.length; i++) {
            if (i==1) {
                System.out.print("  +");
                System.out.print("---".repeat(board.length-1));
                System.out.print("--+");
                System.out.println();
            }
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 && j == 0) System.out.print("     ");
                else if (i == 0) {
                    System.out.print(colIndex + " ");
                    if (colIndex < 10) System.out.print(" ");
                    colIndex++;
                } else if (j == 0) {
                    System.out.print(rowIndex + " | ");
                    rowIndex++;
                } else if (board[i][j] == 0) System.out.print(" . ");
                else if (board[i][j] == 1) System.out.print(ConsoleColors.RED + " X " + ConsoleColors.RESET);
                else if (board[i][j] == 2) System.out.print(ConsoleColors.GREEN_BRIGHT + " O "+ ConsoleColors.RESET);
            }
            if (i != 0)System.out.print( " |");
            System.out.println();
        }
        System.out.print("  +");
        System.out.print("---".repeat(board.length-1));
        System.out.print("--+");
        System.out.println();
    }

    public void enableAi(int player) {
        if (player == 1) player1AI = true;
        if (player == 2) player2AI = true;
    }

    public int[] getAiMove(int player) {
        int otherPlayer = (player == 1) ? 2:1;
        this.ai = new AI(getSubBoard(this.board), this.howMany);
        ai.valueBoard = ai.evaluateBoard(getSubBoard(this.board), this.howMany, player, otherPlayer);
        return ai.getCoordinateWithMaxValue(ai.valueBoard);
    }
    public int[][] getSubBoard(int[][] board) {
        int[][] subBoard = new int[board.length-1][board[0].length-1];
        for (int i = 1; i < board.length; i++) {
            subBoard[i-1] = Arrays.stream(board[i]).skip(1).toArray();
        }
        return subBoard;
    }

    private boolean isValidMark (int[] move){
        return (this.board[move[0]][move[1]] == 0);
    }

    public void play(int howMany) {
        this.howMany = howMany; // added for AI
        int[] move;

        while (true) {

            if (player1AI) { // added for AI
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                move = getAiMove(1);
                mark(1, move[0]+1, move[1]+1);
                ai.printBoard(ai.valueBoard);
            } else {
                printBoard();
                move = getMove(1);
                mark(1, move[0], move[1]);

            }
            if (isFull()) break;
            if (hasWon(1, howMany)){
                printResult(1); // add printresult
                break;
            }


            if (player2AI) { // added for AI
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                move = getAiMove(2);
                mark(2, move[0]+1, move[1]+1);
                ai.printBoard(ai.valueBoard);
            } else {
                printBoard();
                move = getMove(2);
                mark(2, move[0], move[1]);
            }
            if (isFull()) break;
            if (hasWon(2, howMany)){
                printResult(2); // add printresult
                break;}
        }
        printBoard();
        System.out.println("Game Over");
    }

    public void printResult(int player) {
        if (player == 1) {
            System.out.println("'X' won!");
        } else if (player == 2) {
            System.out.println("'O' won!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}