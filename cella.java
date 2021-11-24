import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.*;


public class cella extends JButton{
    public static ImageIcon P, V; //P = pieno; V = vuoto
    public static JLabel mossaPossibile = new JLabel("X", SwingConstants.CENTER); //qui la dichiaro
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
        mossaPossibile.setFont(new Font("Arial", Font.BOLD, 25));// e qui setto lo stile
        mossaPossibile.setForeground(Color.RED);// e qui setto lo stile hai capito? no
    }

    public void setLabel(){ //questa è setlabel
        this.add(mossaPossibile);//aggiungo alla cella il label mossa possibile

    }
}