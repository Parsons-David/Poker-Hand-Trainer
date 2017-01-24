import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.*;
import java.nio.file.*;
import java.io.*;
import java.awt.Image;
import javax.imageio.ImageIO;

// Window Subclass

public class TrainWindow extends JFrame {

    TrainListener listener;

    String mainDirectory = Card.class.getProtectionDomain().getCodeSource().getLocation().getFile();

    // Stores all Components in a Hash Table

    ArrayList<JButton> playerButtons = new ArrayList<>();
    JLabel lblPlayerPH;

    ArrayList<JButton> communityButtons = new ArrayList<>();
    JLabel lblCommunityPH;

    ArrayList<JLabel> communityCardIcons = new ArrayList<>();

    ArrayList<JLabel> playerCardIcons = new ArrayList<>();


    HashMap<String, JLabel> communityCardsIcons = new HashMap<String, JLabel>();
    HashMap<String, JLabel> holdCardsIcons = new HashMap<String, JLabel>();
    JButton btnState;
    ImageIcon backImage;


    public TrainWindow() {
  		try { //try to load the image files
        backImage = new ImageIcon(ImageIO.read(Train.class.getResourceAsStream("/cards/b.gif")));
  		} catch (Exception e) {
  			System.out.println("Image File Read error");
  		}

      System.out.println(backImage == null);

      //adjust size and set layout
      this.getContentPane().setPreferredSize (new Dimension (863, 412));
      setLayout (null);

      btnState = new JButton("Press to Start");
      add(btnState);
      btnState.setBounds(344, 350, 173, 25);
      btnState.setVisible(true);

      for(int i = 0; i < 5; i++){
        JLabel tmpLabel = new JLabel (backImage);
        communityCardIcons.add(tmpLabel);
        add(tmpLabel);
        tmpLabel.setBounds(180 + ((i) * 100), 50, 100, 100);
        tmpLabel.setVisible(true);
      }

      for(int i = 0; i < 2; i++){
        JLabel tmpLabel = new JLabel (backImage);
        playerCardIcons.add(tmpLabel);
        add(tmpLabel);
        tmpLabel.setBounds(330 + ((i) * 100), 220, 100, 100);
        tmpLabel.setVisible(true);
      }

      playerButtons.add(new JButton ("Royal Flush"));
      playerButtons.add(new JButton ("Straight Flush"));
      playerButtons.add(new JButton ("Four of a Kind"));
      playerButtons.add(new JButton ("Full House"));
      playerButtons.add(new JButton ("Flush"));
      playerButtons.add(new JButton ("Straight"));
      playerButtons.add(new JButton ("Three of a Kind"));
      playerButtons.add(new JButton ("Two Pair"));
      playerButtons.add(new JButton ("Pair"));
      playerButtons.add(new JButton ("High Card"));
      lblPlayerPH = new JLabel ("Your Possible Hands", SwingConstants.CENTER);

      //base Dimensions for buttons on the left
      int btnX = 20;
      int btnY = 50;
      int lblX = 20;
      int lblY = 20;

      for(JButton btn : playerButtons){
        add(btn);
        // System.out.println(btn.getText() + " added.");
        btn.setBounds (btnX, btnY, 150, 25);
        btn.setVisible(true);
        btnY += 30;
      }

      add(lblPlayerPH);
      // System.out.println(lbl.getText() + " added.");
      lblPlayerPH.setBounds (lblX, lblY, 150, 25);
      lblPlayerPH.setVisible(true);

      communityButtons.add(new JButton ("Royal Flush"));
      communityButtons.add(new JButton ("Straight Flush"));
      communityButtons.add(new JButton ("Four of a Kind"));
      communityButtons.add(new JButton ("Full House"));
      communityButtons.add(new JButton ("Flush"));
      communityButtons.add(new JButton ("Straight"));
      communityButtons.add(new JButton ("Three of a Kind"));
      communityButtons.add(new JButton ("Two Pair"));
      communityButtons.add(new JButton ("Pair"));
      communityButtons.add(new JButton ("High Card"));
      lblCommunityPH = new JLabel ("All Possible Hands", SwingConstants.CENTER);

      // base Dimensions for buttons on the right
      btnX = 693;
      btnY = 50;
      lblX = 700;
      lblY = 20;

      for(JButton btn : communityButtons){
        add(btn);
        // System.out.println(btn.getText() + " added.");
        btn.setBounds (btnX, btnY, 150, 25);
        btn.setVisible(true);
        btnY += 30;
      }

      add(lblCommunityPH);
      // System.out.println(lbl.getText() + " added.");
      lblCommunityPH.setBounds (lblX - 5, lblY, 150, 25);
      lblCommunityPH.setVisible(true);

      this.listener = new TrainListener(this);

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Poker Hand Trainer");
      this.pack();
      this.setVisible(true);
      this.setResizable(false);
      this.getContentPane().setBackground(new Color(76, 153, 0));
    }
}

// End Window Subclass
