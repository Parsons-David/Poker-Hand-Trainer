import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.util.Iterator;
import java.util.HashMap;

public class TrainListener implements ActionListener{

  private TrainWindow components;

  boolean hands[] = new boolean[10];
  Deck dealer = new Deck();
  ArrayList<Card> hold; // Player Cards
  ArrayList<Card> community; // Community dealt cards
  ArrayList<Integer> values = new ArrayList<>();

  public TrainListener(TrainWindow window){
    this.components = window;
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

    System.out.println("FLOP:");

    checkHands(2);
    printHands();
  }

  public void flopCheck(){

    components.communityCardIcons.get(3).setIcon(community.get(3).getGraphic());

    components.btnState.setText("River");

    System.out.println("TURN:");

    checkHands(1);
    printHands();
  }

  public void turnCheck(){

    components.communityCardIcons.get(4).setIcon(community.get(4).getGraphic());

    components.btnState.setText("Finish");

    System.out.println("RIVER:");

    checkHands(0);
    printHands();
  }

  public void riverCheck(){

    setUp();
    components.btnState.setText("Press to Start");
  }

  public void checkHands(int freeCards){
    boolean cards[][] = new boolean[4][15];

    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 15; j++){
        cards[i][j] = false;
      }
    }

    for(Card i : hold){
      cards[i.getSuit()][i.getFace() - 1] = true;
    }
    for(int i = 0; i < 5 - freeCards; i++){
      cards[community.get(i).getSuit()][community.get(i).getFace() - 1] = true;
    }
    // Sets low and high ace
    for(boolean suit[] : cards){
      suit[0] = suit[0] || suit[14];
      suit[14] = suit[0] || suit[14];
    }

    hands[0] = royalFlush(cards, freeCards);
    hands[1] = straightFlush(cards, freeCards);
    hands[4] = flush(cards, freeCards);
    hands[5] = straight(cards, freeCards);

    pairAndKinds(cards, freeCards);

  }

  // Checks if a royal Straight is possible
  public boolean royalFlush(boolean cards[][], int buffer){
    // Checks every suit in the deck
    for(boolean suit[] : cards){
      // Temporary buffer, Buffer meaning cards that haven't been flipped
      int tmpBuffer = buffer;
      // Iterates over 10 - Ace.
      for(int i = 10; i < 15; i++){
        // If card has been dealt add one to temp buffer
        if(suit[i]){
          tmpBuffer += 1;
        }
      }
      // If temp buffer is greater than 5, it is possible for a Straight to exist
      if(tmpBuffer >= 5){
        return true;
      }
    }
    // If no suit has a Straight
    return false;
  }

  // Checks if every suit has a Straight on any kind
  public boolean straightFlush(boolean cards[][], int buffer){
    // Checks every suit in the deck
    for(boolean suit[] : cards){
      // Buffer of cards that the straight doesn't have to cover to possibly exist.
      int tmpBuffer = buffer;
      // Represents the current streak of cards dealt
      int streak = 0;
      for(int i = 0; i < 15; i++){
        if(suit[i]){
          // if card exists in deck streak continues
          streak++;
        } else {
          // No buffer remaining
          if(tmpBuffer == 0){
            // Resets Buffer
            tmpBuffer = buffer;
            // Resets streak
            streak = 0;
            if(tmpBuffer > 0){
              streak++;
              tmpBuffer--;
            }
            continue;
          } else {
            // Keeps streak alive
            streak++;
            // Takes away from buffer because streak was incrmented
            tmpBuffer--;
          }
        }
        // if a streak of 5 has been achieved, a straight could exist.
        if(streak == 5){ // Might be able to improve this if (streak + tmpBuffer) >= 5
          return true;
        }
      }
    }
    // If no suit has a straight
    return false;
  }

  // Checks if every suit has a flush
  public boolean flush(boolean cards[][], int buffer){
    // Checks every suit
    for(boolean suit[] : cards){
      // number of cards in this suit that has been dealt
      int count = 0;
      // Iterates over every card in suit. NOTE: Excludes high ace
      for(int i = 0; i < 14; i++){
        // If card has been dealt
        if(suit[i]){
          // Increments count
          count++;
        }
      }

      // If count + buffer is enough for a flush
      if((count + buffer) >= 5){
        return true;
      }
    }
    // No flushes Found
    return false;
  }

  // Checks if the dealt cards could have a Straight
  public boolean straight(boolean cards[][], int buffer){
    // Buffer of cards that the straight doesn't have to cover to possibly exist.
    int tmpBuffer = buffer;
    // Represents the current streak of cards dealt
    int streak = 0;

    // Iterates over every face in deck Ace - King, and High Ace
    for(int i = 0; i < 15; i++){
      // If the face of any suit has been dealt
      if(cards[0][i] || cards[1][i] || cards[2][i] || cards[3][i]){
        // Streak continues
        streak++;
        System.out.print(i);
      } else {
        // No buffer remaining
        if(tmpBuffer == 0){
          // Resets Buffer
          tmpBuffer = buffer;
          // Resets streak
          streak = 0;
          if(tmpBuffer > 0){
            streak++;
            tmpBuffer--;
          }
          continue;
        } else {
          // Keeps streak alive
          streak++;
          System.out.print(i);
          // Takes away from buffer because streak was incrmented
          tmpBuffer--;
        }
      }
      // If streak of 5 has been reached, a straight could exist
      if(streak >= 5){ // Might be able to improve this if (streak + tmpBuffer) >= 5
        System.out.println("");
        return true;
      }
    }
    System.out.println("");
    return false;
  }

  public void pairAndKinds(boolean cards[][], int buffer){
    boolean fourKind = false;
    boolean fullHouse = false;
    boolean threeKind = false;
    boolean twoPair = false;
    boolean pair = false;
    boolean highCard = true;
    // Iterates over every face in deck Ace - King NOTE: Excludes High Ace
    for(int i = 0; i < 14; i++){
      // temp count for determining the number of the face that has been dealt
      int tmpCount = (cards[0][i]) ? 1 : 0;
      tmpCount += (cards[1][i]) ? 1 : 0;
      tmpCount += (cards[2][i]) ? 1 : 0;
      tmpCount += (cards[3][i]) ? 1 : 0;

      if(tmpCount == 1){
        highCard = true;
      } else if(tmpCount == 2){
        fullHouse = threeKind ? threeKind : fullHouse;
        twoPair = pair ? pair : twoPair;
        pair = true;
      } else if(tmpCount == 3){
        fullHouse = (pair || threeKind) ? true : fullHouse;
        twoPair = pair ? pair : twoPair;
        pair = true;
        threeKind = true;
      } else if(tmpCount >= 4){
        fullHouse = (pair || threeKind) ? true : fullHouse;
        twoPair = pair ? pair : twoPair;
        pair = true;
        threeKind = true;
        fourKind = true;
      }

    }


    while (buffer > 0){
      if(!pair){
        pair = true;
      } else {
        if(!twoPair){
          twoPair = true;
        } else {
          if(!fullHouse){
            fullHouse = true;
          }
        }
        if(!threeKind){
          threeKind = true;
        } else {
          if(!fourKind){
            fourKind = true;
          }
        }
      }
      buffer--;
    }

    hands[2] = fourKind;
    hands[3] = fullHouse;
    hands[6] = threeKind;
    hands[7] = twoPair;
    hands[8] = pair;
    hands[9] = highCard;
  }

  public void printHands(){
    System.out.println("\tRoyal Flush -> " + hands[0]);
    System.out.println("\tStraight Flush -> " + hands[1]);
    System.out.println("\tFour of a Kind -> " + hands[2]);
    System.out.println("\tFull House -> " + hands[3]);
    System.out.println("\tFlush -> " + hands[4]);
    System.out.println("\tStraight -> " + hands[5]);
    System.out.println("\tThree of a Kind -> " + hands[6]);
    System.out.println("\tTwo Pair -> " + hands[7]);
    System.out.println("\tPair -> " + hands[8]);
    System.out.println("\tHigh Card -> " + hands[9]);
  }

}
