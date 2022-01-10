package com.bridgelabz;

import java.util.*;

public class TicTacToe {

    private static char[] playingBoard = new char[10];
    private static char player1Sybmol, player2Symbol;
    private static int player1Play = 1;

    static Scanner sc;

    private static void initializeScanner() {
        sc = new Scanner(System.in);
    }

    private static void initializeBoard() {
        for(int i = 1; i < 10; i++) {
            playingBoard[i] = '_';
        }
    }

    private static void setPlayingSymbol() {
        while(player1Sybmol != 'X' && player1Sybmol != 'O') {
            System.out.println("Player 1, please Select the Symbol for Play:- X or O");
            initializeScanner();
            player1Sybmol = sc.next().charAt(0);
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

    private static void showBoard() {
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

    private static void setPlayChance() {
        if(player1Play == 1) {
            player1Play = 0;
        } else {
            player1Play = 1;
        }
    }

    private static void playGame() {
        if(player1Play == 1) {
            getUseInput(1,player1Sybmol);
        } else {
            getUseInput(2,player2Symbol);
        }
        setPlayChance();
    }

    private static void getUseInput(int playerNo,char symbol) {
        initializeScanner();
        int playerPosition;
        System.out.println("Player "+playerNo+" Please Enter the Position for Your Play :");
        playerPosition = sc.nextInt();
        if(checkFreePosition(playerPosition)) {
            playingBoard[playerPosition] = symbol;
        } else {
            playGame();
            setPlayChance();
        }
    }

    private static boolean checkFreePosition(int enteredPosition) {
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

    private static void flipToss() {
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

    private static boolean checkWin() {
        if(checkDraw()) {
            System.out.println("The Game is Draw as there is no any location for Player Symbol");
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

    private static boolean checkDraw() {
        boolean flag = true;
        for(int i = 1; i <= 9; i++) {
            if(playingBoard[i] == '_') {
                flag = false;
            }
        }
        return flag;
    }

    private static boolean checkDiagonal() {
        if(!(playingBoard[1] == '_') && playingBoard[1] == playingBoard[5] && playingBoard[1] == playingBoard[9]
                || !(playingBoard[3] == '_') && playingBoard[3] == playingBoard[5] && playingBoard[3] == playingBoard[7]) {
            return true;
        }
        return false;
    }

    private static boolean checkRowWin() {
        for( int i = 1; i < playingBoard.length; i += 3) {
            if(!(playingBoard[i] == '_') && playingBoard[i] == playingBoard[i+1] && playingBoard[i] == playingBoard[i+2]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumnWin() {
        for(int i = 1; i <= 3; i++) {
            if(!(playingBoard[i] == '_') && playingBoard[i] == playingBoard[i+3] && playingBoard[i] == playingBoard[i+6]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe Game");
        initializeBoard();
        setPlayingSymbol();
        showBoard();
        flipToss();
        while(!checkWin()) {
            playGame();
            showBoard();
        }
    }
}
