import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;


public class GUI extends othello implements ActionListener{
    JFrame frame = new JFrame();
    public GUI() throws IOException{
        
        frame.setTitle("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 725);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        
        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        init();
        
        frame.setVisible(true);
        
    }

    JLabel VLscore = new JLabel("", SwingConstants.CENTER);
    JLabel PLscore = new JLabel("", SwingConstants.CENTER);

    void paintButtons(int m[][]) {
        VLscore.setFont(new Font("Arial", Font.PLAIN, 30));
        PLscore.setFont(new Font("Arial", Font.PLAIN, 30));
        VLscore.setText("BIANCHI: " + getVscore());
        PLscore.setText("NERI: " + getPscore());

        Vscore.add(VLscore, BorderLayout.CENTER);
        Pscore.add(PLscore, BorderLayout.CENTER);
        score.add(Vscore);
        score.add(Pscore);

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j <m.length; j++) {
                if(m[j][i] == 0) {
                    buttons[m.length*i + j].setIcon(new ImageIcon("empty.png"));
                } 
                if(m[i][j] == 1){
                    buttons[m.length*i + j].setIcon(cella.P);
                }
                if(m[i][j] == 2){
                    buttons[m.length*i + j].setIcon(cella.V);
                } 
                if(m[i][j] == 3 || m[i][j] == 4) {
                    buttons[m.length*i + j].setIcon(new ImageIcon("empty.png"));
                    buttons[m.length*i + j].setLabel(); 
                } 
            }
        }

    }

    JButton si = new JButton("si cazzo");
    JButton no = new JButton("no voglio la mamma");
    JFrame frame1 = new JFrame();
    public void warning(){
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
        no.addActionListener(this);
        frame1.add(buttons, BorderLayout.SOUTH);
        frame1.setVisible(true);
    }

    JButton connetti = new JButton("connetti");
    JButton annulla = new JButton("annulla");
    JFrame frame2 = new JFrame();
    JTextField textField = new JTextField(20);
    static String ip = "";
    JTextField textField1 = new JTextField(20);
    static String username = "";
    public void connetti(){
        frame2.setTitle("Connettiti a un server");
        
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(400, 200);
        frame2.setLayout(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(1, 2, 2, 2));

        JPanel main = new JPanel(new GridLayout(4, 1, 2, 2));
        JLabel label = new JLabel("Indirizzo IP del server"); //ad esempio guarda
        JLabel label1 = new JLabel("Nome");


        main.setBorder(new EmptyBorder(20, 10, 40, 10));

        main.add(label);
        main.add(textField);
        main.add(label1);
        main.add(textField1);
        frame2.add(main, BorderLayout.CENTER);

        buttons.add(connetti);
        buttons.add(annulla);
        connetti.addActionListener(this);
        annulla.addActionListener(this);
        frame2.add(buttons, BorderLayout.SOUTH);
        frame2.setVisible(true);
    }

    
    JButton ok = new JButton("ok");
    JFrame frame3 = new JFrame();
    public void about(){
        frame3.setTitle("About");
        
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(500, 200);
        frame3.setLayout(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(1, 2, 2, 2));
        JPanel main = new JPanel(new GridLayout(1,1,1,1));
        
        JLabel label = new JLabel("<html><b>Othello</b><br><br>Di Lorenzo Del Forno, Andrea Mauro e Matteo Tramontina<br>Versione: build 22<br>OS:</html>" + System.getProperty("os.name"));
        
        main.setBorder(new EmptyBorder(10,10,10,10));
        
        main.add(label);
        frame3.add(main, BorderLayout.CENTER);
        
        buttons.add(ok);
        ok.addActionListener(this);
        frame3.add(buttons, BorderLayout.SOUTH);
        frame3.setVisible(true);
    }
    
    JButton rigioca = new JButton("rigioca");
    JButton vonde = new JButton("vonde");
    JFrame frame4 = new JFrame();
    public void fine(int x){
        frame1.setTitle("Partita finita");
        
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(400, 200);
        frame1.setLayout(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(1, 2, 2, 2));
        
        JLabel label1 = new JLabel("Giocatore 1 ha vinto: "+ getPscore() + " a " + getVscore());
        JLabel label2 = new JLabel("Giocatore 2 ha vinto: "+ getVscore() + " a " + getPscore());
        JLabel label0 = new JLabel("Pareggio: " + getPscore() + " a " + getVscore());
        
        if(x == 1){
            frame1.add(label1, BorderLayout.CENTER);
        } else if(x == 2){
            frame1.add(label2, BorderLayout.CENTER);
        } else {
            frame1.add(label0, BorderLayout.CENTER);
        }
        
        buttons.add(rigioca);
        buttons.add(vonde);
        si.addActionListener(this);
        no.addActionListener(this);
        frame1.add(buttons, BorderLayout.SOUTH);
        frame1.setVisible(true);
    }

    JFrame frame5 = new JFrame();
    public void attendi(){
        frame5.setTitle("spe");
        
        frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame5.setSize(400, 200);
        frame5.setLayout(new BorderLayout());

        JLabel label = new JLabel("<html>Attendi che l'altro giocatore che si connetta</html>");
        frame5.add(label, BorderLayout.CENTER);
        frame5.setVisible(true);
    }
    
    public void reset(){
        moveCounter = 0;
        initCampo(campo);
        copiaCampo(campo, campoConMosse);
        controlloMosse(campoConMosse, campo, moveCounter);
        paintButtons(campoConMosse);
    }

    boolean started = false;
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == beginner){
            difficolta = 0;
        }
        if(e.getSource() == easy){
            difficolta = 1;
        }
        if(e.getSource() == medium){
            difficolta = 2;
        }
        if(e.getSource() == hard){
            difficolta = 3;
        }
        if(e.getSource() == veryHard){
            difficolta = 4;
            warning();
        }

        if(e.getSource() == si){
            frame1.dispose();
        } else if (e.getSource() == no){
            frame1.dispose();
            medium.setSelected(true);
            medium.revalidate();
        }

        if(e.getSource() == pvp){
            mode = 1;
            reset();
            started = true;
        } else if(e.getSource() == Pbot){
            mode = 2;
            reset();
            started = true;
        }  else if(e.getSource() == multi){
            mode = 3;
            connetti();
        }

        if(e.getSource() == annulla){
            frame2.dispose();
        } else if (e.getSource() == connetti){
            
            ip = textField.getText();
            username = textField1.getText();
            
            try {
                socketInit(ip, username);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if(client.isConnected()){
                frame2.dispose();
                attendi();
            } else {
                JOptionPane.showMessageDialog(null, "Errore di connessione");
            }
            
        }

        if(e.getSource() == about){
            about();
        }

        if(e.getSource() == ok){
            frame3.dispose();
        }
        
        
        if(started){    
            for (int i = 0; i < 64; i++){
                int x = (int)(i / 8);
                int y = i % 8;
                
                if(e.getSource() == buttons[i]){
                    paintButtons(campoConMosse);
                    ClearConsole();

                    if(mode == 1){
                        if(moveCounter % 2 == 0){
                            if(campoConMosse[x][y] == 3){
                                buttons[i].setIcon(cella.P);
                                boolean inutile = controlloMossa(campo, x, y, moveCounter, 1);
                                campo[x][y] = 1;
                                moveCounter++;
                                System.out.println("bru");
                            }
                        } else {
                            if(campoConMosse[x][y] == 4){
                                buttons[i].setIcon(cella.V);
                                boolean inutile = controlloMossa(campo, x, y, moveCounter, 1);
                                campo[x][y] = 2;
                                moveCounter++;
                            }
                        }
                    } else {
                        if(campoConMosse[x][y] == 3){
                            buttons[i].setIcon(cella.V);
                            boolean inutile = controlloMossa(campo, x, y, moveCounter, 1);
                            campo[x][y] = 1;
                            moveCounter++;
                            routineBot(campo, campoConMosse, moveCounter);
                            moveCounter++;
                        }
                        
                    }


                    
                }
                controlloMosse(campoConMosse, campo, moveCounter);
                //stampaCampo(campoConMosse);
                paintButtons(campoConMosse);
                if(controlloVittoria(campoConMosse) >= 0){
                    fine(controlloVittoria(campoConMosse));
                }
                
                
            }
        }
    }

    Client client;
    void socketInit(String ip, String username) throws UnknownHostException, IOException{
        Socket socket = new Socket(ip, 420);
        Client clnt = new Client(socket, username);
        client = clnt;    
    }
    
    JPanel score = new JPanel();
    cella buttons[] = new cella[64];
    JPanel Pscore = new JPanel();
    JPanel Vscore = new JPanel();

    JRadioButtonMenuItem beginner = new JRadioButtonMenuItem("I'm too young to die", false);
    JRadioButtonMenuItem easy = new JRadioButtonMenuItem("Hey, not too rough!", true);
    JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Hurt me plenty", false);
    JRadioButtonMenuItem hard = new JRadioButtonMenuItem("Ultra-violence", false);
    JRadioButtonMenuItem veryHard = new JRadioButtonMenuItem("Nightmare!", false);

    JMenuItem pvp = new JMenuItem("contro giocatore locale");
    JMenuItem Pbot = new JMenuItem("contro bot");
    JMenuItem multi = new JMenuItem("contro giocatore sulla stessa LAN...");
    JMenuItem about = new JMenuItem("About");
    
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

        JMenu help = new JMenu("Help");

        help.add(about);
        about.addActionListener(this);
        menubar.add(help);

        partita.add(bot);
        
        bot.add(beginner);
        bot.add(easy);
        bot.add(medium);
        bot.add(hard);
        bot.add(veryHard);

        
        beginner.addActionListener(this);
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);
        veryHard.addActionListener(this);
        
        ButtonGroup dif = new ButtonGroup();
        dif.add(beginner);
        dif.add(easy);
        dif.add(medium);
        dif.add(hard);
        dif.add(veryHard);

        
        JPanel campo = new JPanel();
        campo.setBackground(new Color(0, 0, 0));
        campo.setPreferredSize(new Dimension(480, 480));
        
        campo.setLayout(new GridLayout(8, 8, 4, 4));
        for(int i = 0; i < 64; i++){    
            buttons[i] = new cella();
            buttons[i].addActionListener(this);
            campo.add(buttons[i]);
        }
        
        score.setPreferredSize(new Dimension(500, 185));
        score.setBackground(new Color(35, 35, 35));
        score.setLayout(new GridLayout(1, 2, 10, 10));        
        
        Pscore.setLayout(new BorderLayout());
        Vscore.setLayout(new BorderLayout());
        Pscore.setBackground(new Color(35, 35, 35));
        Vscore.setBackground(new Color(35, 35, 35));

        VLscore.setForeground(Color.WHITE);
        PLscore.setForeground(Color.WHITE);

        frame.add(campo, BorderLayout.SOUTH);
        frame.add(score, BorderLayout.NORTH);
    }
}