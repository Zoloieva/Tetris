package tetmodel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Timer;

public class Tetris extends Observable implements ActionListener  {
    private Tetrimino tetrimino;
    private Board board;
    private int nextShape;
    private final Timer timer;
    private int level;
    private int lines;
    public static String NEW_FIGURE = "new figure";
    public static String FIGURE_UPDATED = "figure updated";
    public static String BOARD_UPDATED = "board updated";
    public static String END_OF_GAME = "end of game";
    public static String NEW_GAME = "new game";
    
    public Tetris() {
        lines = 0;
        level = 1;
        timer = new Timer(1000, this);
        timer.setInitialDelay(1000);
        board = new Board();
        nextShape = TetriminoCreator.randomShape();
        tetrimino = new Tetrimino(TetriminoCreator.randomShape());
        tetrimino.setMoving(true);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveDown();
    }
    
    public Board getBoard() {
        return board;
    }
    
    public int getLines() {
        return lines;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getNextShape() {
        return nextShape;
    }
    
    public Tetrimino getTetrimino() {
        return tetrimino;
    }
        
    public void moveRight() {
        if ( tetrimino.isMoving() ) {
            if ( TetriminoModifier.movingRight(tetrimino.getCoordinates(), board) ) {
                tetrimino.moveRight();
                setChanged();
                notifyObservers(FIGURE_UPDATED);
            }
        }
    }

    public void moveLeft() {
        if ( tetrimino.isMoving() ) {
            if ( TetriminoModifier.movingLeft(tetrimino.getCoordinates(), board) ) {
                tetrimino.moveLeft();
                setChanged();
                notifyObservers(FIGURE_UPDATED);
            }
        }
    }

    public void moveDown() {
        if ( tetrimino.isMoving() ) {
            if ( TetriminoModifier.movingDown(tetrimino.getCoordinates(), board) ) {
                tetrimino.moveDown();
                setChanged();
                notifyObservers(FIGURE_UPDATED);
            } else {
                tetrimino.setMoving(false);
                timer.stop();
                board.addTetrimino(tetrimino);
                setChanged();
                notifyObservers(BOARD_UPDATED);
                checkFullRows();
                newFig();
            }
        }
    }
    
    private void checkFullRows() {
        int[][] tempCoord = tetrimino.getCoordinates();
        int minY = tempCoord[0][0];
        int maxY = minY;
        for ( int i = 1; i < Tetrimino.COORD_LENGTH; i++ ) {
            int temp = tempCoord[i][0];
            if ( temp > maxY ) {
                maxY = temp;
            } else if ( temp < minY ) {
                minY = temp;
            }
        }
        
        for ( int i = minY; i <= maxY; i++ ) {
            if ( board.isRowComplete(i) ) {
                board.eraseRow(i);
                addLine();
                setChanged();
                notifyObservers(BOARD_UPDATED);
            }
        }       
    }
    
    public void rotate() {
        if ( tetrimino.isMoving() ) {
            if ( TetriminoModifier.rotate(tetrimino.getCoordinates(), tetrimino.getCenterXCoord(), tetrimino.getCenterYCoord(), board) ) {
                tetrimino.rotate();
                setChanged();
                notifyObservers(FIGURE_UPDATED);
            }
        }
    }
        
    private void endOfGame() {
        setChanged();
        notifyObservers(END_OF_GAME);
    }
    
    private void newFig() {
        tetrimino = new Tetrimino(nextShape);
        nextShape = TetriminoCreator.randomShape();
        if ( board.isFreeSpace(tetrimino.getCoordinates()) ) {
            tetrimino.setMoving(true);
            setChanged();
            notifyObservers(NEW_FIGURE);
            timer.start();
        } else {
            endOfGame();
        }
    }
    
    public void newGame() {
        board = new Board();
        tetrimino = new Tetrimino(TetriminoCreator.randomShape());
        tetrimino.setMoving(true);
        lines = 0;
        level = 1;
        setChanged();
        notifyObservers(NEW_GAME);
        timer.setDelay(1000);
        timer.start();
    }
    
    private void riseLevel() {
        level += 1;
        if ( level < 10 ) {
            timer.setDelay(1000 - level*100);
            timer.setInitialDelay(1000 - level*100);
        }
    }

    private void addLine() {
        lines += 1;
        if ( lines % 10 == 0 ) {
            riseLevel();
        }
    }
}
