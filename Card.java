import java.awt.*;
import java.nio.file.*;
import java.io.*;

public class Card {

  private int suit; // Suit {0 : spades, 1 : hearts, 2 : clubs, 3 : diamonds}
  private int face; // Face {1 : ace, 2-10 : 2-10, 11 : jack, 12 : queen, 13 : king}
  private Image graphic;


  // Creates new card
  public Card(int newSuit, int newFace){
    this.suit = newSuit;
    this.face = newFace;

    String imagePath = "cards/";

    switch (newFace){
      case 1 : imagePath += "a"; break;
      case 10 : imagePath += "t"; break;
      case 11 : imagePath += "j"; break;
      case 12 : imagePath += "q"; break;
      case 13 : imagePath += "k"; break;
      default : imagePath += Integer.toString(newFace); break;
    }

    switch (newSuit){
      case 0 : imagePath += "s"; break;
      case 1 : imagePath += "h"; break;
      case 2 : imagePath += "c"; break;
      case 3 : imagePath += "d"; break;
    }

    imagePath += ".gif";
    Toolkit tk = Toolkit.getDefaultToolkit();
    File tf = new File(imagePath);

    this.graphic = (tf.exists() ? tk.getImage(imagePath) : null);

    System.out.print(imagePath + " -> ");

    if(this.graphic == null){
      System.out.println(Integer.toString(newFace) + Integer.toString(newFace) + ".gif Not Found");
    } else {
      System.out.println(Integer.toString(newFace) + Integer.toString(newFace) + ".gif Found");
    }
  }

}
