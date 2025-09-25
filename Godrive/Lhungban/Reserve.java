package Godrive.Lhungban;

import java.awt.*;
import javax.swing.*;

public class Reserve {

    public Reserve() {
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

        JLabel firstname = new JLabel("first name");
        firstname.setBounds(250, 270, 100, 20);
        cp.add(firstname);
        JTextField tf1 = new JTextField("Enter your firstname", 20);
        tf1.setBounds(250, 290, 250, 30); 
        cp.add(tf1);

        JLabel lastname = new JLabel("last name");
        lastname.setBounds(250, 350, 100, 20);
        cp.add(lastname);
        JTextField tf2 = new JTextField("Enter your lastname", 20);
        tf2.setBounds(250, 370, 250, 30); 
        cp.add(tf2);

        JLabel driver = new JLabel("Driver's license  number");
        driver.setBounds(250, 430, 100, 20);
        cp.add(driver);
        JTextField tf3 = new JTextField("Enter your driver's license  number", 30);
        tf3.setBounds(250, 450, 250, 30); 
        cp.add(tf3);

        JLabel expiration = new JLabel("Expiration date");
        expiration.setBounds(250, 510, 100, 20);
        cp.add(expiration);
        JTextField tf4 = new JTextField("MM/DD/YYYY", 30);
        tf4.setBounds(250, 530, 250, 30); 
        cp.add(tf4);

        JLabel address = new JLabel("Adderss");
        address.setBounds(550, 350, 100, 20);
        cp.add(address);
        JTextField tf5 = new JTextField("Enter your address", 20);
        tf5.setBounds(550, 370, 250, 30); 
        cp.add(tf5);

        JLabel phone = new JLabel("Phone number");
        phone.setBounds(550, 430, 100, 20);
        cp.add(phone);
        JTextField tf6 = new JTextField("Enter your phone  number", 30);
        tf6.setBounds(550, 450, 250, 30); 
        cp.add(tf6);

        JPanel p2 = new JPanel();
        p2.setBounds(550, 20, 400, 300);
        p2.setSize(290, 300);
        p2.setBackground(new Color(246,246,246));

        JButton b1 = new JButton("Booking");
        b1.setFont(new Font("Arial", Font.BOLD, 20));
        b1.setForeground(Color.WHITE);
        b1.setBounds(550,530,250,30);
        b1.setBackground(new Color(215,18,18));
        cp.add(b1);


        JPanel p3 = new JPanel();
        p3.setBounds(220, 30, 400, 300);
        p3.setSize(300, 200);
        p3.setBackground(new Color(246,246,246));
        
        cp.add(p3);
        cp.add(p2);
        cp.add(l1);
        cp.add(l2);
        cp.add(l3);
        cp.add(p1);
        

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Godrive/Imge/car.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);
        
        p3.add(imageLabel);

        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setResizable(false);


        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}