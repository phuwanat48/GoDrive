package GoDrive;
import GoDrive.Payment.Payment;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Personal {
    
    public Personal() {
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
        
        JPanel p2 = new JPanel();
        p2.setBounds(300, 50, 800, 700);
        p2.setSize(500, 500);
        p2.setBackground(new Color(247,247,247));


        JPanel p3 = new JPanel();
        p3.setBounds(300, 115, 800, 700);
        p3.setSize(500,1 );
        p3.setBackground(new Color(215,222,228));

        JPanel p4 = new JPanel();
        p4.setBounds(320, 150, 400, 300);
        p4.setSize(120, 80);
        p4.setBackground(new Color(247,247,247));

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Lib/Img/car_personal.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(120, 80, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);
        p4.add(imageLabel);
 
        JPanel p5 = new JPanel();
        p5.setBounds(320, 250, 400, 300);
        p5.setSize(40, 40);
        p5.setBackground(new Color(247,247,247));
        ImageIcon ogIcon = new ImageIcon(getClass().getResource("/Lib/Img/location.png"));
        Image scaledpng = ogIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon Icon = new ImageIcon(scaledpng);
        JLabel PngLabel = new JLabel(Icon);
        p5.add(PngLabel);

        JPanel p6 = new JPanel();
        p6.setBounds(320, 310, 400, 300);
        p6.setSize(40, 40);
        p6.setBackground(new Color(247,247,247));
        ImageIcon chekIcon2 = new ImageIcon(getClass().getResource("/Lib/Img/location.png"));
        Image scaledcheck = chekIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon Checkicon = new ImageIcon(scaledcheck);
        JLabel CheckLabel = new JLabel(Checkicon);
        p6.add(CheckLabel);

        JPanel p7 = new JPanel();
        p7.setBounds(300, 370, 800, 700);
        p7.setSize(500,1 );
        p7.setBackground(new Color(215,222,228));


        JLabel booking = new JLabel("Booking summary");
        booking.setFont(new Font("Arial", Font.BOLD, 14));
        booking.setBounds(320, 40, 200, 60);

        JLabel check = new JLabel("Please check your booking details before confirming.");
        check.setFont(new Font("Arial", Font.PLAIN, 12));
        check.setBounds(320, 60, 800, 60);

        JLabel detail = new JLabel("Booking detail");
        detail.setFont(new Font("Arial", Font.BOLD, 14));
        detail.setBounds(320, 100, 200, 60);

        JLabel price = new JLabel("Price details");
        price.setFont(new Font("Arial", Font.BOLD, 14));
        price.setBounds(320, 360, 200, 60);

        JLabel rental = new JLabel("rental fee");
        rental.setFont(new Font("Arial", Font.PLAIN, 12));
        rental.setBounds(320, 390, 200, 60);

        JLabel tax = new JLabel("Taxes and fees                                                                                          0.0 baht");
        tax.setFont(new Font("Arial", Font.PLAIN, 12));
        tax.setBounds(320, 420, 2000, 60);

        JPanel p8 = new JPanel();
        p8.setBounds(300, 470, 800, 700);
        p8.setSize(500,1 );
        p8.setBackground(new Color(215,222,228));


        JLabel total = new JLabel("Total");
        total.setFont(new Font("Arial", Font.PLAIN, 12));
        total.setBounds(320, 470, 2000, 60);

        JButton b1 = new JButton("Make payment");
        b1.setFont(new Font("Arial", Font.BOLD, 22));
        b1.setForeground(Color.WHITE);
        b1.setBounds(430,570,250,30);
        b1.setBackground(new Color(215,18,18));
        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Payment();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        
        JPanel back = new JPanel();
        back.setBounds(35, 580, 500, 500);
        back.setSize(60, 60);
        back.setBackground(new Color(235,243,250));
        ImageIcon backIcon2 = new ImageIcon(getClass().getResource("/Lib/Img/back.png"));
        Image scaledback = backIcon2.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon backicon = new ImageIcon(scaledback);
        JLabel backLabel = new JLabel(backicon);
        back.add(backLabel);
        back.addMouseListener(new MouseListener() {
                                  @Override
                                  public void mouseClicked(MouseEvent e) {
                                      f.dispose();
                                      new Reserve();
                                  }

                                  @Override
                                  public void mousePressed(MouseEvent e) {

                                  }

                                  @Override
                                  public void mouseReleased(MouseEvent e) {

                                  }

                                  @Override
                                  public void mouseEntered(MouseEvent e) {

                                  }

                                  @Override
                                  public void mouseExited(MouseEvent e) {

                                  }
                              }

        );
        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 12));
        email.setBounds(35, 625, 2000, 60);


     cp.add(email);   
     cp.add(back);
     cp.add(total);   
     cp.add(p8);
     cp.add(tax);
     cp.add(rental);
     cp.add(price);   
     cp.add(p7);
     cp.add(p6);
     cp.add(p5);
     cp.add(p4);
     cp.add(detail);
     cp.add(p3);
     cp.add(check);   
     cp.add(booking);
     cp.add(b1);
     cp.add(p2);  
     cp.add(l1);
     cp.add(l2);
     cp.add(l3);
     cp.add(p1);
    
     

     f.setSize(900, 700);
     f.setLocationRelativeTo(null);
     f.setResizable(false);
     f.setVisible(true);
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
