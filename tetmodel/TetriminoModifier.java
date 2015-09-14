package tetmodel;

import static tetmodel.Board.BOARD_HEIGHT;
import static tetmodel.Board.BOARD_WIDTH;

public class TetriminoModifier {
    private static final int X = 1;
    private static final int Y = 0;
    private static int[][] tempCoord = new int[Tetrimino.COORD_LENGTH][Tetrimino.COORD_WIDTH];
        
    private static void coordsRotation( int[][] coords, int x, int y ) {  
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            int temp = coords[i][1];
            coords[i][1] = (coords[i][0] - y) * (-1) + x;
            coords[i][0] = (temp - x) + y;
        }
    }
    
    private static void setTempCoordinates(int coords[][]) {
        tempCoord = coords;        
    }
    
    private static boolean outOfLimits() {
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            if ( tempCoord[i][0] < 0 || tempCoord[i][0] >= BOARD_HEIGHT || tempCoord[i][1] < 0 || tempCoord[i][1] >= BOARD_WIDTH) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean tooLow() {
        for ( int i = 0; i < 4; i++ ) {
            if ( tempCoord[i][0] >= BOARD_HEIGHT ) {
                return true;
            }
         }
         return false;
    }
       
    private static void modifyTempCoordinates( int index, int shift ) {
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            tempCoord[i][index] += shift;
        }
    }
    
    public static boolean movingDown(int coords[][], Board board) {
        setTempCoordinates(coords);
        modifyTempCoordinates(Y, 1);
        return !tooLow() && board.isFreeSpace(tempCoord);
    }
    
    public static boolean movingRight( int coords[][], Board board ) {
        setTempCoordinates(coords);
        modifyTempCoordinates(X, 1);
        return !outOfLimits() && board.isFreeSpace(tempCoord);
    }
     
    public static boolean movingLeft(int coords[][], Board board) {
        setTempCoordinates(coords);
        modifyTempCoordinates(X, -1);
        return !outOfLimits() && board.isFreeSpace(tempCoord);
    }
    
    public static boolean rotate(int coords[][], int centerXCoord, int centerYCoord, Board board) {
        setTempCoordinates(coords);
        coordsRotation(tempCoord, centerXCoord, centerYCoord);
        return !outOfLimits() && board.isFreeSpace(tempCoord);
    }
}
