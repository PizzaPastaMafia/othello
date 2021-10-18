import javax.swing.Jbutton;
import javax.swing.ImageIcon;


public class cella extends JButton{
    ImageIcon P, V; //P = pieno; V = vuoto
    byte value = 0;
    /*
    0 = NUIE
    1 = p
    2 = v
    */
    
    public cella(){
        P = new ImageIcon("P.png");
        V = new ImageIcon("V.png");

    }
}