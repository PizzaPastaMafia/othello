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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;


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
        
        //start();
        init();
        //warning();
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

    JButton si = new JButton("si cazzo");
    JButton no = new JButton("no voglio la mamma");
    public void warning(){
        JFrame frame1 = new JFrame();
        frame1.setTitle("Meglio di no");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(400, 200);
        frame1.setLayout(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(1, 2, 2, 2));

        JLabel label = new JLabel("<html>Nightmare! è talmente difficile che neanche io sono ancora riusito a battere il bot, </br>sei sicuro di volere questa difficoltà?</html>");
        frame1.add(label, BorderLayout.CENTER);

        buttons.add(si);
        buttons.add(no);
        si.addActionListener(this);
        frame1.add(buttons, BorderLayout.SOUTH);
        frame1.setVisible(true);


    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == hard){
            
            warning();
        }

        if(e.getSource() == si){
            
            
        }

        if(e.getSource() == pvp){
            mode = 1;
        } else if(e.getSource() == Pbot){
            mode = 2;
        }  else if(e.getSource() == multi){
            mode = 3;
        }

        /*if(e.getSource() == single || e.getSource() == bot || e.getSource() == multi){
            frame.remove(mainStart);
        }*/
        
        
        for (int i = 0; i < 64; i++){
            int x = (int)(i / 8);
            int y = i % 8;
            
            if(e.getSource() == buttons[i]){
                gioco(campo);
                ClearConsole();

                if(mode == 1){
                    if(moveCounter % 2 == 0){
                        if(campoConMosse[x][y] == 3){
                            buttons[i].setIcon(cella.P);
                            boolean inutile = ControlloMossa(campo, x, y, moveCounter, 1);
                            campo[x][y] = 1;
                            moveCounter++;
                            System.out.println("bru");
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
            //stampaCampo(campoConMosse);
            gioco(campo);
            ControlloVittoria(campoConMosse);
            
            
        }
    }
    
    cella buttons[] = new cella[64];
    JRadioButtonMenuItem beginner = new JRadioButtonMenuItem("I'm too young to die", true);
    JRadioButtonMenuItem easy = new JRadioButtonMenuItem("Hurt me plenty", false);
    JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Ultra-violence", false);
    JRadioButtonMenuItem hard = new JRadioButtonMenuItem("Nightmare!", false);
    JMenuItem pvp = new JMenuItem("contro giocatore locale");
    JMenuItem Pbot = new JMenuItem("contro bot");
    JMenuItem multi = new JMenuItem("contro giocatore sulla stessa LAN...");
    
    void init() throws IOException{

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu partita = new JMenu("partita");
        menubar.add(partita);
        
        JMenu nuovaPartita = new JMenu("nuova partita");
        partita.add(nuovaPartita);


        nuovaPartita.add(pvp);
        nuovaPartita.add(Pbot);        
        nuovaPartita.add(multi); 
        pvp.addActionListener(this);
        Pbot.addActionListener(this);
        multi.addActionListener(this);       

        JMenu bot = new JMenu("difficoltà bot");
        partita.add(bot);
        
        bot.add(beginner);
        bot.add(easy);
        bot.add(medium);
        bot.add(hard);
        
        beginner.addActionListener(this);
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);
        
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
        
        frame.add(campo, BorderLayout.SOUTH);
        frame.add(score, BorderLayout.NORTH);
        
    }
}