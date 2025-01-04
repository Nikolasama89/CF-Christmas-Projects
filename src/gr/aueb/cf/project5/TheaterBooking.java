package gr.aueb.cf.project5;


public class TheaterBooking {

    static boolean[][] theater = new boolean[30][12];

    public static void main(String[] args) {

        book('A', 2);

        book('A',2);

        cancel('A',2);

        cancel('A',2);
    }

    /**
     * Books a seat in the theater. If the seat is already booked we have an error message.
     * The row and column must be valid, and the seat must not be already booked.
     *
     * @param col the seat column A-L
     * @param row The row number (1-30)  the seat row
     */

    public static void book(char col, int row) {

        int colIndex = col - 'A';
        int rowIndex = row - 1;

        if (row < 1 || row > 30) {
            System.out.println("Invalid row! Must be between 1-30");
            return;
        }

        if (col < 'A' || col > 'L') {
            System.out.println("Invalid column! Must be between A-L");
            return;
        }

        if (theater[rowIndex][colIndex]) {
            System.out.println("Seat is already booked!");
            return;
        }

        theater[rowIndex][colIndex] = true;

        System.out.println("Seat in row: "+ row + " and column: " + col + " was booked successfully!");

    }

    /**
     * Cancels a seat booking in the theater. If the seat is not booked, we have an error message.
     * Row and column must be valid and the seat must be booked to cancel.
     *
     * @param col The seat column A-L
     * @param row The seat row
     */
    public static void cancel(char col, int row) {

        int colIndex = col - 'A';
        int rowIndex = row - 1;

        if (row < 1 || row > 30) {
            System.out.println("Invalid row! Must be between 1-30");
            return;
        }

        if (col < 'A' || col > 'L') {
            System.out.println("Invalid column! Must be between A-L");
            return;
        }

        if (!theater[rowIndex][colIndex]) {
            System.out.println("Seat is already not booked!");
            return;
        }

        theater[rowIndex][colIndex] = false;
        System.out.println("Seat in row: " + row + " and column: " + col + " was canceled successfully!");
    }
}