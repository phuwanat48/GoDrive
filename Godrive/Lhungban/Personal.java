package Godrive.Lhungban;
import java.awt.*;
import javax.swing.*;


public class Personal extends JPanel {

    public Personal(){

        new Personal() ;
        JFrame f  = new JFrame() ;
    Container cp = f.getContentPane() ;
    cp.setLayout(new FlowLayout());

    JTextField tf1 = new JTextField("TextField 1",20);
    cp.add(tf1);


    f.pack();
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
  
}
