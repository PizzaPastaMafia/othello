import java.awt.*;
import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

//import javafx.scene.layout.GridPane;

public class GUI extends othello{
    JFrame frame = new JFrame();
    public GUI(){
        frame.setTitle("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 725);
        frame.setLayout(new BorderLayout());
        
        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());
        
        //frame.getContentPane().setBackground(new Color(0, 204, 0));
        
        gioco();
        
        frame.setVisible(true);
    }
    
    void gioco(){
        cella buttons[] = new cella[64];
        JPanel campo = new JPanel();
        campo.setBackground(new Color(0, 0, 0));
        campo.setPreferredSize(new Dimension(480, 480));
        
        campo.setLayout(new GridLayout(8, 8, 4, 4));
        for(int i = 0; i < 64; i++){    
            buttons[i] = new cella();
            campo.add(buttons[i]);
        }
    
        JPanel score = new JPanel();
        score.setPreferredSize(new Dimension(500, 225));
        score.setBackground(new Color(35, 35, 35));

        JPanel Pscore = new JPanel();
        JPanel Vscore = new JPanel();
        JPanel PLscore = new JPanel();
        JPanel VLscore = new JPanel();

        Pscore.setLayout(null);
        Vscore.setLayout(null);
        PLscore.setLayout(null);
        VLscore.setLayout(null);

        Pscore.setBounds(100, 70, 50, 50);

        //JTextField Ptext = new JTextField();
        //Ptext.setText("lmao");

        Pscore.add(Ptext);


        score.add(Pscore);
        score.add(Vscore);
        score.add(PLscore);
        score.add(VLscore);
        

        frame.add(campo, BorderLayout.SOUTH);
        frame.add(score, BorderLayout.NORTH);
        
    }
}