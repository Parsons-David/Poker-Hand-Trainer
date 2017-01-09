import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class TrainWindow extends JFrame{

  TrainPanel panel;
  Train trainListener;

  public TrainWindow() {
    this.panel = new TrainPanel();
    this.trainListener = new Train(this.panel);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(this.panel);
    this.setTitle("Poker Hand Trainer");
    this.pack();
    this.setVisible(true);
    this.setResizable(false);
  }

}
