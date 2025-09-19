package GoDrive;
import java.awt.*;
import javax.swing.*;

public class MainFrame {
    public MainFrame() {
        JFrame f = new JFrame("GoDrive");
        Container cp = f.getContentPane();
        f.getContentPane().setBackground(new Color(235, 243, 250));
        cp.setLayout(null);
        
        JLabel l1 = new JLabel("GoDrive");
        l1.setFont(new Font("Arial", Font.BOLD, 29));
        l1.setBounds(25, 25, 126, 25);
        
        JLabel l2 = new JLabel("Home");
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l2.setBounds(50, 65, 120, 20);

        JLabel l3 = new JLabel("Rent");
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        l3.setBounds(50, 105, 120, 20);

        JPanel p1 = new JPanel();
        p1.setBounds(200, 0, 600, 50);
        p1.setSize(1500, 700);
        p1.setBackground(Color.WHITE);
        
        cp.add(l1);
        cp.add(l2);
        cp.add(l3);
        cp.add(p1);

        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Popup popup = new Popup(f);
        popup.setVisible(true);
        
        

    }

    }
