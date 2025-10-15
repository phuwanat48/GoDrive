



import CarCard.CarCardPN;
import CarCard.VehicleManager;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Login implements FocusListener {
    private VehicleManager manager;
    private CarCardPN carPanel;
    private CarCardPN motorcyclePanel;
    JFrame f = new JFrame("GoDrive");
    Container cp = f.getContentPane();
    JTextField tf1 = new JTextField("Username");
    JPasswordField pf1 = new JPasswordField("Password");
    char defaultEchoChar; // <-- ย้ายมาประกาศตรงนี้เพื่อให้ constructor มองเห็น
    JButton b1;
    ImageIcon img = new ImageIcon("../Lib/Img/logo_notext.png");

    public Login() {
        // เก็บค่า echo char เริ่มต้นหลังจากสร้าง object JPasswordField แล้ว
        defaultEchoChar = pf1.getEchoChar();
        Initial();
        setComponents();
        Finally();
    }

    

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == tf1) {
            if (tf1.getText().equals("Username")) {
                tf1.setText("");
                tf1.setForeground(Color.BLACK); // <-- แก้ไข: เปลี่ยนสีตัวอักษรเป็นสีดำ
            }
        } else if (e.getSource() == pf1) {
            // <-- แก้ไข: ใช้ String.valueOf(pf1.getPassword()) ในการเปรียบเทียบ
            String passwordText = String.valueOf(pf1.getPassword());
            if (passwordText.equals("Password")) {
                pf1.setText("");
                pf1.setForeground(Color.BLACK); // <-- แก้ไข: เปลี่ยนสีตัวอักษรเป็นสีดำ
                pf1.setEchoChar(defaultEchoChar); // <-- แก้ไข: กลับไปใช้โหมดซ่อนรหัส
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == tf1) {
            // <-- แก้ไข: ใช้ .isEmpty() เพื่อความชัดเจน
            if (tf1.getText().isEmpty()) {
                tf1.setText("Username");
                tf1.setForeground(Color.GRAY); // <-- แก้ไข: เปลี่ยนสี placeholder เป็นสีเทา
            }
        } else if (e.getSource() == pf1) {
            // <-- แก้ไข: ใช้ .getPassword().length == 0 ในการเช็คค่าว่าง
            if (pf1.getPassword().length == 0) {
                pf1.setText("Password");
                pf1.setForeground(Color.GRAY); // <-- แก้ไข: เปลี่ยนสี placeholder เป็นสีเทา
                pf1.setEchoChar((char) 0); // <-- แก้ไข: ทำให้ข้อความ placeholder มองเห็นได้
            }
        }
    }

    public void Initial() {
        f.getContentPane().setBackground(new Color(80, 83, 86));
        cp.setLayout(null);
    }

    public void setComponents() {
        // Panel หลัก (เคยเป็นสีแดง)
        JPanel p1 = new JPanel(null);
        p1.setBounds(300, 165, 290, 350);
        p1.setBackground(Color.WHITE);

        // Panel รองสำหรับฟอร์ม (เคยเป็นสีเขียว)
        JPanel p2 = new JPanel(null);
        p2.setBounds(0, 180, 290, 150);
        p2.setBackground(Color.white);

        // --- โหลดรูปภาพและปรับขนาด ---
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/user.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);
        imageLabel.setBounds(110, 80, 80, 80);

        JLabel l1 = new JLabel("ADMIN LOGIN");
        l1.setFont(new Font("Arial", Font.PLAIN, 30));
        l1.setBounds(50, 20, 200, 50);

        // --- แก้ไขตำแหน่ง Components ภายใน p2 ---
        tf1.setBounds(20, 10, 250, 30);
        tf1.setForeground(Color.GRAY);

        pf1.setForeground(Color.GRAY);
        pf1.setEchoChar((char) 0);
        pf1.setBounds(20, 50, 250, 30);

        // กำหนดตำแหน่งปุ่ม Login (ใช้ b1 ที่ประกาศไว้เป็น instance variable)
        b1 = new JButton("Login");
        b1.setBounds(105, 100, 80, 30);


        // เพิ่ม FocusListener ให้กับทั้งสอง field
        tf1.addFocusListener(this);
        pf1.addFocusListener(this);

        // --- << จุดที่เพิ่มโค้ด Action Listener >> ---
        b1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String username = tf1.getText();
                String password = String.valueOf(pf1.getPassword());

                if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(f, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    VehicleManager manager = new VehicleManager();
                    new Addnewcar(manager, null, null);

                } else {
                    JOptionPane.showMessageDialog(f, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- เพิ่ม Components ทั้งหมดกลับเข้าไป ---
        p1.add(imageLabel);
        p1.add(l1);
        p1.add(p2);

        p2.add(tf1);
        p2.add(pf1);
        p2.add(b1);

        f.add(p1);
    }

    public void Finally() {
        
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setIconImage(img.getImage());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  
}

