package gr.aueb.cf.project4;

public class TrilizaGame {

    public static void main(String[] args) {
        char[][] board = new char[3][3];
        startingBoard(board);
        printBoard(board);
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
}
