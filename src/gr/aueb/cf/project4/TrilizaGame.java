package gr.aueb.cf.project4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TrilizaGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        startingBoard(board);

        System.out.println("Welcome to a Classic game of Triliza!!");
        char player1 = playersSymbolPick(scanner);
        char player2 = (player1 == 'X') ? 'O' : 'X';

        System.out.println("Player One picks: " + player1);
        System.out.println("Player Two takes: " + player2);

        while (true) {

            printBoard(board);
            System.out.println("Player One turn : ");
            playerMove(scanner, board, player1);
            if (isWin(board, player1)) {
                printBoard(board);
                System.out.println("Player One Wins");
                break;
            }

            if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("GAME IS A TIE!");
                break;
            }

            printBoard(board);
            System.out.println("Player Two turn : ");
            playerMove(scanner, board, player2);
            if (isWin(board, player2)) {
                printBoard(board);
                System.out.println("Player Two Wins");
                break;
            }
        }
    }

    /**
     * Initializes the board
     *
     * @param board 3x3 board game initialization
     */
    public static void startingBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    /**
     * Displays the state of the board
     *
     * @param board board to display
     */
    public static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Asks User to pick their symbol
     *
     * @param scanner   For reading input
     * @return  the choice of the symbol
     */
    public static char playersSymbolPick(Scanner scanner) {
        char symbol;

        while (true) {
            System.out.println("Please pick X or O: ");
            symbol = scanner.next().toUpperCase().charAt(0);

            if (symbol == 'X' || symbol == 'O') {
                break;
            } else {
                System.out.println("Pick a valid option!");
            }
        }
        return symbol;
    }

    /**
     *Validates player move and updates the board
     *
     * @param scanner   for reading player input
     * @param board     the game board
     * @param symbol    player symbol
     */
    public static void playerMove(Scanner scanner, char[][] board, char symbol) {

        while (true) {
            try {
                System.out.println("Please select row and column for your next move (from 1-3)");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    if (board[row][col] == '-') {
                        board[row][col] = symbol;
                        break;
                    } else {
                        System.out.println("Spot is already taken. Choose an other one");
                    }
                } else {
                    System.out.println("Invalid input. Choose row and column from 1-3!");
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("Please Enter numbers only");
                scanner.nextLine();
            }
        }
    }

    /**
     * Checks if the specified player has won
     *
     * @param board     Game board
     * @param player    The player symbol to check for X or O
     * @return          True if the player has won
     */
    public static boolean isWin ( char[][] board, char player){
        for (int i = 0; i < 3; i++) {
            //checks rows
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            //checks columns
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // checks diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    /**
     * Checks if the board is full and used for checking if we have a tie
     *
     * @param board     the game board to check
     * @return          true if the board is full
     */
    public static boolean isBoardFull ( char[][] board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
