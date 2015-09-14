package tetmodel;

public class Board {
    private final int[][] board; 
    public static final int BOARD_HEIGHT = 20;
    public static final int BOARD_WIDTH = 10;
    
    public Board() {
        board = new int[BOARD_HEIGHT][BOARD_WIDTH];
    }

    public int[][] getCoordinates() {
        int[][] tempBoard = new int[BOARD_HEIGHT][BOARD_WIDTH];
        for ( int i = 0; i < BOARD_HEIGHT; i++ ) {
            tempBoard[i] = board[i].clone();
        }
        return board;
    }
    
    public boolean isFreeSpace(int[][] coordinates) {
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            if ( board[coordinates[i][0]][coordinates[i][1]] != 0 ) {
                return false;
            }
        }
        return true;
    }

    public void addTetrimino(Tetrimino tetrimino) {
        int shape = tetrimino.getShape();
        int[][] tempCoord = tetrimino.getCoordinates();
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            board[tempCoord[i][0]][tempCoord[i][1]] = shape;
        }
    }

    public boolean isRowComplete(int row) {
        for ( int i = 0; i < BOARD_WIDTH; i++ ) {
            if ( board[row][i] == 0 ) {
                return false;
            }
        }
        return true;
    }

    public void eraseRow(int row) {
        for ( int i = row; i > 0; i-- ) {
            board[i] = board[i-1].clone();
        }
        for ( int i = 0; i < BOARD_WIDTH; i++ ) {
            board[0][i] = 0;
        }
    }
}
