package com.bridgelabz;
import java.util.Scanner;

public class TicTacToe {

    private static char[] boardArray = new char[10];
    private static char player1Choice;
    private static char player2Choice;

    static Scanner scan = new Scanner(System.in);

    public static void initiateBoard() {                                // board initialization -uc1
        for (int i = 1; i < 10; i++) {
            boardArray[i] = ' ';
        }
    }

    public static void playerChoice() {                                  //getting user inputs - uc2
        System.out.println("Enter Player 1 choice (X / O) :");
        char choice = scan.next().toUpperCase().charAt(0);
        if (choice == 'X') {
            player1Choice = choice;
            player2Choice = 'O';
        } else if (choice == 'O') {
            player1Choice = choice;
            player2Choice = 'X';
        } else {
            System.out.println("Wrong Choice Entered.");
            playerChoice();
        }
    }

    public static void showBoard() {                            //player move on board to select valid empty cell-uc3
        System.out.println(" ----------- ");
        System.out.println("| " + boardArray[1] + " | " + boardArray[2] + " | " + boardArray[3] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + boardArray[4] + " | " + boardArray[5] + " | " + boardArray[6] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + boardArray[7] + " | " + boardArray[8] + " | " + boardArray[9] + " |");
        System.out.println(" ----------- ");
    }

    public static void main(String[] args) {
        initiateBoard();
        playerChoice();
        showBoard();
    }
}
