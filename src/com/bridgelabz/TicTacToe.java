package com.bridgelabz;

import java.sql.Array;

public class TicTacToe {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe Game");
        createBoard();
    }

    public static void createBoard() {
        char[] arr = new char[10];

        for(int i=1; i<=10; i++) {
            arr[i] = ' ';
        }
    }
}
