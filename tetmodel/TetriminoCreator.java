package tetmodel;

import java.util.Random;

public class TetriminoCreator {
    public static final int O_SHAPE = 1;
    public static final int I_SHAPE = 2;
    public static final int S_SHAPE = 3;
    public static final int Z_SHAPE = 4;
    public static final int J_SHAPE = 5;
    public static final int L_SHAPE = 6;
    public static final int T_SHAPE = 7;
    private static final int[][][] shapesCoords = {{{-1, -1}, {0, -1}, {-1, 0}, {0, 0}}, 
                                                  {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
                                                  {{-1, -1}, {0, -1}, {0, 0}, {1, 0}},
                                                  {{0, -1}, {1, -1}, {-1, 0}, {0, 0}},
                                                  {{-1, 0}, {0, 0}, {1, 0}, {-1, 1}},
                                                  {{-1, -1}, {-1, 0}, {0, 0}, {1, 0}},
                                                  {{0, -1}, {-1, 0}, {0, 0}, {0, 1}}};
    
    public static int getCenterX(int shape) {
        if ( shape == I_SHAPE ) {
            return 4;
        }
        return 5;
    }
    
    public static int getCenterY(int shape) {
        if ( shape == I_SHAPE ) {
            return 0;
        }
        return 1;
    }
    
    public static int[][] getCoordinates(int shape) {
        int[][] coords = new int[Tetrimino.COORD_LENGTH][Tetrimino.COORD_WIDTH];
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            coords[i] = shapesCoords[shape-1][i].clone();
        }
        return coords;
    }

    public static int randomShape() {
        return new Random().nextInt(6) + 1;
    }
}
