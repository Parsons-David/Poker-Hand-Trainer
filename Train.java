import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.util.Iterator;
import java.util.HashMap;

public class Train implements ActionListener{

  private TrainPanel components;

  boolean hands[] = new boolean[10];
  Deck dealer = new Deck();
  ArrayList<Card> hold; // Player Cards
  ArrayList<Card> community; // Community dealt cards
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

    hold = new ArrayList<>(2);
    community = new ArrayList<>(5);

    for(int i = 0; i < hands.length; i++){
      hands[i] = false;
    }

    // Enable Possible Hand Buttons
    for(JButton btn : this.components.playerButtons){
      btn.setEnabled(true);
    }
    for(JButton btn : this.components.communityButtons){
      btn.setEnabled(true);
    }

    // Deals cards to players
    for(int i = 0; i < 2; i++){
      hold.add(i, dealer.drawCard());
      components.playerCardIcons.get(i).setIcon(hold.get(i).getGraphic());
    }

    // Draws cards for community
    for(int i = 0; i < 5; i++){
      community.add(i, dealer.drawCard());
    }

    // Show Flop
    for(int i = 0; i < 3; i++){
      components.communityCardIcons.get(i).setIcon(community.get(i).getGraphic());
    }

    components.btnState.setText("Turn");
  }

  public void flopCheck(){

    checkHands(2);
    printHands();

    components.communityCardIcons.get(3).setIcon(community.get(3).getGraphic());

    components.btnState.setText("River");
  }

  public void turnCheck(){

    checkHands(1);
    printHands();

    components.communityCardIcons.get(4).setIcon(community.get(4).getGraphic());

    components.btnState.setText("Finish");
  }

  public void riverCheck(){

    checkHands(0);
    printHands();

    setUp();
    components.btnState.setText("Press to Start");
  }

  public void checkHands(int freeCards){
    hands[4] = flush(freeCards);

    int faces[] = new int[14];
    for(Card i : hold){
      faces[i.getFace() - 1]++;
    }
    for(int i = 0; i < 5 - freeCards; i++){
      faces[community.get(i).getFace() - 1]++;
    }
    faces[13] = faces[0];

    for(int i = 0; i < 14; i++){
      System.out.println("\t" + faces[i] + " " + i);
    }

    // Holder array for storing if
    boolean straightFaces[] = new boolean[14];
    for(int i = 0; i < 14; i++){
      if(faces[i] > 0){
        straightFaces[i] = true;
        switch (faces[i]){
          case 2 :
            // 3 -> Full House : If three of a kind exist, now a pair exists, therefore full house exists.
            hands[3] = hands[6];
            // 7 -> Two Pair : If a pair already exits, this is the second pair.
            hands[7] = hands[8];
            // 8 -> Pair : In this case a pair exists.
            hands[8] = true;
            break;
          case 3 :
            // 3 -> Full House : If a pair exists, now three of a kind exists, therefore full house exists.
            hands[3] = hands[8];
            // 7 -> Two Pair : If a pair already exits, this is the second pair.
            hands[7] = hands[8];
            // 6 -> Three of a kind : In this case three of a kind exists.
            hands[6] = true;
            // 8 -> Pair : In this case a pair exists.
            hands[8] = true;
            break;
          case 4 :
            // 2 -> Four of a kind : In this case four of a kind exists.
            hands[2] = true;
            // 3 -> Full House : If a pair exists, now three of a kind exists, therefore full house exists.
            hands[3] = hands[8];
            // 7 -> Two Pair : If a pair already exits, this is the second pair.
            hands[7] = hands[8];
            // 6 -> Three of a kind : In this case three of a kind exists.
            hands[6] = true;
            // 8 -> Pair : In this case a pair exists.
            hands[8] = true;
            break;
        }
      } else {
        straightFaces[i] = false;
      }
    }

    hands[5] = isStraight(freeCards, straightFaces);

    // 2 -> Straight Flush: If a straight and a flush exists, then a straight flush exists
    // hands[1] = hands[5] && hands[4];
  }

  public boolean flush(int freeCards){
    String strs[] = {"♠","♥","♣","♦"};
    int suits[] = new int[4];
    for(Card i : hold){
      suits[i.getSuit()]++;
    }
    for(int i = 0; i < 5 - freeCards; i++){
      suits[community.get(i).getSuit()]++;
    }
    for(int i = 0; i < 4; i++){
      System.out.println("\t" + suits[i] + " " + strs[i]);
    }
    for(int i : suits){
      if(i + freeCards > 4){
        System.out.println("Flush Possible");
        return true;
      }
    }
    System.out.println("Flush Not Possible");
    return false;
  }

  public boolean straight(int freeCards, boolean faces[]){
    int streak = 0;
    int buffer = freeCards;

    for(boolean i : faces){

      if(i){
        streak++;
      } else {
        if(buffer > 0){
          streak++;
          buffer--;
        } else {
          streak = 0;
          buffer = freeCards;
        }
      }

      if(streak > 4){
        return true;
      }

    }

    return false;
  }

  public void isStraight(int freeCards, boolean faces[]){
    int streak = 0;
    int buffer = freeCards;

    for(boolean i : faces){
      if(i){
        streak++;
      } else {
        if(buffer > 0){
          streak++;
          buffer--;
        } else {
          streak = 0;
          buffer = freeCards;
        }
      }

      if(streak > 4){
        // 5 -> Straight: If 5 consecutive face values have 1 or more 5 or more times in a row, the a Straight exists
        hands[5] = true;
        break;
      }

    }

  }

  public void printHands(){
    System.out.println("Royal Flush -> " + hands[0]);
    System.out.println("Straight Flush -> " + hands[1]);
    System.out.println("Four of a Kind -> " + hands[2]);
    System.out.println("Full House -> " + hands[3]);
    System.out.println("Flush -> " + hands[4]);
    System.out.println("Straight -> " + hands[5]);
    System.out.println("Three of a Kind -> " + hands[6]);
    System.out.println("Two Pair -> " + hands[7]);
    System.out.println("Pair -> " + hands[8]);
    System.out.println("High Card -> " + hands[9]);
  }

}
