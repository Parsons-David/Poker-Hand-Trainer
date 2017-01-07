
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.*;
import java.nio.file.*;
import java.io.*;

public class TrainWindow extends JFrame {

  private TrainPanel panel = new TrainPanel();

  public TrainWindow() {

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(panel);
    this.setTitle("Matrix Calculator");
    this.pack();
    this.setVisible(true);
    this.setResizable(false);
  }

  // Panel Subclass

  private class TrainPanel extends JPanel{

      // Stores all Components in a Hash Table
      HashMap<String, Object> panelComponents = new HashMap<String, Object>();

      public TrainPanel() {

        // BufferedImage image = ImageIO.read(new File("/cards/test.jpg"))

        // String imagePath = "u.gif";
        //
        // Toolkit tk = Toolkit.getDefaultToolkit();
        // File tf = new File(imagePath);
        //
        // ImageIcon t = new ImageIcon(this.getClass().getResource("../cards/test.jpg"));
        //
        // Image def = t.getImage();

        panelComponents.put("btnPlayerFH", new JButton ("Full House"));
        panelComponents.put("btnPlayerFK", new JButton ("Four of a Kind"));
        panelComponents.put("btnPlayerSF", new JButton ("Straight Flush"));
        panelComponents.put("btnPlayerRF", new JButton ("Royal Flush"));
        panelComponents.put("btnPlayerF", new JButton ("Flush"));
        panelComponents.put("btnPlayerStraight", new JButton ("Straight"));
        panelComponents.put("btnPlayerTK", new JButton ("Three of a Kind"));
        panelComponents.put("btnPlayerTP", new JButton ("Two Pair"));
        panelComponents.put("btnPlayerP", new JButton ("Pair"));
        panelComponents.put("btnPlayerHC", new JButton ("High Card"));
        panelComponents.put("lblPlayerPH", new JLabel ("Your Possible Hands", SwingConstants.CENTER));

        // JLabel lblCard1 = new JLabel(new ImageIcon(this.getClass().getResource("../cards/test.jpg")));
        // System.out.println(def.getWidth(null) == -1);
        // // lblCard1.setIcon(def);
        // add(lblCard1);
        // lblCard1.setBounds (100, 100, 150, 25);
        // lblCard1.setVisible(true);

        //adjust size and set layout
        setPreferredSize (new Dimension (722, 412));
        setLayout (null);

        //base Dimensions for buttons on the left
        int btnX = 20;
        int btnY = 50;
        int lblX = 20;
        int lblY = 20;

        Iterator it = panelComponents.entrySet().iterator();
        while(it.hasNext()){ // Iterates over every component of the panel hash table

          HashMap.Entry pair = (HashMap.Entry) it.next();
          Object comp = pair.getValue();

          if(comp instanceof JButton){
            JButton btn = (JButton) comp;

            add(btn);
            // System.out.println(btn.getText() + " added.");

            btn.setBounds (btnX, btnY, 150, 25);
            btn.setVisible(true);

            btnY += 30;
          } else if(comp instanceof JLabel){
            JLabel lbl = (JLabel) comp;

            add(lbl);

            // System.out.println(lbl.getText() + " added.");

            lbl.setBounds (lblX, lblY, 150, 25);
            lbl.setVisible(true);
          }

          it.remove(); // avoids a ConcurrentModificationException
        }

      }
  }

  // End Panel Subclass


}
