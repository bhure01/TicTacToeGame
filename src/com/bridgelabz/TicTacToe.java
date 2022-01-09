package com.bridgelabz;
import java.util.Scanner;

public class TicTacToe {

    private static char[] boardArray = new char[10];      //playing board
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

    private static void getUserInput(int i) {
        System.out.println("Player " + i + " Enter the Position for Your Symbol :");
        int position = scan.nextInt();
        if (i % 2 == 0) {
            boardArray[position] = 'O';
        } else {
            boardArray[position] = 'X';
        }
    }

    private static boolean checkFreePosition(int enteredPosition) {
        if(enteredPosition < 1 || enteredPosition > 9) {
            System.out.println("Please Enter the Position between 1 to 9 only.");
            return false;
        } else if(boardArray[enteredPosition] != '_') {
            System.out.println("Entered Location Contain Symbol. Please Enter Another Location.");
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        initiateBoard();
        playerChoice();
        showBoard();
        for(int i=1;i<=10;i++) {
            getUserInput((i%2)+1);
            showBoard();
        }
    }
}
