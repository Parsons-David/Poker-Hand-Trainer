import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.util.Iterator;
import java.util.HashMap;

public class Train implements ActionListener{

  private TrainPanel components;

  Deck dealer = new Deck();
  Card[] hold = new Card[2]; // Player Cards
  Card[] community = new Card[5]; // Community dealt cards
  ArrayList<Integer> values = new ArrayList<>();

  public Train(TrainPanel panel){
    this.components = panel;
    assignListeners();
  }

  private void assignListeners(){

    Iterator it;

    // State Button
    this.components.btnState.addActionListener(this);
    // 
    // System.out.println(this.components.communityComponents);
    //
    // // Player Buttons
    // it = this.components.playerComponents.entrySet().iterator();
    // while(it.hasNext()){
    //
    //   HashMap.Entry pair = (HashMap.Entry) it.next();
    //   Object comp = pair.getValue();
    //   System.out.println(pair.getKey());
    //
    //   if(comp instanceof JButton){
    //     JButton btn = (JButton) comp;
    //     btn.addActionListener(this);
    //   }
    //
    //   it.remove(); // avoids a ConcurrentModificationException
    // }
    //
    // // Community Buttons
    // it = components.communityComponents.entrySet().iterator();
    // while(it.hasNext()){
    //
    //
    // }

  }

  public void actionPerformed(ActionEvent e) {
    System.out.println("Action Performed - Updated");
    if(e.getSource().equals(components.btnState)){
      JButton state = components.btnState;
      switch (state.getText()){
        case "Press to Start": state.setText("Turn"); break;
        case "Turn": state.setText("River"); break;
        case "River": state.setText("Finish"); break;
        case "Finish": state.setText("Press to Start"); break;
      }
    }
  }

}
