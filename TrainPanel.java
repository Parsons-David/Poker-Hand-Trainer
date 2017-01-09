import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.*;
import java.nio.file.*;
import java.io.*;

// Panel Subclass

public class TrainPanel extends JPanel {

    String mainDirectory = Paths.get("..").toAbsolutePath().toString().replace("\\","/");

    // Stores all Components in a Hash Table
    HashMap<String, Object> playerComponents = new HashMap<String, Object>();
    HashMap<String, Object> communityComponents = new HashMap<String, Object>();
    HashMap<String, JLabel> communityCardsIcons = new HashMap<String, JLabel>();
    HashMap<String, JLabel> holdCardsIcons = new HashMap<String, JLabel>();
    JButton btnState;
    ImageIcon backImage = new ImageIcon(mainDirectory + "/cards/b.gif");


    public TrainPanel() {

      //adjust size and set layout
      setPreferredSize (new Dimension (854, 412));
      setLayout (null);

      btnState = new JButton("Press to Start");
      add(btnState);
      btnState.setBounds(344, 350, 173, 25);
      btnState.setVisible(true);

      for(int i = 1; i < 6; i++){
        JLabel tmpLabel = new JLabel (backImage);
        communityCardsIcons.put("lblCommunityCard" + String.valueOf(i), tmpLabel);
        add(tmpLabel);
        tmpLabel.setBounds(180 + ((i - 1) * 100), 50, 100, 100);
        tmpLabel.setVisible(true);
      }

      for(int i = 1; i < 3; i++){
        JLabel tmpLabel = new JLabel (backImage);
        communityCardsIcons.put("lblHoldCard" + String.valueOf(i), tmpLabel);
        add(tmpLabel);
        tmpLabel.setBounds(330 + ((i - 1) * 100), 220, 100, 100);
        tmpLabel.setVisible(true);
      }

      playerComponents.put("btnPlayerFH", new JButton ("Full House"));
      playerComponents.put("btnPlayerFK", new JButton ("Four of a Kind"));
      playerComponents.put("btnPlayerSF", new JButton ("Straight Flush"));
      playerComponents.put("btnPlayerRF", new JButton ("Royal Flush"));
      playerComponents.put("btnPlayerF", new JButton ("Flush"));
      playerComponents.put("btnPlayerStraight", new JButton ("Straight"));
      playerComponents.put("btnPlayerTK", new JButton ("Three of a Kind"));
      playerComponents.put("btnPlayerTP", new JButton ("Two Pair"));
      playerComponents.put("btnPlayerP", new JButton ("Pair"));
      playerComponents.put("btnPlayerHC", new JButton ("High Card"));
      playerComponents.put("lblPlayerPH", new JLabel ("Your Possible Hands", SwingConstants.CENTER));

      //base Dimensions for buttons on the left
      int btnX = 20;
      int btnY = 50;
      int lblX = 20;
      int lblY = 20;

      Iterator it = playerComponents.entrySet().iterator();
      while(it.hasNext()){ // Iterates over every component of the panel hash table

        HashMap.Entry pair = (HashMap.Entry) it.next();
        Object comp = pair.getValue();

        if(comp instanceof JButton){
          JButton btn = (JButton) comp;

          add(btn);
          // System.out.println(btn.getText() + " added.");

          btn.setBounds (btnX, btnY, 150, 25);
          btn.setVisible(true);

          btnY += 30;
        } else if(comp instanceof JLabel){
          JLabel lbl = (JLabel) comp;

          add(lbl);

          // System.out.println(lbl.getText() + " added.");

          lbl.setBounds (lblX, lblY, 150, 25);
          lbl.setVisible(true);
        }
      }

      communityComponents.put("btnCommunityFH", new JButton ("Full House"));
      communityComponents.put("btnCommunityFK", new JButton ("Four of a Kind"));
      communityComponents.put("btnCommunitySF", new JButton ("Straight Flush"));
      communityComponents.put("btnCommunityRF", new JButton ("Royal Flush"));
      communityComponents.put("btnCommunityF", new JButton ("Flush"));
      communityComponents.put("btnCommunityStraight", new JButton ("Straight"));
      communityComponents.put("btnCommunityTK", new JButton ("Three of a Kind"));
      communityComponents.put("btnCommunityTP", new JButton ("Two Pair"));
      communityComponents.put("btnCommunityP", new JButton ("Pair"));
      communityComponents.put("btnCommunityHC", new JButton ("High Card"));
      communityComponents.put("lblCommunityPH", new JLabel ("All Possible Hands", SwingConstants.CENTER));


      // base Dimensions for buttons on the right
      btnX = 693;
      btnY = 50;
      lblX = 700;
      lblY = 20;

      it = communityComponents.entrySet().iterator();
      while(it.hasNext()){ // Iterates over every component of the panel hash table

        HashMap.Entry pair = (HashMap.Entry) it.next();
        Object comp = pair.getValue();

        if(comp instanceof JButton){
          JButton btn = (JButton) comp;

          add(btn);
          // System.out.println(btn.getText() + " added.");

          btn.setBounds (btnX, btnY, 150, 25);
          btn.setVisible(true);

          btnY += 30;
        } else if(comp instanceof JLabel){
          JLabel lbl = (JLabel) comp;

          add(lbl);

          // System.out.println(lbl.getText() + " added.");

          lbl.setBounds (lblX, lblY, 150, 25);
          lbl.setVisible(true);
        }
      }

    }
}

// End Panel Subclass
