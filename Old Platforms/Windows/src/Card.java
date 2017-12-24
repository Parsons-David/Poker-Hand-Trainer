import java.awt.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
// import java.net.*;

public class Card {

  // suits and specialFaces are static, since  they only need to exist once
  private static final String[] suitGraphics = {"♠","♥","♣","♦"};
  private static final char[] suitLetters = {'s','h','c','d'};
  private static final char[] specialFaces = {'a', 'j', 'q', 'k'};

  // each card has a suit, face, and a graphic
  private int suit; // Suit {0 : spades, 1 : hearts, 2 : clubs, 3 : diamonds}
  private int face; // Face {1 : ace, 2-10 : 2-10, 11 : jack, 12 : queen, 13 : king}
  private ImageIcon graphic;
  private boolean faceUp = true;

  // Creates new card
  public Card(int newSuit, int newFace){
    this.suit = newSuit;
    this.face = newFace;

    // All card graphics are stored in the cards folder
    String imagePath = "/cards/";

    // Values 2-10 are represented by their integer value. Values
    imagePath += (this.face < 11 && this.face > 1 ? Integer.toString(newFace) : specialFaces[(newFace - 1) % 9]);

    // maps suit integer value to the char representing it
    imagePath += suitLetters[newSuit];

    // Adds file extension
    imagePath += ".gif";

    // Toolkit tk = Toolkit.getDefaultToolkit();
    File tf = new File(imagePath);
    //
    // // assures that the given graphic exists
		try { //try to load the image files
      this.graphic = new ImageIcon(ImageIO.read(Train.class.getResourceAsStream(imagePath)));
		} catch (Exception e) {
      this.graphic = null;
			System.out.println("Image File Read error: " + imagePath + " not found");
		}

    // if(this.graphic == null){
    //   System.out.println(Integer.toString(newFace) + Integer.toString(newFace) + ".gif Not Found");
    // } else {
    //   System.out.println(Integer.toString(newFace) + Integer.toString(newFace) + ".gif Found");
    // }
  }

  //
  public int getSuit() {
      return this.suit;
  }

  //
  public int getFace() {
      return this.face;
  }

  //
  public ImageIcon getGraphic() {
      return this.graphic;
  }

  public String toString(){
    String output = "[";
    output += (this.face < 11 && this.face > 1 ? Integer.toString(this.face) : Character.toUpperCase(specialFaces[(this.face - 1) % 9]));
    output += suitGraphics[this.suit];
    output += "]";

    return output;
  }

  //
  public boolean isFaceUp() {
      return this.faceUp;
  }

  public void flip(){
    this.faceUp = !this.faceUp;
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Card)){
      return false;
    }
    Card e = (Card) obj;
    return (this == null ? e == null : (this.suit == e.suit) && (this.face == e.face));
  }

}
