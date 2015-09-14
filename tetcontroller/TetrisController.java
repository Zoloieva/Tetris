package tetcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import tetmodel.Tetris;


public class TetrisController implements MouseListener {
    private final Tetris tetris;
    
    private class MoveAction extends AbstractAction {
        private final String action;

        private MoveAction(String action) {
            this.action = action;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( action.equals("right")) {
                tetriminoMoveRight();
            } else if ( action.equals("left")) {
                tetriminoMoveLeft();
            } else if ( action.equals("down")) {
                tetriminoMoveDown();
            } else if ( action.equals("rotate")) {
                tetriminoRotate();
            } 
        }
    }
    
    public TetrisController(Tetris tet) {
        tetris = tet;
    }
    
    public void addMoveActions(JComponent component) {
        component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        component.getActionMap().put("right", new MoveAction("right"));
        component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        component.getActionMap().put("left", new MoveAction("left"));
        component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        component.getActionMap().put("down", new MoveAction("down"));
        component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "rotate");
        component.getActionMap().put("rotate", new MoveAction("rotate"));
    }
    
    
    public void tetriminoMoveRight() {
        tetris.moveRight();
    }
    
    public void tetriminoMoveLeft() {
        tetris.moveLeft();
    }
    
    public void tetriminoMoveDown() {
        tetris.moveDown();
    }
    
    public void tetriminoRotate() {
        tetris.rotate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getPoint().getX();
        double y = e.getPoint().getY();
        if ( x > 110 && x < 290 ) {
            if ( y > 170 && y < 220 ) {
                tetris.newGame();
            } else if ( y > 230 && y < 280 ) {
                //here will be menu panel
            } else if ( y > 290 && y < 340 ) {
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
