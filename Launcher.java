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
