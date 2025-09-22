package GoDrive;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Signin implements FocusListener {

    JFrame f = new JFrame("GoDrive");
    Container cp = f.getContentPane();
    JTextField tf1 = new JTextField("UserName");
    JTextField tf2 = new JTextField("Email");
    JPasswordField pf1 = new JPasswordField("Password");
    JPasswordField pf2 = new JPasswordField("Confirm Password");
    char defaultEchoChar;
    

    public Signin() {
        // เก็บค่า echo char เริ่มต้นหลังจากสร้าง object JPasswordField แล้ว
        defaultEchoChar = pf1.getEchoChar();
        Initial();
        setComponents();
        Finally();
    }

    @Override
    public void focusGained(FocusEvent e) {
        // --- แก้ไข: ปรับโครงสร้าง if-else if ที่ซ้อนกันผิด ---
        if (e.getSource() == tf1) {
            if (tf1.getText().equals("UserName")) {
                tf1.setText("");
                tf1.setForeground(Color.BLACK);
            }
        } else if (e.getSource() == tf2) { // <-- แก้ไข: แยกออกมาเป็น else if
            if (tf2.getText().equals("Email")) {
                tf2.setText("");
                tf2.setForeground(Color.BLACK);
            }
        } else if (e.getSource() == pf1) {
            String passwordText = String.valueOf(pf1.getPassword());
            if (passwordText.equals("Password")) {
                pf1.setText("");
                pf1.setForeground(Color.BLACK);
                pf1.setEchoChar(defaultEchoChar);
            }
        } else if (e.getSource() == pf2) { // <-- แก้ไข: เพิ่มโค้ดสำหรับ pf2
            String confirmPasswordText = String.valueOf(pf2.getPassword());
            if (confirmPasswordText.equals("Confirm Password")) {
                pf2.setText("");
                pf2.setForeground(Color.BLACK);
                pf2.setEchoChar(defaultEchoChar);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        // --- แก้ไข: ปรับโครงสร้าง if-else if ที่ซ้อนกันผิด ---
        if (e.getSource() == tf1) {
            if (tf1.getText().isEmpty()) {
                tf1.setText("UserName");
                tf1.setForeground(Color.GRAY);
            }
        } else if (e.getSource() == tf2) { // <-- แก้ไข: แยกออกมาเป็น else if
            if (tf2.getText().isEmpty()) {
                tf2.setText("Email");
                tf2.setForeground(Color.GRAY);
            }
        } else if (e.getSource() == pf1) {
            if (pf1.getPassword().length == 0) {
                pf1.setText("Password");
                pf1.setForeground(Color.GRAY);
                pf1.setEchoChar((char) 0);
            }
        } else if (e.getSource() == pf2) { // <-- แก้ไข: เพิ่มโค้ดสำหรับ pf2
            if (pf2.getPassword().length == 0) {
                pf2.setText("Confirm Password");
                pf2.setForeground(Color.GRAY);
                pf2.setEchoChar((char) 0);
            }
        }
    }

    public void Initial() {
        f.getContentPane().setBackground(new Color(80, 83, 86));
        cp.setLayout(null);
    }

    public void setComponents() {
        JPanel p1 = new JPanel(null);
        p1.setBounds(300, 165, 290, 400);
        p1.setBackground(Color.WHITE);

        JPanel p2 = new JPanel(null);
        p2.setBounds(0, 150, 290, 250);
        p2.setBackground(Color.WHITE); // <-- แก้ไข: เปลี่ยนจากสีเขียวเป็นสีขาว

         // --- จุดแก้ไขที่ 1: โหลดรูปภาพด้วยวิธีที่ถูกต้องและปรับขนาด ---
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Lib/Img/user.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);

        
        
        // 1. วางรูปภาพไว้บนสุด
        imageLabel.setBounds(105, 80, 80, 80); // x, y, width, height


        JLabel l1 = new JLabel("Register");
        l1.setFont(new Font("Arial", Font.PLAIN, 40));
        l1.setBounds(75, 20, 150, 50);

        JLabel l2 = new JLabel("Login");
        l2.setFont(new Font("Arial", Font.PLAIN, 15));
        l2.setBounds(240, -10, 60, 50);
        l2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // เปลี่ยนเมาส์เป็นรูปมือเมื่อชี้

        // --- จุดสำคัญ: เพิ่ม MouseListener ให้กับ JLabel ---
        l2.addMouseListener(new MouseAdapter() {
           
            @Override
            public void mouseClicked(MouseEvent e) {
                // 1. ปิดหน้าต่าง Login ปัจจุบัน
                f.dispose();

                SwingUtilities.invokeLater(() -> {
                // สมมติว่าคุณมีคลาส RegisterFrame.java
                // new RegisterFrame().setVisible(true);
                // หรือถ้าคลาส Register ของคุณชื่ออื่น ให้เปลี่ยนตามนั้น
                new Login().Finally(); // สมมติว่า Register ของคุณมีเมธอดแบบนี้
                });
            }
        });


        tf1.setBounds(20, 20, 250, 30);
        tf1.setForeground(Color.GRAY);

        tf2.setBounds(20, 60, 250, 30);
        tf2.setForeground(Color.GRAY);

        pf1.setForeground(Color.GRAY);
        pf1.setEchoChar((char) 0);
        pf1.setBounds(20, 100, 250, 30);

        pf2.setForeground(Color.GRAY);
        pf2.setEchoChar((char) 0);
        pf2.setBounds(20, 140, 250, 30);

        JButton b1 = new JButton("Register");
        b1.setBounds(100, 190, 85, 30);

        // --- แก้ไข: เพิ่ม FocusListener ให้กับทุก Component ---
        tf1.addFocusListener(this);
        tf2.addFocusListener(this); // <-- เพิ่ม
        pf1.addFocusListener(this);
        pf2.addFocusListener(this); // <-- เพิ่ม
        p1.add(imageLabel); // <-- เพิ่มรูปภาพเข้าไปใน p1
        p1.add(l1);
        p1.add(l2);
        p1.add(p2);

        p2.add(tf1);
        p2.add(tf2);
        p2.add(pf1);
        p2.add(pf2);
        p2.add(b1);
        

        f.add(p1);
    }

    public void Finally() {
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}