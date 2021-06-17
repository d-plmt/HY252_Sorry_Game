package view;

import controller.Controller;
import model.card.Card;
import model.pawn.Pawn;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;


public class GraphicUI extends JFrame{

    public Card lastcard;
    public int last_value;

    private Image img;
    private ImageIcon img2;

    private JButton cardsButton, opencards, foldButton;
    private JTextArea infobox;
    private JLabel cardText;

    private JButton p1pawn1, p1pawn2, p2pawn1, p2pawn2;

    private myDesktopPane panel;
    private JLayeredPane background;        //unused
    private JLabel logo;

    private JLabel [] path;
    private JLabel [] path1,path2,path3,path4;
    private JLabel [] safety1;
    private JLabel [] safety2;
    private JLabel home1, home2;
    private JLabel start1,start2;

    private JMenuBar menu;
    private JButton new_game, save, cont_saved, exit;

    private URL url;
    private ClassLoader loader;

    /**
     * Constructor: creates a new instance of the class GraphicUI
     * Post-condition: a new instance of the class GraphicUI has been created
     */
    public GraphicUI() {
        lastcard = null;

        cardsButton = new JButton();
        opencards = new JButton();
        foldButton = new JButton();

        infobox = new JTextArea();
        cardText = new JLabel();

        p1pawn1 = new JButton();
        p1pawn2 = new JButton();
        p2pawn1 = new JButton();
        p2pawn2 = new JButton();

        path = new JLabel[60];
        path1 = new JLabel[16];
        path2 = new JLabel[14];
        path3 = new JLabel[16];
        path4 = new JLabel[14];
        safety1 = new JLabel[5];
        safety2 = new JLabel[5];

        menu = new JMenuBar();
        new_game = new JButton("New Game");
        save = new JButton("Save");
        cont_saved = new JButton("Continue");
        exit = new JButton("Exit");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loader = this.getClass().getClassLoader();
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.setTitle("Sorry!");
        url = loader.getResource("images/other/sorryImage.jpg");
        this.setIconImage(new ImageIcon(url).getImage());
        this.setPreferredSize(new Dimension(1024, 768));

        init_comps();
    }

    /**
     * Transformer: initializes all necessary components
     * Post-condition: the components have been initialized
     */
    public void init_comps() {
        int i;

        url =loader.getResource("images/other/background.png");
        img = new ImageIcon(url).getImage();
        panel = new myDesktopPane(img);
        pack();

        cardsButton.setBounds(515,100,95,115);
        url = loader.getResource("images/cards/backCard.png");
        img = new ImageIcon(url).getImage();
        img = img.getScaledInstance(95,115,Image.SCALE_SMOOTH);
        img2 = new ImageIcon(img);
        cardsButton.setIcon(img2);
        cardsButton.setVisible(true);
        cardsButton.setOpaque(true);
        panel.add(cardsButton,2);

        cardText.setBounds(515,220,250,20);
        cardText.setFont(new Font("Rockwell Extra Bold",Font.BOLD,14));
        cardText.setText("Receive Card    Current Card");
        cardText.setVisible(true);
        panel.add(cardText,2);

        foldButton.setBounds(500,250,240,35);
        foldButton.setBackground(Color.red);
        foldButton.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
        foldButton.setText("Fold Button");
        foldButton.setVisible(true);
        foldButton.setOpaque(true);
        panel.add(foldButton, 2);

        logo = new JLabel();
        logo.setBounds(150,215,180,60);
        url = loader.getResource("images/other/sorryImage.jpg");
        img = new ImageIcon(url).getImage();
        img = img.getScaledInstance(180,60, Image.SCALE_SMOOTH);
        img2 = new ImageIcon(img);
        logo.setIcon(img2);
        logo.setVisible(true);
        panel.add(logo,0);

        for (i =0; i<16; i++) {         //create path and slides
            url = null;
            path[i] = new JLabel();
            path[i].setBounds(i*30+2,5,30,30);
            path[i].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
            if ((i==1) || (i==9)) {
                url = loader.getResource("images/slides/redSlideStart.png");
            }
            else if ((i==2) || (i==3) || (i==10) || (i==11) || (i==12)) {
                url = loader.getResource("images/slides/redSlideMedium.png");
            }
            else if ((i==4) || (i==13)){
                url = loader.getResource("images/slides/redSlideEnd.png");
            }
            if (url != null) {
                img = new ImageIcon(url).getImage();
                img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                img2 = new ImageIcon(img);
                path[i].setIcon(img2);
            }
            else {
                path[i].setForeground(Color.white);
                path[i].setOpaque(true);
            }
            panel.add(path[i],1);
        }
        for (i=16; i<31; i++) {
            url = null;
            path[i] = new JLabel();
            path[i].setBounds(path[15].getX(),path[i-1].getY()+30,30,30);
            path[i].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
            if ((i==16) || (i==24)) {
                url = loader.getResource("images/slides/blueSlideStart.png");
            }
            else if ((i==17) || (i==18) || (i==25) || (i==26) || (i==27)) {
                url = loader.getResource("images/slides/blueSlideMedium.png");
            }
            else if ((i==19) || (i==28)) {
                url = loader.getResource("images/slides/blueSlideEnd.png");
            }
            if (url!=null) {
                img = new ImageIcon(url).getImage();
                img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                img2 = new ImageIcon(img);
                path[i].setIcon(img2);
            }
            else {
                path[i].setForeground(Color.white);
                path[i].setOpaque(true);
            }
            panel.add(path[i], 1);
        }
        for (i=31;i<46;i++) {
            url = null;
            path[i] = new JLabel();
            path[i].setBounds(path[i-1].getX()-30,path[30].getY(),30,30);
            path[i].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
            if ((i==31) || (i==39)) {
                url = loader.getResource("images/slides/yellowSlideStart.png");
            }
            else if ((i==32) || (i==33) || (i==40) || (i==41) || (i==42)) {
                url = loader.getResource("images/slides/yellowSlideMedium.png");
            }
            else if ((i==34) || (i==43)) {
                url = loader.getResource("images/slides/yellowSlideEnd.png");
            }
            if (url!=null) {
                img = new ImageIcon(url).getImage();
                img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                img2 = new ImageIcon(img);
                path[i].setIcon(img2);
            }
            else {
                path[i].setForeground(Color.white);
                path[i].setOpaque(true);
            }
            panel.add(path[i], 1);
        }
        for (i=46; i<60; i++) {
            url = null;
            path[i] = new JLabel();
            path[i].setBounds(path[45].getX(),path[i-1].getY()-30,30,30);
            path[i].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
            if ((i==46) || (i==54)) {
                url = loader.getResource("images/slides/greenSlideStart.png");
            }
            else if ((i==47) || (i==48) || (i==55) || (i==56) || (i==57)) {
                url = loader.getResource("images/slides/greenSlideMedium.png");
            }
            else if ((i==49) || (i==58)) {
                url = loader.getResource("images/slides/greenSlideEnd.png");
            }
            if (url!=null) {
                img = new ImageIcon(url).getImage();
                img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                img2 = new ImageIcon(img);
                path[i].setIcon(img2);
            }
            else {
                path[i].setForeground(Color.white);
                path[i].setOpaque(true);
            }
            panel.add(path[i], 1);
        }

       for (i=1; i<6; i++) {       //red safety and home
            safety1[i-1] = new JLabel();
            safety1[i-1].setBounds(62,30*i+2,30,30);
            safety1[i-1].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
            safety1[i-1].setBackground(Color.red);
            safety1[i-1].setVisible(true);
            safety1[i - 1].setOpaque(true);
            panel.add(safety1[i-1], 1);
        }
        home1 = new JLabel();
        home1.setBounds(46,182,65,50);
        home1.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
        home1.setBackground(Color.white);
        home1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
        home1.setHorizontalAlignment(JTextField.CENTER);
        home1.setVerticalAlignment(JTextField.BOTTOM);
        home1.setText("Home");
        home1.setOpaque(true);
        panel.add(home1,1);

        for (i = 1; i<6; i++) {     //yellow safety and home
            safety2[i-1] = new JLabel();
            safety2[i-1].setBounds(392,276+30*i,30,30);
            safety2[i-1].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
            safety2[i-1].setBackground(Color.yellow);
            safety2[i-1].setVisible(true);
            safety2[i - 1].setOpaque(true);
            panel.add(safety2[i-1], 1);
        }
        home2 = new JLabel();
        home2.setBounds(376,256,65,50);
        home2.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.yellow));
        home2.setBackground(Color.white);
        home2.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
        home2.setHorizontalAlignment(JTextField.CENTER);
        home2.setVerticalAlignment(JTextField.TOP);
        home2.setText("Home");
        home2.setOpaque(true);
        panel.add(home2,1);

        start1 = new JLabel();
        start1.setBounds(104,34,65,50);
        start1.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
        start1.setBackground(Color.white);
        start1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
        start1.setHorizontalAlignment(JTextField.CENTER);
        start1.setVerticalAlignment(JTextField.BOTTOM);
        start1.setText("Start");
        start1.setOpaque(true);
        panel.add(start1,1);

        start2 = new JLabel();
        start2.setBounds(315,405,65,50);
        start2.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.yellow));
        start2.setBackground(Color.white);
        start2.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
        start2.setHorizontalAlignment(JTextField.CENTER);
        start2.setVerticalAlignment(JTextField.TOP);
        start2.setText("Start");
        start2.setOpaque(true);
        panel.add(start2,2);

        /*background = new JLayeredPane();      //layer bugs or sth :)
        background.setBounds(33,35,418,418);
        background.setForeground(Color.cyan);
        background.setBackground(Color.cyan);
        background.setOpaque(true);
        background.setVisible(true);
        panel.add(background,10);*/

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 1024, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 768, GroupLayout.PREFERRED_SIZE)
        );
        panel.revalidate();
        panel.repaint();
        setVisible(true);

    }

    /**
     * Transformer: updates the info box in graphics
     * Post-condition: the info box is updated
     * @param game
     */
    public void updateinfo(Controller game) {
        String info = "Info Box\n\nTurn: ";
        if (game.turn.getTurn() == game.player1) {
            info += "Player 1 (Red)";
        }
        else {
            info += "Player 2 (Yellow)";
        }
        info += "\nCards Left: "+game.cards.size();
        infobox = new JTextArea(6,5);
        infobox.setBounds(500,290,240,90);
        infobox.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
        infobox.setText(info);
        infobox.setVisible(true);
        infobox.setEditable(false);
        infobox.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
        panel.add(infobox,1);
    }

    /**
     * Transformer: initializes the 1st red pawn
     * Post-condition: the 1st red pawn is put in the "start" area
     */
    public void initp1pawn1() {
        p1pawn1.setBounds(110,40,25,25);
        p1pawn1.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        url = loader.getResource("images/pawns/redPawn1.png");
        img = new ImageIcon(url).getImage();
        img = img.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        img2 = new ImageIcon(img);
        p1pawn1.setIcon(img2);
        p1pawn1.setVisible(true);
        p1pawn1.setFocusPainted(false);
        p1pawn1.setBorderPainted(true);
        panel.add(p1pawn1,1);
    }

    /**
     * Transformer: initializes the 2nd red pawn
     * Post-condition: the 2nd red pawn is put in the "start" area
     */
    public void initp1pawn2() {
        p1pawn2.setBounds(138,40,25,25);
        p1pawn2.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        url = loader.getResource("images/pawns/redPawn2.png");
        img = new ImageIcon(url).getImage();
        img = img.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        img2 = new ImageIcon(img);
        p1pawn2.setIcon(img2);
        p1pawn2.setVisible(true);
        p1pawn2.setFocusPainted(false);
        p1pawn2.setBorderPainted(true);
        panel.add(p1pawn2,1);
    }

    /**
     * Transformer: initializes the 1st yellow pawn
     * Post-condition: the 1st yellow pawn is put in the "start" area
     */
    public void initp2pawn1() {
        p2pawn1.setBounds(322,423,25,25);
        p2pawn1.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        url = loader.getResource("images/pawns/yellowPawn1.png");
        img = new ImageIcon(url).getImage();
        img = img.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        img2 = new ImageIcon(img);
        p2pawn1.setIcon(img2);
        p2pawn1.setVisible(true);
        p2pawn1.setFocusPainted(false);
        p2pawn1.setBorderPainted(true);
        panel.add(p2pawn1,3);
    }

    /**
     * Transformer: initializes the 2nd yellow pawn
     * Post-condition: the 2nd yellow pawn is put in the "start" area
     */
    public void initp2pawn2() {
        p2pawn2.setBounds(350,423,25,25);
        p2pawn2.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        url = loader.getResource("images/pawns/yellowPawn2.png");
        img = new ImageIcon(url).getImage();
        img = img.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        img2 = new ImageIcon(img);
        p2pawn2.setIcon(img2);
        p2pawn2.setVisible(true);
        p2pawn2.setFocusPainted(false);
        p2pawn2.setBorderPainted(true);
        panel.add(p2pawn2,3);
    }

    /**
     * Transformer: after playing a card, updates the red pawn location
     * Post-condition: the red pawn location is updated according to the card played
     * @param pawn
     */
    public void updatePawn(Pawn pawn) {
        if (pawn.getColor().equals("Red")) {
            if (pawn.getName().equals("Pawn 1")) {
                if (pawn.getSquare()<56) {
                    p1pawn1.setBounds(path[pawn.getSquare()+4].getBounds());
                }
                else {
                    p1pawn1.setBounds(path[pawn.getSquare()-56].getBounds());
                }
                panel.add(p1pawn1, 3);
            }
            else {
                if (pawn.getSquare()<56) {
                    p1pawn2.setBounds(path[pawn.getSquare()+4].getBounds());
                }
                else {
                    p1pawn2.setBounds(path[pawn.getSquare()-56].getBounds());
                }
                p1pawn2.add(p1pawn2, 3);
            }
        }
        else {
            if (pawn.getName().equals("Pawn 1")) {
                if (pawn.getSquare()<26) {
                    p2pawn1.setBounds(path[pawn.getSquare()+34].getBounds());
                }
                else {
                    p2pawn1.setBounds(path[pawn.getSquare()-26].getBounds());
                }
                panel.add(p2pawn1, 3);
            }
            else {
                if (pawn.getSquare()<26) {
                    p2pawn2.setBounds(path[pawn.getSquare()+34].getBounds());
                }
                else {
                    p2pawn2.setBounds(path[pawn.getSquare()-26].getBounds());
                }
                panel.add(p2pawn2, 3);
            }
        }
    }

    /**
     * Transformer: places the last played card face-up on the deck
     * Post-condition: the last played card has been placed
     */
    public void updateopenCards() {
        opencards.setBounds(625,100,95,115);
        url = null;
        switch (last_value) {
                case -1:
                    url = loader.getResource("images/cards/cardSorry.png");
                    break;
                case 1:
                    url = loader.getResource("images/cards/card1.png");
                    break;
                case 2:
                    url = loader.getResource("images/cards/card2.png");
                    break;
                case 3:
                    url = loader.getResource("images/cards/card3.png");
                    break;
                case 4:
                    url = loader.getResource("images/cards/card4.png");
                    break;
                case 5:
                    url = loader.getResource("images/cards/card5.png");
                    break;
                case 7:
                    url = loader.getResource("images/cards/card7.png");
                    break;
                case 8:
                    url = loader.getResource("images/cards/card8.png");
                    break;
                case 10:
                    url = loader.getResource("images/cards/card10.png");
                    break;
                case 11:
                    url = loader.getResource("images/cards/card11.png");
                    break;
                case 12:
                    url = loader.getResource("images/cards/card12.png");
                    break;
        }
        img = new ImageIcon(url).getImage();
        img = img.getScaledInstance(95,115,Image.SCALE_SMOOTH);
        img2 = new ImageIcon(img);
        opencards.setIcon(img2);
        opencards.setVisible(true);
        opencards.setOpaque(true);
        opencards.setFocusPainted(false);
        opencards.setBorderPainted(false);
        panel.add(opencards,2);
        }

    /**
     * Accessor: returns the button where the cards are placed
     * Post-condition: the placement of the button is returned
     * @return button
     */
    public JButton getCardsButton() {
        return cardsButton;
    }

    /**
     * Accessor: returns the fold button
     * Post-condition: the placement of the button is returned
     * @return button
     */
    public JButton getFoldButton() {
        return foldButton;
    }


    /**
     * Accessor: returns the last card that was opened
     * Post-condition: the last card has been returned
     * @return Deck
     */
    public Card getLastcard() {
        return lastcard;
    }

    public class myDesktopPane extends JDesktopPane
    {
        private Image backImage;

        myDesktopPane(Image img)
        {
            backImage = img;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backImage, 1, 1, 1024, 768, this);
        }
    }
}
