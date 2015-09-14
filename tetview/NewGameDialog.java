
package tetview;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;


public class NewGameDialog extends JPanel {
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(100, 100, 200, 250);
        g.setColor(Color.BLUE);
        g.fillRect(102, 102, 196, 246);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.BOLD, 30));
        g.drawString("YOU LOSE!", 120, 150);
        g.drawRect(110, 170, 180, 50);
        g.drawRect(110, 230, 180, 50);
        g.drawRect(110, 290, 180, 50);
        g.drawString("NEW GAME", 115, 210);
        g.drawString("MENU", 150, 270);
        g.drawString("EXIT", 160, 330);
    }
}
