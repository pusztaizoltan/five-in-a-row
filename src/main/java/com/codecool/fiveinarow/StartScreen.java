package com.codecool.fiveinarow;

public class StartScreen {

    public static void Gomoku() {
         System.out.println();
         System.out.println();
         System.out.println();
         System.out.println();
         System.out.println(ConsoleColors.RED_BRIGHT   + "         XXXXXX         OOOO        XXX     XXX        OOOO        XX    XX      OO    OO");
         System.out.println(ConsoleColors.GREEN_BRIGHT + "        XX    XX       OO  OO       XX XX XX XX       OO  OO       XX   XX       OO    OO");
         System.out.println(ConsoleColors.RED_BRIGHT   + "        XX            OO    OO      XX   X   XX      OO    OO      XX  XX        OO    OO");
         System.out.println(ConsoleColors.GREEN_BRIGHT + "        XX            OO    OO      XX       XX      OO    OO      XX XX         OO    OO");
         System.out.println(ConsoleColors.RED_BRIGHT   + "        XX   XXX      OO    OO      XX       XX      OO    OO      XXXX          OO    OO");
         System.out.println(ConsoleColors.GREEN_BRIGHT + "        XX    XX      OO    OO      XX       XX      OO    OO      XX XX         OO    OO");
         System.out.println(ConsoleColors.RED_BRIGHT   + "        XX    XX      OO    OO      XX       XX      OO    OO      XX  XX        OO    OO");
         System.out.println(ConsoleColors.GREEN_BRIGHT + "        XX    XX       OO  OO       XX       XX       OO  OO       XX   XX        OO  OO ");
         System.out.println(ConsoleColors.RED_BRIGHT   + "         XXXXXX         OOOO        XX       XX        OOOO        XX    XX        OOOO  ");
         System.out.println();
         System.out.println();
         System.out.println(ConsoleColors.PURPLE +      "                                    Made by BE1_4(AOIH) team");
         System.out.println(ConsoleColors.CYAN_BRIGHT + "              1  2  3  4  5                                              1  2  3  4  5");
         System.out.println(ConsoleColors.YELLOW_BRIGHT+"           +-----------------+                                        +-----------------+");
         System.out.println(ConsoleColors.GREEN_BRIGHT+ "         A |  .  .  X  .  .  |           Hruby Henriett             A |  .  .  O  .  .  |");
         System.out.println(ConsoleColors.RED_BRIGHT+   "         B |  .  .  X  .  .  |           Pusztai Zoltán             B |  .  .  O  .  .  |");
         System.out.println(ConsoleColors.GREEN_BRIGHT+ "         C |  O  O  .  O  O  |           Szász Gergely              C |  X  X  .  X  X  |");
         System.out.println(ConsoleColors.RED_BRIGHT+   "         D |  .  .  X  .  .  |           Németh Zsolt               D |  .  .  O  .  .  |");
         System.out.println(ConsoleColors.GREEN_BRIGHT+ "         E |  .  .  X  .  .  |                                      E |  .  .  O  .  .  |");
         System.out.println(ConsoleColors.YELLOW_BRIGHT+"           +-----------------+                                        +-----------------+");
         System.out.println();
         System.out.println(ConsoleColors.BLUE_BRIGHT + "                                Your formidable opponent is coming!" + ConsoleColors.RESET);
         System.out.print("                                                ");
         for (int i = 5; i >= 0; i--) {
             if (i > 0) {
                 System.out.print("\b" + i);
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException ex) {
                     Thread.currentThread().interrupt();
                 }
             }  else System.out.println("\b");
         }
        }
    }