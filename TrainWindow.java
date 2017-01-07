
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.*;
import java.nio.file.*;
import java.io.*;

public class TrainWindow extends JFrame implements ActionListener {

  TrainPanel panel = new TrainPanel();

  public TrainWindow() {

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(panel);
    this.setTitle("Matrix Calculator");
    this.pack();
    this.setVisible(true);
    this.setResizable(false);

    panel.btnState.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    System.out.println("Action Performed");
    if(e.getSource().equals(panel.btnState)){
      switch (panel.btnState.getText()){
        case "Press to Start": panel.btnState.setText("Turn"); break;
        case "Turn": panel.btnState.setText("River"); break;
        case "River": panel.btnState.setText("Finish"); break;
        case "Finish": panel.btnState.setText("Press to Start"); break;
      }
    }
  }

  // Panel Subclass

  public class TrainPanel extends JPanel {

      // Stores all Components in a Hash Table
      HashMap<String, Object> panelComponents = new HashMap<String, Object>();
      HashMap<String, JLabel> communityCardsIcons = new HashMap<String, JLabel>();
      HashMap<String, JLabel> holdCardsIcons = new HashMap<String, JLabel>();
      String mainDirectory = Paths.get("..").toAbsolutePath().toString().replace("\\","/");
      JButton btnState;
      ImageIcon backImage = new ImageIcon(mainDirectory + "/cards/b.gif");

      public TrainPanel() {

        btnState = new JButton("Press to Start");
        add(btnState);
        btnState.setBounds(344, 350, 173, 25);
        btnState.setVisible(true);

        panelComponents.put("btnPlayerFH", new JButton ("Full House"));
        panelComponents.put("btnPlayerFK", new JButton ("Four of a Kind"));
        panelComponents.put("btnPlayerSF", new JButton ("Straight Flush"));
        panelComponents.put("btnPlayerRF", new JButton ("Royal Flush"));
        panelComponents.put("btnPlayerF", new JButton ("Flush"));
        panelComponents.put("btnPlayerStraight", new JButton ("Straight"));
        panelComponents.put("btnPlayerTK", new JButton ("Three of a Kind"));
        panelComponents.put("btnPlayerTP", new JButton ("Two Pair"));
        panelComponents.put("btnPlayerP", new JButton ("Pair"));
        panelComponents.put("btnPlayerHC", new JButton ("High Card"));
        panelComponents.put("lblPlayerPH", new JLabel ("Your Possible Hands", SwingConstants.CENTER));

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

        //adjust size and set layout
        setPreferredSize (new Dimension (722, 412));
        setLayout (null);

        //base Dimensions for buttons on the left
        int btnX = 20;
        int btnY = 50;
        int lblX = 20;
        int lblY = 20;

        Iterator it = panelComponents.entrySet().iterator();
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

          it.remove(); // avoids a ConcurrentModificationException
        }

      }
  }

  // End Panel Subclass


}
