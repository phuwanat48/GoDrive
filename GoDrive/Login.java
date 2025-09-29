package GoDrive;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Login implements FocusListener {
    
    JFrame f = new JFrame("GoDrive");
    Container cp = f.getContentPane();
    JTextField tf1 = new JTextField("Username or Email");
    JPasswordField pf1 = new JPasswordField("Password");
    char defaultEchoChar; // <-- ย้ายมาประกาศตรงนี้เพื่อให้ constructor มองเห็น
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
            if (tf1.getText().equals("Username or Email")) {
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
                tf1.setText("Username or Email");
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
    p1.setBackground(Color.WHITE); // เปลี่ยนสีกลับเป็นขาวเพื่อให้สวยงาม

    // Panel รองสำหรับฟอร์ม (เคยเป็นสีเขียว)
    JPanel p2 = new JPanel(null);
    p2.setBounds(0, 180, 290, 150); // ย้าย p2 ลงมาข้างล่าง Label "Login"
    p2.setBackground(Color.white);

    // --- จุดแก้ไขที่ 1: โหลดรูปภาพด้วยวิธีที่ถูกต้องและปรับขนาด ---
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Lib/Img/user.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);

         // 1. วางรูปภาพไว้บนสุด
        imageLabel.setBounds(105, 80, 80, 80); // x, y, width, height

    JLabel l1 = new JLabel("Login");
    l1.setFont(new Font("Arial", Font.PLAIN, 40));
    l1.setBounds(100, 20, 100, 50);

    JLabel l2 = new JLabel("Register");
    l2.setFont(new Font("Arial", Font.PLAIN, 15));
    l2.setBounds(225, -10, 60, 50);

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
                new Signin().Finally(); // สมมติว่า Register ของคุณมีเมธอดแบบนี้
                });
            }
        });


    // --- แก้ไขตำแหน่ง Components ภายใน p2 ---

    // กำหนดตำแหน่ง Username Field ให้เหมาะสม
    // x=20 เพื่อให้มีระยะห่างจากขอบซ้าย 20, width=250 จะพอดีกับขอบขวา (20+250 = 270)
    tf1.setBounds(20, 10, 250, 30); // <-- จุดที่แก้ไข
    tf1.setForeground(Color.GRAY);

    // กำหนดตำแหน่ง Password Field
    pf1.setForeground(Color.GRAY);
    pf1.setEchoChar((char) 0);
    pf1.setBounds(20, 50, 250, 30); // <-- แก้ไข: วางไว้ใต้ Username

    // กำหนดตำแหน่งปุ่ม Login
    JButton b1 = new JButton("Login");
    b1.setBounds(105, 100, 80, 30); // <-- แก้ไข: วางไว้กึ่งกลางข้างล่าง

    // เพิ่ม FocusListener ให้กับทั้งสอง field
    tf1.addFocusListener(this);
    pf1.addFocusListener(this);

    // --- เพิ่ม Components ทั้งหมดกลับเข้าไป ---
    p1.add(imageLabel); // <-- เพิ่มรูปภาพเข้าไปใน p1
    p1.add(l1);
    p1.add(l2);
    p1.add(p2); // เพิ่ม p2 เข้าไปใน p1

    p2.add(tf1);
    p2.add(pf1); // <-- นำกลับมาใช้งาน
    p2.add(b1);  // <-- นำกลับมาใช้งาน

    f.add(p1); // เพิ่ม p1 ลงใน Frame
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

