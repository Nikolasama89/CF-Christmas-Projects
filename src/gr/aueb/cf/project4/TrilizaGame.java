package gr.aueb.cf.project4;

import java.util.Scanner;

public class TrilizaGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        startingBoard(board);
        printBoard(board);
        char player1 = playersSymbolPick(scanner);
    }

    public static void startingBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char playersSymbolPick(Scanner scanner) {
        char symbol;

        while (true) {
            System.out.println("Please pick X or O: ");
            symbol = scanner.next().toUpperCase().charAt(0);

            if (symbol == 'X' || symbol == 'O') {
                break;
            } else {
                System.out.println("Please pick a valid option!");
            }
        }
        System.out.println("Player 1 picks: " + symbol);
        return symbol;
    }

}
