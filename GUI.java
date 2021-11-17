import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends othello implements ActionListener{
    JFrame frame = new JFrame();
    public GUI() throws IOException{
        
        frame.setTitle("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 725);
        frame.setLayout(new BorderLayout());
        
        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());
        
        //frame.getContentPane().setBackground(new Color(0, 204, 0));
        
        init();
        gioco(campo);
        
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
        gioco(campo);

        
        for (int i = 0; i < 64; i++){
            int x = (int)(i / 8);
            int y = i % 8;
            
            if(e.getSource() == buttons[i]){
                ClearConsole();

                if(mode == 1){
                    if(moveCounter % 2 == 0){
                        if(campoConMosse[x][y] == 3){
                            buttons[i].setIcon(cella.P);
                            boolean inutile = ControlloMossa(campo, x, y, moveCounter, 1);
                            campo[x][y] = 1;
                            moveCounter++;
                        }
                    } else {
                        if(campoConMosse[x][y] == 4){
                            buttons[i].setIcon(cella.V);
                            boolean inutile = ControlloMossa(campo, x, y, moveCounter, 1);
                            campo[x][y] = 2;
                            moveCounter++;
                        }
                    }
                } else {
                    if(campoConMosse[x][y] == 3){
                        buttons[i].setIcon(cella.V);
                        boolean inutile = ControlloMossa(campo, x, y, moveCounter, 1);
                        campo[x][y] = 1;
                        moveCounter++;
                        routineBot(campo, campoConMosse, moveCounter);
                        moveCounter++;
                    }
                    
                }



            }
            controlloMosse(campoConMosse, campo, moveCounter);
            stampaCampo(campoConMosse);
            gioco(campo);
            
        }
    }
    
    cella buttons[] = new cella[64];
    void init() throws IOException{
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
        score.setLayout(new GridLayout(1, 4, 4, 4));

        //score.setHgap(150);

        JPanel Pscore = new JPanel();
        JPanel Vscore = new JPanel();
        JPanel PLscore = new JPanel();
        JPanel VLscore = new JPanel();

        Pscore.setLayout(new BorderLayout());
        Vscore.setLayout(null);
        PLscore.setLayout(null);
        VLscore.setLayout(null);

        /*Pscore.setBounds(100, 70, 50, 50);

        //JTextField Ptext = new JTextField();
        //Ptext.setText("lmao");

        //Pscore.add(Ptext);

        score.add(PLscore);
        score.add(VLscore);*/
        

        frame.add(campo, BorderLayout.SOUTH);
        frame.add(score, BorderLayout.NORTH);

        /*Image Pimg = cella.P; // transform it 
        Image newimg = cella.P.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        
        Image Vimg = cella.V.getImage(); // transform it 
        newimg = cella.V.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        
        imageIcon = new ImageIcon(newimg);  // transform it back
        Pscore.add(Pimg);
        Vscore.add(Vimg);

        score.add(Pscore);
        score.add(Vscore);*/


        /*BufferedImage Psmol = ImageIO.read(this.getClass().getResource("P.png"));
        JLabel PscoreImg = new JLabel(new ImageIcon(Psmol));*/

        /*JLabel PscoreImg = new JLabel();
        PscoreImg.setIcon(new ImageIcon("P.png").getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH) );*/

        //BufferedImage image=ImageIO.read(new File("P.png"));
        //BufferedImage PscoreImg=resize(image,100,100);
        //Pscore.add(PscoreImg);
        //score.add(Pscore);
        //score.add(Pscore);

        
        


        
    }
}