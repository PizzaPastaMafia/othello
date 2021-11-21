import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.*;


public class cella extends JButton{
    public static ImageIcon P, V; //P = pieno; V = vuoto
    byte value = 1;
    /*
    0 = NUIE
    1 = P
    2 = V
    */
    
    public cella(){
        P = new ImageIcon(this.getClass().getResource("P.png"));
        V = new ImageIcon(this.getClass().getResource("V.png"));
        setBackground(new Color(0, 188, 140));
    }
}