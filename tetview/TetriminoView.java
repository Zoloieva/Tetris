package tetview;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetmodel.Tetrimino;
import static tetview.TetrisView.BLOCK_SIZE;

class TetriminoView extends JPanel {
    private final int[][] coordinates = new int[Tetrimino.COORD_LENGTH][Tetrimino.COORD_WIDTH];
    private Color color;
    
    public TetriminoView(Tetrimino tetrimino){
        color = TetrisView.defineShapeColor(tetrimino.getShape());
        setCoord(tetrimino.getCoordinates());
    }
    
    public void setColor(int shape) {
        color = TetrisView.defineShapeColor(shape);
    }
    
    public final void setCoord( int coordinates[][] ) {
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            this.coordinates[i][0] = coordinates[i][0] * BLOCK_SIZE + BLOCK_SIZE;
            this.coordinates[i][1] = coordinates[i][1] * BLOCK_SIZE + BLOCK_SIZE;
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(new Color(0,0,0,0));
        //Graphics2D g2 = (Graphics2D) g;
        g.setColor(color);
        for ( int i = 0; i < Tetrimino.COORD_LENGTH; i++ ) {
            g.fillRect(coordinates[i][1], coordinates[i][0], BLOCK_SIZE, BLOCK_SIZE);
        }        
    }    
}
