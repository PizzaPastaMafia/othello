import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.*;

public class cella extends JButton{
    ImageIcon P, V; //P = pieno; V = vuoto
    byte value = 1;
    /*
    0 = NUIE
    1 = P
    2 = V
    */
    
    public cella(){
        P = new ImageIcon(this.getClass().getResource("P.png"));
        /*Image img = icon.getImage("P.png") ;  
        Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH ) ; */ 
        //P = new ImageIcon( newimg );
        V = new ImageIcon(this.getClass().getResource("V.png"));
        //setIcon(P);
        //setPreferredSize(5, 5);
        //setMaximumSize(5, 5);
        setBackground(new Color(0, 188, 140));
    }
}