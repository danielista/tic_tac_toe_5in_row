package tictacmartinek;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MojMain {

    static int rows = 15;
    static int columns = 15;
    static int[] numbersOnTiles = new int[226];
    static int[][] boardik = new int[rows][columns];
    static int counter = rows*columns;

    static boolean player1 = true;
    static boolean gameEnded = false;



    public static void main(String[] args) {

        setBoard();


        Scanner in = new Scanner(System.in);
        System.out.println("Let's play Tic Tac Toe!");
        System.out.print("Player 1, what is your name? ");
        String p1 = in.nextLine();
        System.out.print("Player 2, what is your name? ");
        String p2 = in.nextLine();


        while(!gameEnded) {

            //Draw the board
            printBoard();

            //Print whose turn it is
            if(player1) {
                System.out.println(p1 + "'s Turn (888):");
            } else {
                System.out.println(p2 + "'s Turn (555):");
            }

            //Create a char variable that stores either 555 or 888 based on what player's turn it is
            //char c = '-';
            int c = 555;
            if(player1) {
                c = 888;
            }

            //Create row and col variables which represent indexes that correspond to a position on our board
            // u mňa to je len číslo jedno
            int desiredNumber;

            //Only break out of the while loop once the user enters a valid position
            while(true) {

                //Ask the user for what position they want to place their x or o
                System.out.print("Enter a number of desired CELL: ");
                desiredNumber = in.nextInt();

                //Check if the row and col are 0, 1, or 2
                if(desiredNumber < 1 || (desiredNumber > 225 && desiredNumber < 555)) {
                    System.out.println("This position is off the bounds of the board! Try again.");

                    //Check if the position on the board the user entered is empty (has a -) or not
                } else if(desiredNumber == 555 || desiredNumber == 888) {
                    System.out.println("Someone has already made a move at this position! Try again.");

                    //Otherwise, the position is valid so break out of the while loop
                } else {
                    break;
                }

            }

            //Set the position on the board at row, col to desiredNumber
            for(var i=0;i<rows;i++) {
                for (var j=0; j<columns; j++) {
                    if (boardik[i][j] == desiredNumber) boardik[i][j] = c;
                }
            }


            //Check to see if either player has won
            if(playerHasWon(boardik) == 888) {
                System.out.println("---------------------------------------------");
                System.out.println(p1 + " has won!");
                gameEnded = true;
            } else if(playerHasWon(boardik) == 555) {
                System.out.println("---------------------------------------------");
                System.out.println(p2 + " has won!");
                gameEnded = true;
            } else {

                //If neither player has won, check to see if there has been a tie (if the board is full)
                if(boardIsFull(boardik)) {
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {
                    //If player1 is true, make it false, and vice versa; this way, the players alternate each turn
                    player1 = !player1;
                }
            }
        }

        //Draw the board at the end of the game
        //drawBoard(board);
        printBoard();
    }


    // adding numbers to boards tiles
    public static void setBoard(){
        for(int j = 1; j<226; j++){
            numbersOnTiles[j] = counter;
            if (counter>1) counter--;
        }

        for(int i = 0; i<rows; i++)
            for(int j = 0; j<columns; j++){
                boardik[i][j] = numbersOnTiles[counter];
                counter++;
            }
    }

    public static void printBoard(){
        for(var i=0;i<rows;i++) {
            for (var j=0; j<columns; j++) {
                if (boardik[i][j]>=100) System.out.print(boardik[i][j] + " ");
                else if (boardik[i][j]>9) System.out.print(boardik[i][j] + "  ");
                else  System.out.print(boardik[i][j] +     "   ");

            }
            System.out.println();
        }
    }


    public static int playerHasWon(int[][] m){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                //checking rows
                if(j<=10 && m[i][j]+m[i][j+1]+m[i][j+2]+m[i][j+3]+m[i][j+4]==2775)
                    return m[i][j];
                if(j<=10 && m[i][j]+m[i][j+1]+m[i][j+2]+m[i][j+3]+m[i][j+4]==4440)
                    return m[i][j];

                //checking collumns
                if(i<=10 && m[i][j]+m[i+1][j]+m[i+2][j]+m[i+3][j]+m[i+4][j]==2775)
                    return m[i][j];
                if(i<=10 && m[i][j]+m[i+1][j]+m[i+2][j]+m[i+3][j]+m[i+4][j]==4440)
                    return m[i][j];

                //diagonales
                if(j<=10 && i<=10 && m[i][j]+m[i+1][j+1]+m[i+2][j+2]+m[i+3][j+3]+m[i+4][j+4]==2775)
                    return m[i][j];
                if(j<=10 && i<=10 && m[i][j]+m[i+1][j+1]+m[i+2][j+2]+m[i+3][j+3]+m[i+4][j+4]==4440)
                    return m[i][j];

                //another diagonale
                if(j>=4 && i<=10 && m[i][j]+m[i+1][j-1]+m[i+2][j-2]+m[i+3][j-3]+m[i+4][j-4]==2775)
                    return m[i][j];
                if(j>=4 && i<=10 && m[i][j]+m[i+1][j-1]+m[i+2][j-2]+m[i+3][j-3]+m[i+4][j-4]==4440)
                    return m[i][j];
            }
        }
        //Otherwise nobody has not won yet
        return 0;
    }

    //Make a function to see if someone has won and return the winning char
    public static int aa(int[][] board) {

        //Check each row
        for(int i = 0; i < columns; i++) {
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return board[i][0];
            }
        }

        //Check each column
        for(int j = 0; j < 3; j++) {
            if(board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
                return board[0][j];
            }
        }

        //Check the diagonals
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }
        if(board[2][0] == board[1][1] && board[1][1] ==  board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }

        //Otherwise nobody has not won yet
        return ' ';

    }

    //Make a function to check if all of the positions on the board have been filled
    public static boolean boardIsFull(int[][] board) {

        for(var i=0;i<rows;i++) {
            for (var j=0; j<columns; j++) {
                if((board[i][j] < 226) && (board[i][j] > 0) ) {
                    return false;
                }
            }
        }
        return true;
    }


}