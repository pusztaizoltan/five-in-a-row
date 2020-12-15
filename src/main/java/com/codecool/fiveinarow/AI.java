package com.codecool.fiveinarow;
import java.util.Arrays;
import java.util.stream.IntStream;

public class AI {

        String[] rowNames = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""); //jo
        String[] colNames = "abcdefghijklmnopqrstuvwxyz".split(""); //jo
        int nRows; //jo
        int nCols; //jo
        private int[][] board;
        int howMany;
        int[][] valueBoard;

        public AI(int[][] board, int howMany){
            this.board = board;
            this.howMany = howMany;
            this.nRows = board.length;
            this.nCols = board[0].length;
        }

        private int[][] emptyBoard(int nRows, int nCols) {
            int[][] board = new int[nRows][nCols];
            for (int row = 0; row < board.length; row++) {
                Arrays.fill(board[row], 0);               // fill empty cells with zeros
            }
            return board;
        }


        public void printBoard(int[][] board) { // testmethod
            printColNames(nCols, "");
            System.out.print("               ");
            printColNames(nCols, "\n");
            printSeparator(nCols, "");
            System.out.print("            ");
            printSeparator(nCols, "\n");
            int[] maxCoor = getCoordinateWithMaxValue(board);
            int maxVal = board[maxCoor[0]][maxCoor[1]];
            for (int i = 0; i < nRows; i++) {
                System.out.print(ConsoleColors.YELLOW + rowNames[i] + " |"  + ConsoleColors.RESET);
                Arrays.stream(this.board[i]).forEach(j -> System.out.print( "  " + convertMarker(j)));
                System.out.print(ConsoleColors.YELLOW + "  | " + rowNames[i] + "          ");
                System.out.print(rowNames[i] + " |"+ ConsoleColors.RESET);
                Arrays.stream(board[i]).forEach(j -> System.out.print("  " + ((j*2/maxVal*4.5)==0?".":(j*9/maxVal))));
                System.out.print(ConsoleColors.YELLOW + "  | " + rowNames[i] + ConsoleColors.RESET);
                System.out.print("\n");
            }
            printSeparator(nCols, "");
            System.out.print("            ");
            printSeparator(nCols, "\n");
            printColNames(nCols, "");
            System.out.print("               ");
            printColNames(nCols, "\n");

        }
        public String convertMarker(int player) {
            switch (player){
                case 1:
                    return "X";
                case 2:
                    return "0";
                default:
                    return ".";
            }
        }

        public void printColNames(int nCols, String endtag) {
            System.out.print("   ");
            IntStream.range(0, nCols).forEach(i -> System.out.print("  " + ConsoleColors.YELLOW + colNames[i] + ConsoleColors.RESET));
            System.out.print(endtag);
        }

        public void printSeparator(int nCols, String endtag) {
            System.out.print(ConsoleColors.YELLOW +"  +");
            IntStream.range(0, nCols).forEach(k ->System.out.print( "---"));
            System.out.print( "--+" +endtag + ConsoleColors.RESET);
        }
        public int[] getCoordinateWithMaxValue(int[][] board) {
            int value = 0;
            int[][] coordinates = new int[][]{{0, 0}};
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col]==value) {
                        coordinates = Arrays.copyOf(coordinates,coordinates.length + 1);
                        coordinates[coordinates.length-1] = new int[]{row, col};
                    }
                    if (board[row][col]>value) {
                        coordinates = new int[][]{{row, col}};
                        value = board[row][col];
                    }
                }
            }
            //System.out.println("variability of options" + coordinates.length);  //testline
            if (coordinates.length==1) return coordinates[0];
            return coordinates[(int)(Math.random() * ((coordinates.length-1) + 1))];
        }



        public int[][] evaluateBoard(int[][] board,
                                     int steps,
                                     int playerToMove,
                                     int otherPlayer) {
            int[][] valueBoard = emptyBoard(board.length, board[0].length);
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col]==0) {
                        valueBoard[row][col] = evaluateCoordinate(board,
                                new int[]{row, col},
                                steps,
                                playerToMove,
                                otherPlayer);
                    }
                }
            }
            return valueBoard;
        }

        public int[] getNext(int[] coordinate, String direction) {
            String[] validDirections = {"uu","ur","rr","rd","dd","dl","ll","lu"}; //PL.: uu = up, rd = right-down
            if (Arrays.asList(validDirections).contains(direction)){
                switch (direction){
                    case "uu":return new int[]{coordinate[0] - 1, coordinate[1]    };
                    case "ur":return new int[]{coordinate[0] - 1, coordinate[1] + 1};
                    case "rr":return new int[]{coordinate[0]    , coordinate[1] + 1};
                    case "rd":return new int[]{coordinate[0] + 1, coordinate[1] + 1};
                    case "dd":return new int[]{coordinate[0] + 1, coordinate[1]    };
                    case "dl":return new int[]{coordinate[0] + 1, coordinate[1] - 1};
                    case "ll":return new int[]{coordinate[0]    , coordinate[1] - 1};
                    case "lu":return new int[]{coordinate[0] - 1, coordinate[1] - 1};
                }
            } else System.out.println("invalid direction selection");
            return coordinate; // never get here
        }

        public int[] getTrace(int[][] board,
                              int[] startCoordinates,
                              String direction,
                              int steps) {
            int[] trace = new int[1];
            int[] traceCoordinates = startCoordinates;
            trace[0] = board[traceCoordinates[0]][traceCoordinates[1]];
            int nextTraceValue = 0 ;
            for (int i = 1; i < steps; i++) {
                traceCoordinates = getNext(traceCoordinates, direction);
                try { nextTraceValue = board[traceCoordinates[0]][traceCoordinates[1]];
                }catch (IndexOutOfBoundsException e){ break;}
                trace = Arrays.copyOf(trace, i + 1);
                trace[i] = nextTraceValue;
            }
            return trace;

        }

        public int[][] getFoldedLine(int[][] board,
                                     int[] startCoordinates,
                                     String[] directions,
                                     int steps) {
            int[][] foldedLine = new int[2][];
            foldedLine[0] = getTrace (board,
                    startCoordinates,
                    directions[0],
                    steps);
            foldedLine[1] = getTrace (board,
                    startCoordinates,
                    directions[1],
                    steps);
            return foldedLine;
        }

        public int evaluateFoldedLine(int[][] foldedLine,
                                      int playerToMove,
                                      int otherPlayer,
                                      int steps) {
            int value = 0;
            //go for victory: mean continuous marks with number of steps
            int counter =0;
            int endmark1 =0;
            int endmark2 =0;
            boolean continuous = false;
            for (int i = 1; i<foldedLine[0].length+1; i++) {
                try {
                    continuous = (foldedLine[0][i] == playerToMove);
                } catch (ArrayIndexOutOfBoundsException e){
                    endmark1 = otherPlayer;
                    break;
                }
                if (!continuous) {
                    endmark1 = foldedLine[0][i];
                    break;
                }
                counter++;
            }
            for (int i = 1; i<foldedLine[1].length + 1; i++) {
                try {
                    continuous = (foldedLine[1][i] == playerToMove);
                } catch (ArrayIndexOutOfBoundsException e){
                    endmark1 = otherPlayer;
                    break;
                }
                if (!continuous) {
                    endmark2 = foldedLine[1][i];
                    break;
                }
                counter++;
            }
            value += (counter >= steps-1)? 1500:0; //instant victory (pl from 4 to 5)
            value += (counter == steps-2 && endmark1 == 0 && endmark2 ==0)? 500:0; //probable victory (pl from 3 to 4 two open end)
            value += (counter == steps-2 && (endmark1 == 0 || endmark2 ==0))? 100:0; //stalling tactic (pl from 3 to 4 one open end)
            value += (counter == steps-3 && endmark1 == 0 && endmark2 ==0)? 100:0; //create opportunity tactic (pl from 3 to 4 one open end)
            value += (counter >=2 && endmark1 == 0 && endmark2 ==0)? 5:0; // put next to existing
            value += (counter >=1 && endmark1 == 0 && endmark2 ==0)? 5:0; // put next to existing
            //prevent loss: mean continuous marks with number of steps on the opponent
            counter =0;
            endmark1 =0;
            endmark2 =0;
            continuous = false;
            for (int i = 1; i<foldedLine[0].length+1; i++) {
                try {
                    continuous = (foldedLine[0][i] == otherPlayer);
                } catch (ArrayIndexOutOfBoundsException e){
                    endmark1 = playerToMove;
                    break;
                }
                if (!continuous) {
                    endmark1 = foldedLine[0][i];
                    break;
                }
                counter++;
            }
            for (int i = 1; i<foldedLine[1].length+1; i++) {
                try {
                    continuous = (foldedLine[1][i] == otherPlayer);
                } catch (ArrayIndexOutOfBoundsException e){
                    endmark1 = playerToMove;
                    break;
                }
                if (!continuous) {
                    endmark2 = foldedLine[1][i];
                    break;
                }
                counter++;
            }

            value += (counter >= steps-1)? 700:0; //prevent instant loss (pl from 4 to 5)
            value += (counter == steps-2 && endmark1 == 0 && endmark2 ==0)? 300:0; //prevent probable loss (pl from 3 to 4 two open end)
            value += (counter == steps-2 && (endmark1 == 0 || endmark2 ==0))? 10:0; //prevent stalling tactic (pl from 3 to 4 one open end)
            value += (counter == steps-3 && endmark1 == 0 && endmark2 ==0)? 10:0; //prevent opportunity (pl from 3 to 4 one open end)
            value += (counter >=2 && endmark1 == 0 && endmark2 ==0)? 5:0; // put next to existing
            value += (counter >=1 && endmark1 == 0 && endmark2 ==0)? 5:0; // put next to existing
            return value;
        }

        public int evaluateTrace(int[] trace,int playerToMove, int otherPlayer){
            int value = 0;
            boolean continuous;
            continuous = false;
            for (int i: trace) {
                if (i == otherPlayer){break;}
                if (i == playerToMove) {
                    if(continuous) value +=4;
                    value +=2;
                    continuous = true;
                }
                if (i == 0) {
                    value +=1;
                    continuous = false;}
            }
            continuous = false;
            for (int i: trace) {
                if (i == playerToMove){break;}
                if (i == otherPlayer){
                    if(continuous) value +=4;
                    value +=2;
                    continuous = true;
                }
                if (i == 0) {
                    value +=1;
                    continuous = false;
                };
            }
            return value;
        }

        public int evaluateCoordinate(int[][] board,
                                      int[] startCoordinates,
                                      int steps,
                                      int playerToMove,
                                      int otherPlayer) {
            String[][] validLines = {{"uu","dd"}, {"rr","ll"},{"lu","rd"},{"dl","ur"}};
            String[] validDirections = {"uu","ur","rr","rd","dd","dl","ll","lu"};
            int valueOfCoordinate = 0;
            for (String direction: validDirections) {
                valueOfCoordinate += evaluateTrace(getTrace(board, startCoordinates, direction, steps),
                        playerToMove,
                        otherPlayer);
            }
            for (String[] foldedLine: validLines) {
                valueOfCoordinate += evaluateFoldedLine(getFoldedLine(board, startCoordinates, foldedLine, steps),
                        playerToMove,
                        otherPlayer,
                        steps);
            }
            return valueOfCoordinate;
        }
    }
