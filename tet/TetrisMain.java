package tet;

import tetmodel.Tetris;
import tetcontroller.TetrisController;
import tetview.TetrisView;
import java.awt.EventQueue;

public class TetrisMain {
    
    public TetrisMain() {
        Tetris tetris = new Tetris();
        TetrisController tetcontrol = new TetrisController(tetris);
        TetrisView gui = new TetrisView(tetris, tetcontrol);
        tetris.addObserver(gui);
        gui.pack();
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {		
            @Override
            public void run() {
                TetrisMain tetris = new TetrisMain();
            }});
    }
}
