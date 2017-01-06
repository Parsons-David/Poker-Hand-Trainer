public class Train{

  public static void main(String[] args) {
    Deck test = new Deck();
    test.print();
    test.shuffle();
    System.out.println();
    test.print();
    test.shuffle();

    System.out.println();
    for(int i = 0; i < 200; i++){
      System.out.println(test.drawCard().toString());
    }
  }

}
