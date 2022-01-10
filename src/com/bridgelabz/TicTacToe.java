package com.bridgelabz;

import java.util.*;

public class TicTacToe {

    private static char[] playingBoard;
    private static char player1Sybmol,player2Symbol;
    private static int player1Play = 1;

    static Scanner sc;
    private static void initializeGame() {
        playingBoard = new char[10];
        player1Sybmol = ' ';
        player2Symbol = ' ';
        initializeBoard();
    }

    private static void initScanner() {                                       // Method to Initialize Scanner Object
        sc = new Scanner(System.in);
    }

    private static void initializeBoard() {                                  // UC-1 Method for initializing board position
        for(int i = 1; i < 10; i++) {
            playingBoard[i] = '_';
        }
    }

    private static void showBoard() {                                       // UC-3 Showing the Board Elements
        for (int i = 1; i < 10; i++) {
            if ((i) % 3 == 0) {
                System.out.println(playingBoard[i]);
                if (i != 10 - 1) {
                    System.out.println("-----");
                }
            } else {
                System.out.print(playingBoard[i] + "|");
            }
        }
    }


    private static void setPlayingSymbol() {                                 // UC 2 Method for setting Input Symbol for User
        while(player1Sybmol != 'X' && player1Sybmol != 'O') {
            System.out.println("Player 1 Please Select the Symbol for Play: X or O");
            initScanner();                                             // Scanner Object Initilize Method for User Input
            player1Sybmol = sc.next().toUpperCase().charAt(0);
            switch (player1Sybmol) {
                case 'X':
                    player2Symbol = 'O';
                    System.out.println("Player 1 Selected " + player1Sybmol + " Symbol");
                    System.out.println("Player 2 Selected " + player2Symbol + " Symbol");
                    break;
                case 'O':
                    player2Symbol = 'X';
                    System.out.println("Player 1 Selected " + player1Sybmol + " Symbol");
                    System.out.println("Player Selected " + player2Symbol + " Symbol");
                    break;
                default:
                    System.out.println("Please Select the Symbol From X or O only");
                    break;
            }
        }
    }

    private static void setPlayChance() {                                               // Method for Setting Player Turn
        if(player1Play == 1) {
            player1Play = 0;
        } else {
            player1Play = 1;
        }
    }

    private static void playGame() {                                                 // UC-4 Get User Input and Move on Board
        if(player1Play == 1) {
            getSymbol(1,player1Sybmol);
        } else {
            getSymbol(2,player2Symbol);
        }
        setPlayChance();
    }

    private static void getSymbol(int playerNo,char symbol) {                        // Methode for Getting Input From Player
        initScanner();
        int playerPosition;
        System.out.println("Player "+playerNo+" Please Enter the Position for Your Play:");
        playerPosition = sc.nextInt();
        if(checkFreePosition(playerPosition)) {
            playingBoard[playerPosition] = symbol;
        } else {
            playGame();
            setPlayChance();
        }
    }

    private static boolean checkFreePosition(int enteredPosition) {                // Uc-5 Check for Free Space and Make Move
        if(enteredPosition < 1 || enteredPosition > 9) {
            System.out.println("Please Enter the Position between 1 to 9 only");
            return false;
        } else if(playingBoard[enteredPosition] != '_') {
            System.out.println("Entered Location Contain Symbol. Please Enter Another Location");
            return false;
        } else {
            return true;
        }
    }

    private static void flipToss() {                                                 // Uc-6 Make Toss for Player Chance
        Random tossValue = new Random();
        int toss = tossValue.nextInt(2)+1;
        if(toss == 1) {
            player1Play = 1;
            System.out.println("Player 1 Won the Toss");
        } else {
            player1Play = 0;
            System.out.println("Player 2 Won the Toss");
        }
    }

    private static boolean checkWin() {                                                // UC-7 Check for Winner or Draw
        if(checkDraw()) {
            System.out.println("The Game is DRAW. As there is no any location for Player Symbol");
            showBoard();
            return true;
        } else {
            if(checkDiagonal() || checkColumnWin() || checkRowWin()) {
                if(player1Play == 0) {
                    System.out.println("Player 1 WON THE GAME!");
                } else {
                    System.out.println("Player 2 WON THE GAME!");
                }
                showBoard();
                return true;
            }
        }
        return false;
    }

    private static boolean checkDraw() {                                                  // Method to check for Game Draw
        boolean flag = true;
        for(int i = 1; i <= 9; i++) {
            if(playingBoard[i] == '_') {
                flag = false;
            }
        }
        return flag;
    }

    private static boolean checkDiagonal() {                                         // Method to check for Diagonal Win
        if(!(playingBoard[1] == '_') && playingBoard[1] == playingBoard[5] && playingBoard[1] == playingBoard[9]
                || !(playingBoard[3] == '_') && playingBoard[3] == playingBoard[5] && playingBoard[3] == playingBoard[7]) {
            return true;
        }
        return false;
    }

    private static boolean checkRowWin() {                                               // Method to Check for Row Win
        for( int i = 1; i < playingBoard.length; i += 3) {
            if(!(playingBoard[i] == '_') && playingBoard[i] == playingBoard[i+1] && playingBoard[i] == playingBoard[i+2]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumnWin() {                                          // Method to Check for Column Win
        for(int i = 1; i <= 3; i++) {
            if(!(playingBoard[i] == '_') && playingBoard[i] == playingBoard[i+3] && playingBoard[i] == playingBoard[i+6]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe Game");

        char userChoice;                                                                  // Play the Game till Win Or Draw
        do {
            initializeGame();                                                             // Set The New Game
            setPlayingSymbol();                                                           // Setting the Symbol for Play
            showBoard();                                                                  // Showing the Initial Board
            flipToss();                                                                   // Flip the Toss for Player Play Chance
            while(!checkWin()) {
                playGame();
                showBoard();
            }
            System.out.println("Do you want to Play Again? (Y/N) :");
            userChoice = sc.next().toUpperCase().charAt(0);
        }while(userChoice == 'Y');
    }
}