import java.util.ArrayList;

public class Train{

  public static void main(String[] args) {
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
    System.out.println("Are you ready to begin?");
    while(!ConsoleIO.readString().equalsIgnoreCase("yes")){
      System.out.println("\tOk let me know when you are.\nAre you ready to begin now?");
    }

    Deck dealer = new Deck();
    Card[] hand = new Card[7];
    ArrayList<Integer> values = new ArrayList<>();
    // Card[] dealt = new Card[5];

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
