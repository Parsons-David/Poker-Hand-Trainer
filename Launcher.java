import java.util.ArrayList;
import java.awt.event.*;

public class Launcher {

  public static void main(String[] args) {

    // Window Testing

    TrainWindow window = new TrainWindow();

    // END Window Testing


    // Deck Testing

    // Deck test = new Deck();
    // test.print();
    // test.shuffle();
    // System.out.println();
    // test.print();
    // test.shuffle();
    //
    // System.out.println();
    // for(int i = 0; i < 200; i++){
    //   System.out.println(test.drawCard().toString());
    // }

    // END Deck Testing

    Deck dealer = new Deck();
    Card[] hold = new Card[2]; // Player Cards
    Card[] community = new Card[5]; // Community dealt cards
    ArrayList<Integer> values = new ArrayList<>();

    System.out.println("Welcome to Poker Hand Training!");
    System.out.println("Type yes, when you are ready to begin?");

    while(ConsoleIO.readString().equalsIgnoreCase("yes")){

      // Deals cards to players
      for(int i = 0; i < 2; i++){
        hold[i] = dealer.drawCard();
        window.panel.communityCardsIcons.get("lblHoldCard" + String.valueOf(i + 1)).setIcon(hold[i].getGraphic());
      }

      // Draws cards for community
      for(int i = 0; i < 5; i++){
        community[i] = dealer.drawCard();
      }

      // Flop

      for(int i = 0; i < 3; i++){
        window.panel.communityCardsIcons.get("lblCommunityCard" + String.valueOf(i + 1)).setIcon(community[i].getGraphic());
      }
      for(int i = 3; i < 5; i++){
        window.panel.communityCardsIcons.get("lblCommunityCard" + String.valueOf(i + 1)).setIcon(window.panel.backImage);
      }

      String holdStr = hold[0].toString() + " " + hold[1].toString();
      String communityStr = community[0].toString() + " " + community[1].toString() + " " + community[2].toString();

      System.out.printf("Your hand:\t%s\n\n", holdStr);
      System.out.printf("\t\t%s\n\n", communityStr);

      System.out.println("Enter the values representing the hands that are no longer possible (enter '-1' when you are done):");

      int val = ConsoleIO.readInt();

      while(val != -1){
        if(val > 0 && val < 11){
          values.add(val);
        }
        System.out.print("Enter another value or '-1': ");
        val = ConsoleIO.readInt();
      }

      // END FLOP

      // Turn

      window.panel.communityCardsIcons.get("lblCommunityCard4").setIcon(community[3].getGraphic());

      communityStr += " " + community[3].toString();

      System.out.printf("Your hand:\t%s\n\n", holdStr);
      System.out.printf("\t\t%s\n\n", communityStr);

      System.out.println("Enter the values representing the hands that are no longer possible (enter '-1' when you are done):");

      val = ConsoleIO.readInt();

      while(val != -1){
        if(val > 0 && val < 11){
          values.add(val);
        }
        System.out.print("Enter another value or '-1': ");
        val = ConsoleIO.readInt();
      }

      // END Turn

      // River

      window.panel.communityCardsIcons.get("lblCommunityCard5").setIcon(community[4].getGraphic());

      communityStr += " " + community[4].toString();

      System.out.printf("Your hand:\t%s\n\n", holdStr);
      System.out.printf("\t\t%s\n\n", communityStr);

      System.out.println("Enter the values representing the hands that are no longer possible (enter '-1' when you are done):");

      val = ConsoleIO.readInt();

      while(val != -1){
        if(val > 0 && val < 11){
          values.add(val);
        }
        System.out.print("Enter another value or '-1': ");
        val = ConsoleIO.readInt();
      }

      System.out.println("Would you like complete another round? - Type 'yes' if you are.");

    }


    // Equality and Contains Test

    // ArrayList<Card> test = new ArrayList<>();
    // for(int i = 1; i < 8; i++){
    //   Card hold = new Card(0, i);
    //   test.add(hold);
    //   System.out.println(test.get(i - 1).toString());
    // }
    //
    // for(int i = 5; i < 10; i++){
    //   System.out.println(test.contains(new Card(0, i)));
    // }

    // END Equality and Contains Test

  }

  public boolean royalFlush(){
    for(int i = 0; i < 5; i++){

    }
    return false;
  }

  // public boolean straightFlush(){
  //
  // }
  //
  // public boolean fourKind(){
  //
  // }
  //
  // public boolean fullHouse(){
  //
  // }
  //
  // public boolean flush(){
  //
  // }
  //
  // public boolean straight(){
  //
  // }
  //
  // public boolean threeKind(){
  //
  // }
  //
  // public boolean twoPair(){
  //
  // }
  //
  // public boolean pair(){
  //
  // }
  //

}
