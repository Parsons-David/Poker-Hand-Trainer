public class Deck{

  // Deck has 52 cards
  private Card[] cards = new Card[52];
  // Keeps track of the number of cards left to dealt
  private int dealtCards = 0;

  // Adds one of every card to the deck
  public Deck() {

    int insertedCards = 0;

    for(int i = 0; i < 4; i++){
      for (int j = 1; j < 14; j++) {
        this.cards[insertedCards] = new Card(i, j);
        insertedCards += 1;
      }
    }

    this.shuffle();
  }

  // Places all cards in new random loactions in the deck
  public void shuffle(){
    // Temporary card array for storing the cards in their new locations
    Card[] tempCards = new Card[52];
    // Holds the attempted new location of whatever card is being placed
    // Gets a random value from 0 - 51
    int newPosition = (int) (Math.random() * 52);
    // Iterates over ever card in the original deck
    for(Card i : this.cards){
      // Gets a new random loaction, if the current random location in the temp deck contains a card
      while(tempCards[newPosition] != null){
        // Gets a random value from 0 - 51
        newPosition = (int) (Math.random() * 52);
      }
      // Places current card into new location in shuffled deck
      tempCards[newPosition] = i;
    }
    // Updates reference of objects deck to the new shuffled deck
    this.cards = tempCards;
  }

  // Prints the string representation of all cards making in the deck
  public void print(){
    // Iterates over every card in the deck
    for(Card i : this.cards){
      // Prints the string reprsentation of the current card
      System.out.println(i.toString());
    }
  }

  // Returns a reference to the next card in the deck stream
  public Card drawCard(){
    // If 52 cards have already been dealt, no cards are left to be dealt
    if(this.dealtCards >= 52){
      // Resets the number of dealt cards to 0
      this.dealtCards = 0;
      // Shuffles deck to create a new order/deck
      this.shuffle();
    }
    // Returns the next card in the deck and postdecrements the nextCard counter
    return this.cards[this.dealtCards++];
  }

  // Returns the number of cards dealt from the deck
  public int getDealtCards(){
    return this.dealtCards;
  }

  public Card[] getCards(){
    return this.cards;
  }

}
