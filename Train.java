import java.util.ArrayList;

public class Train{

  public static void main(String[] args) {

    // Window Testing

    TrainWindow trainer = new TrainWindow();

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

    System.out.println("Welcome to Poker Hand Training!");
    System.out.println("Type yes, when you are ready to begin?");
    while(ConsoleIO.readString().equalsIgnoreCase("yes")){

      Deck dealer = new Deck();
      Card[] hand = new Card[7];
      ArrayList<Integer> values = new ArrayList<>();
      // Card[] dealt = new Card[5];

      // Flop

      for(int i = 0; i < 5; i ++){
        hand[i] = dealer.drawCard();
      }

      String handStr = hand[0].toString() + " " + hand[1].toString();
      String dealtStr = hand[2].toString() + " " + hand[3].toString() + " " + hand[4].toString();

      System.out.printf("Your hand:\t%s\n\n", handStr);
      System.out.printf("\t\t%s\n\n", dealtStr);

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

      hand[5] = dealer.drawCard();

      dealtStr += " " + hand[5].toString();

      System.out.printf("Your hand:\t%s\n\n", handStr);
      System.out.printf("\t\t%s\n\n", dealtStr);

      System.out.println("Enter the values representing the hands that are no longer possible (enter '-1' when you are done):");

      val = ConsoleIO.readInt();

      while(val != -1){
        if(val > 0 && val < 11){
          values.add(val);
        }
        System.out.print("Enter another value or '-1': ");
        val = ConsoleIO.readInt();
      }

      // River

      hand[6] = dealer.drawCard();

      dealtStr += " " + hand[6].toString();

      System.out.printf("Your hand:\t%s\n\n", handStr);
      System.out.printf("\t\t%s\n\n", dealtStr);

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
