import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

//import javafx.scene.layout.GridPane;

public class GUI{
    cella buttons[] = new cella[100];
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
        
        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());
        
        frame.getContentPane().setBackground(new Color(0, 204, 0));

        frame.setLayout(new GridLayout(10, 10, 10, 10));
        for(int i = 0; i < 100; i++){    
            buttons[i] = new cella();
            frame.add(buttons[i]);
        }
    }
}