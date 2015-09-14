package tetview;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import tetmodel.Tetris;
import tetcontroller.TetrisController;

public class TetrisView extends JFrame implements Observer {
    public static final int BLOCK_SIZE = 20;
    private final JLayeredPane layers;
    private final TetriminoView tetriminoView;
    private final BackView back;
    private final NewGameDialog ngd;
    private final TetrisController controller;
    private static final Color[] shapeColors = {new Color(255, 87, 87), new Color(94, 147, 226), new Color(87, 255, 171), new Color(109, 255, 87), new Color(255, 250, 87), new Color(255, 188, 87), new Color(255, 87, 228)};
    
    public TetrisView(Tetris tetris, TetrisController control) {
        this.controller = control;

        layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(420, 480));

        back = new BackView(tetris.getBoard().getCoordinates(), tetris.getNextShape());
        back.setSize(layers.getPreferredSize());
        
        tetriminoView = new TetriminoView(tetris.getTetrimino());
        controller.addMoveActions(tetriminoView);
        tetriminoView.setSize(layers.getPreferredSize());
        tetriminoView.setOpaque(false);
        
        layers.add(back, 0, 0);
        layers.add(tetriminoView, 1, 0);
        this.add(layers);
        
        ngd = new NewGameDialog();
        this.setGlassPane(ngd);
        ngd.setOpaque(false);
        ngd.addMouseListener(controller);

        this.setPreferredSize(layers.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void endGameMenu() {
        ngd.setVisible(true);
    }
    
    public static Color defineShapeColor(int shape) {
        return shapeColors[shape-1];
    }
    
    private void newTetrimino(Tetris tetris) {
        tetriminoView.setColor(tetris.getTetrimino().getShape());
        updateTetrimino(tetris);
    }
    
    private void updateTetrimino(Tetris tetris) {
        tetriminoView.setCoord(tetris.getTetrimino().getCoordinates());
        tetriminoView.repaint();
    }

    private void updateBack(Tetris tetris) {
        back.setBoard(tetris.getBoard().getCoordinates());
        back.setLines(tetris.getLines());
        back.setLevel(tetris.getLevel());
        back.setNextTetrimino(tetris.getNextShape());
        back.setNextColor(tetris.getNextShape());
        back.repaint();
    }

    private void newGame(Tetris tetris) {
        ngd.setVisible(false);
        updateBack(tetris);
        newTetrimino(tetris);
    }

    @Override
    public void update(Observable o, Object arg) {
        Tetris tetris = (Tetris)o;
        if ( Tetris.FIGURE_UPDATED.equals((String)arg) ) {
            updateTetrimino(tetris);
        } else if ( Tetris.BOARD_UPDATED.equals((String)arg) ) {
            updateBack(tetris);
        } else if ( Tetris.NEW_FIGURE.equals((String)arg) ) {
            updateBack(tetris);
            newTetrimino(tetris);
        } else if ( Tetris.END_OF_GAME.equals((String)arg) ) {
            endGameMenu();
        } else if ( Tetris.NEW_GAME.equals((String)arg) ) {
            newGame(tetris);
        }
    }
}
