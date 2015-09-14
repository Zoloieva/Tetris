
package tetview;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetmodel.Tetrimino;
import tetmodel.TetriminoCreator;
import static tetmodel.Board.BOARD_HEIGHT;
import static tetmodel.Board.BOARD_WIDTH;
import static tetview.TetrisView.BLOCK_SIZE;

public class BackView extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(40, 20, 100);
    private static final int NEXT_Y_SHIFT = 80;
    private static final int NEXT_X_SHIFT = 220;
    private int[][] board = new int[BOARD_HEIGHT][BOARD_WIDTH];
    private int[][] nextTetrimino;
    private String lines;
    private String level;
    private Color nextColor;
    
    public BackView(int[][] board, int nextShape){
        lines = "0";
        level = "1";
        nextColor = TetrisView.defineShapeColor(nextShape);
        setBoard(board);
        setNextTetrimino(nextShape);
    }
    
    public void setLevel(int n) {
        level = String.valueOf(n);
    }
    
    public void setLines(int n) {
        lines = String.valueOf(n);
    }
    
    public void setNextColor(int nextShape) {
        nextColor = TetrisView.defineShapeColor(nextShape);
    }
    
    public final void setNextTetrimino(int nextShape) {
        nextTetrimino = TetriminoCreator.getCoordinates(nextShape);
        int x = TetriminoCreator.getCenterX(nextShape);
        int y = TetriminoCreator.getCenterY(nextShape);
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            nextTetrimino[i][0] = ( nextTetrimino[i][0] + y) * BLOCK_SIZE + NEXT_Y_SHIFT;
            nextTetrimino[i][1] = ( nextTetrimino[i][1] + x) * BLOCK_SIZE + NEXT_X_SHIFT;
        }
    }
    
    public final void setBoard(int[][] board) {
        this.board = board;
    }
    
    @Override
    public void paintComponent(Graphics g){
        //Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        this.setBackground(BACKGROUND_COLOR);

        g.setColor(Color.BLACK);
        g.fillRect(BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * BOARD_HEIGHT);
        g.fillRect(260, 20, 120, 160);
        
        g.setColor(Color.WHITE);
        g.drawRect(BLOCK_SIZE-1, BLOCK_SIZE-1, BLOCK_SIZE * BOARD_WIDTH + 1, BLOCK_SIZE * BOARD_HEIGHT + 1);
        g.drawRect(260, 20, 120, 160);
        
        g.setColor(nextColor);
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            g.fillRect(nextTetrimino[i][1], nextTetrimino[i][0], BLOCK_SIZE, BLOCK_SIZE);
        }
        
        for ( int i = 0; i < 20; i++ ) {
            for ( int j = 0; j < 10; j++ ) {
                if ( board[i][j] != 0 ) {
                    int x = j * BLOCK_SIZE + BLOCK_SIZE;
                    int y = i * BLOCK_SIZE + BLOCK_SIZE;
                    g.setColor(TetrisView.defineShapeColor(board[i][j]));
                    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
        g.setColor(Color.white);
        g.setFont(new Font("Courier", Font.BOLD, 26));
        g.drawString("NEXT", 280, 50);
        g.drawString("LINES", 260, 250);
        g.drawString(lines, 280, 290);
        g.drawString("LEVEL", 260, 330);
        g.drawString(level, 280, 370);
    }
}