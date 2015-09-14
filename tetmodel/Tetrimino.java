package tetmodel;

public class Tetrimino {
    
    public static final int COORD_LENGTH = 4;
    public static final int COORD_WIDTH = 2;
    private final int[][] coordinates;
    private final int shape;
    private boolean moving;
    private int centerYCoord;
    private int centerXCoord;

    public Tetrimino(int shape) {
        this.shape = shape;
        coordinates = TetriminoCreator.getCoordinates(shape);
        centerXCoord = TetriminoCreator.getCenterX(shape);
        centerYCoord = TetriminoCreator.getCenterY(shape);
        moving = false; 
    }

    public int getCenterXCoord() {
        return centerXCoord;
    }

    public int getCenterYCoord() {
        return centerYCoord;
    }

    public int[][] getCoordinates() {
        int[][] tempCoordinates = new int[COORD_LENGTH][COORD_WIDTH];
        for( int i = 0; i < COORD_LENGTH; i++ ){
            tempCoordinates[i][0] = coordinates[i][0] + centerYCoord;
            tempCoordinates[i][1] = coordinates[i][1] + centerXCoord;
        }
        return tempCoordinates;
    }

    public int getShape() {
        return shape;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

    public void moveDown() {
        centerYCoord += 1;
    }

    public void moveLeft() {
        centerXCoord -= 1;
    }

    public void moveRight() {
        centerXCoord += 1;
    }

    public void rotate() {
        for ( int i = 0; i < COORD_LENGTH; i++ ) {
            int temp = coordinates[i][1];
            coordinates[i][1] = coordinates[i][0] * (-1);
            coordinates[i][0] = (temp);
        }
    }
}
