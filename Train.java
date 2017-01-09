import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.util.Iterator;
import java.util.HashMap;

public class Train implements ActionListener{

  private TrainPanel components;

  Deck dealer = new Deck();
  Card[] hold = new Card[2]; // Player Cards
  Card[] community = new Card[5]; // Community dealt cards
  ArrayList<Integer> values = new ArrayList<>();

  public Train(TrainPanel panel){
    this.components = panel;
    assignListeners();
    setUp();
  }

  private void assignListeners(){

    Iterator it;

    // State Button
    this.components.btnState.addActionListener(this);

    for(JButton btn : this.components.playerButtons){
      btn.addActionListener(this);
    }

    for(JButton btn : this.components.communityButtons){
      btn.addActionListener(this);
    }

  }

  public void actionPerformed(ActionEvent e) {

    if(e.getSource() instanceof JButton){
      JButton btn = (JButton) e.getSource();
      if(this.components.playerButtons.contains(btn)){
        btn.setEnabled(false);
      } else if(this.components.communityButtons.contains(btn)){
        btn.setEnabled(false);
      } else {
        switch (btn.getText()){
          case "Press to Start": beginRound(); break;
          case "Turn": flopCheck(); break;
          case "River": turnCheck(); break;
          case "Finish": riverCheck(); break;
        }
      }

    }

    // System.out.println("Action Performed - Updated");
  }

  public void setUp(){

    // Disable Possible Hand Buttons
    for(JButton btn : this.components.playerButtons){
      btn.setEnabled(false);
    }
    for(JButton btn : this.components.communityButtons){
      btn.setEnabled(false);
    }

    // Displays Back Images for start.
    for(int i = 0; i < 5; i++){
      components.communityCardIcons.get(i).setIcon(components.backImage);
    }
    for(int i = 0; i < 2; i++){
      components.playerCardIcons.get(i).setIcon(components.backImage);
    }
  }

  public void beginRound(){

    // Enable Possible Hand Buttons
    for(JButton btn : this.components.playerButtons){
      btn.setEnabled(true);
    }
    for(JButton btn : this.components.communityButtons){
      btn.setEnabled(true);
    }

    // Deals cards to players
    for(int i = 0; i < 2; i++){
      hold[i] = dealer.drawCard();
      components.playerCardIcons.get(i).setIcon(hold[i].getGraphic());
    }

    // Draws cards for community
    for(int i = 0; i < 5; i++){
      community[i] = dealer.drawCard();
    }

    // Show Flop
    for(int i = 0; i < 3; i++){
      components.communityCardIcons.get(i).setIcon(community[i].getGraphic());
    }

    components.btnState.setText("Turn");
  }

  public void flopCheck(){

    components.communityCardIcons.get(3).setIcon(community[3].getGraphic());

    components.btnState.setText("River");
  }

  public void turnCheck(){

    components.communityCardIcons.get(4).setIcon(community[4].getGraphic());

    components.btnState.setText("Finish");
  }

  public void riverCheck(){

    setUp();
    components.btnState.setText("Press to Start");
  }

}
