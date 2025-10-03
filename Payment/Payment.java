import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

public class Payment {
    public Payment() {
        JFrame f = new JFrame("GoDrive");
        Container cp = f.getContentPane();
        cp.setLayout(new BorderLayout(10, 10));
        cp.setBackground(new Color(235, 243, 250));

        // --- Panel เมนูด้านซ้าย ---
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(235, 243, 250));
        menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        menu.setPreferredSize(new Dimension(200, 0));

        JLabel name = new JLabel("GoDrive");
        name.setFont(new Font("Arial", Font.BOLD, 29));
        
        JLabel home = new JLabel("Home");
        home.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel rent = new JLabel("Rent");
        rent.setFont(new Font("Arial", Font.BOLD, 18));
        
        menu.add(name);
        menu.add(Box.createRigidArea(new Dimension(0, 40)));
        menu.add(home);
        menu.add(Box.createRigidArea(new Dimension(0, 15)));
        menu.add(rent);
        menu.add(Box.createVerticalGlue());
        
        String arrowIconPath = "images/back.png";
        ImageIcon originalArrowIcon = new ImageIcon(getClass().getClassLoader().getResource(arrowIconPath));
        Image resizedArrowImage = originalArrowIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon arrowIcon = new ImageIcon(resizedArrowImage);
        JButton refreshButton = new JButton(arrowIcon);
        refreshButton.setBorderPainted(false);
        refreshButton.setContentAreaFilled(false);
        refreshButton.setFocusPainted(false);
        refreshButton.setOpaque(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.addActionListener(e -> {
            System.out.println("Refresh click!");
            JOptionPane.showMessageDialog(f, "Are you sure you want to go back? ");
        });
        menu.add(refreshButton);

        // --- Panel หลักตรงกลาง ---
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        // Panel สีเทาที่ครอบ QR Code
        JPanel QrPanel = new JPanel();
        QrPanel.setLayout(null);
        QrPanel.setBounds(50, 60, 330, 450);
        QrPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(QrPanel);

       JPanel Booking = new JPanel();
       Booking.setBounds(640, 60, 210, 300);
       Booking.setBackground(new Color(245, 245, 245));
       cp.add(Booking);

        // Panel สำหรับรูป QR Code
        JPanel Qr = new JPanel();
        Qr.setLayout(new BorderLayout()); 
        Qr.setBounds(42, 30, 250, 300); 
        Qr.setBackground(Color.white);
        QrPanel.add(Qr);

        // รูปภาพ QR Code
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/Qr.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH); 
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);
        Qr.add(imageLabel, BorderLayout.CENTER);

        // ปุ่ม "แนบสลิป"
        JButton attachSlipButton = new JButton("Upload a picture");
        attachSlipButton.setFont(new Font("Arial", Font.BOLD, 16));
        attachSlipButton.setBackground(new Color(205, 51, 51));
        attachSlipButton.setForeground(Color.WHITE);
        attachSlipButton.setFocusPainted(false);
        attachSlipButton.setBorderPainted(false);
        attachSlipButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        attachSlipButton.setBounds(50, 530, 330, 40);
        mainPanel.add(attachSlipButton);

        // 3. แก้ไข ActionListener ของปุ่ม "แนบสลิป"
        attachSlipButton.addActionListener(e -> {
            // สร้างตัวเลือกไฟล์
            JFileChooser fileChooser = new JFileChooser();
            // สร้าง Filter ให้เลือกได้เฉพาะไฟล์รูปภาพ
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                "Image files", "jpg", "png", "gif", "jpeg");
            fileChooser.setFileFilter(imageFilter);
            
            // แสดงหน้าต่างเลือกไฟล์
            int option = fileChooser.showOpenDialog(f);
          
        });
       

        // --- การจัดวาง Layout หลัก ---
        cp.add(menu, BorderLayout.WEST);
        cp.add(mainPanel, BorderLayout.CENTER);
        
        // --- ส่วนท้าย ---
        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 13));
        email.setBorder(new EmptyBorder(0, 35, 2, 0));
        cp.add(email, BorderLayout.SOUTH);
        
        // --- ตั้งค่า Frame ---
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}