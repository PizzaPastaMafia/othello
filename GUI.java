import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

//import javafx.scene.layout.GridPane;

public class GUI extends othello implements ActionListener{
    JFrame frame = new JFrame();
    othello mat = new othello();
    public GUI(){
        
        frame.setTitle("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 725);
        frame.setLayout(new BorderLayout());
        
        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());
        
        //frame.getContentPane().setBackground(new Color(0, 204, 0));
        
        init();
        gioco(mat.campo);
        
        
        frame.setVisible(true);
    }

    void gioco(int m[][]) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j <m.length; j++) {
                if(m[i][j] == 1){
                    buttons[m.length*i + j].setIcon(cella.P);
                }
                if(m[i][j] == 2){
                    buttons[m.length*i + j].setIcon(cella.V);
                }
            }
        }

    }

    public void actionPerformed(ActionEvent e){
        int turn = 2;
        for (int i = 0; i < 64; i++){
            int x = (int)(i / 8);
            int y = i % 8;

            if(e.getSource() == buttons[i]){
                if(turn % 2 == 0){
                    if(mat.campo[x][y] == 3 || mat.campo[x][y] == 5){
                        buttons[i].setIcon(cella.P);
                        mat.campo[x][y] = 1;
                    }
                } else {
                    if(mat.campo[x][y] == 4 || mat.campo[x][y] == 5){
                        buttons[i].setIcon(cella.V);
                        mat.campo[x][y] = 2;                        
                    }
                }
            }
        }
    }
    
    cella buttons[] = new cella[64];
    void init(){
        JPanel campo = new JPanel();
        campo.setBackground(new Color(0, 0, 0));
        campo.setPreferredSize(new Dimension(480, 480));
        
        campo.setLayout(new GridLayout(8, 8, 4, 4));
        for(int i = 0; i < 64; i++){    
            buttons[i] = new cella();
            buttons[i].addActionListener(this);
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

        //Pscore.add(Ptext);


        score.add(Pscore);
        score.add(Vscore);
        score.add(PLscore);
        score.add(VLscore);
        

        frame.add(campo, BorderLayout.SOUTH);
        frame.add(score, BorderLayout.NORTH);
        
    }
}