import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class crPayment {
    private JFrame f;
    private Container cp;
    
    // << CHANGED: กลับมาใช้ Constructor แบบเดิม
    public crPayment() {
        initial();
        setComponent();
        Finally();
    }
   
    public void initial() {
        f = new JFrame("GoDrive");
        cp = f.getContentPane();
        cp.setLayout(new BorderLayout(10, 10));
        cp.setBackground(new Color(235, 243, 250));
    }

    // << CHANGED: กลับมาใช้เมธอดแบบเดิม
    public void setComponent() {
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
        ImageIcon originalArrowIcon = new ImageIcon(getClass().getResource("/" + arrowIconPath));
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

        // --- Panel สีเทาด้านซ้าย ---
        JPanel CrPanel = new JPanel();
        CrPanel.setLayout(null);
        CrPanel.setBounds(40, 80, 300, 360);
        CrPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(CrPanel);

        // --- Panel สรุปข้อมูลด้านขวา (ว่าง) ---
        JPanel Booking = new JPanel();
        Booking.setBounds(360, 80, 290, 480);
        Booking.setBackground(new Color(245, 245, 245));
        Booking.setLayout(new BoxLayout(Booking, BoxLayout.Y_AXIS));
        Booking.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.add(Booking);

        // --- Panel สำหรับรูปภาพติ๊กถูก ---
        JPanel cr = new JPanel();
        cr.setLayout(new BorderLayout()); 
        cr.setBounds(60, 40, 180, 180); 
        cr.setBackground(new Color(245, 245, 245));
        CrPanel.add(cr);

        // --- รูปภาพ Correct ---
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/correct.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH); 
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);
        cr.add(imageLabel, BorderLayout.CENTER);
        
        // --- ข้อความ Confirmation ---
        JLabel sus = new JLabel("Your car booking is confirmed.");
        sus.setHorizontalAlignment(SwingConstants.CENTER);
        sus.setBounds(0, 250, 300, 40);
        sus.setFont(new Font("Arial", Font.BOLD, 18));
        CrPanel.add(sus);

        // --- ปุ่ม "Return to Homepage" ---
        JButton returnButton = new JButton("Return to Homepage");
        returnButton.setFont(new Font("Arial", Font.BOLD, 16));
        returnButton.setBackground(new Color(205, 51, 51));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnButton.setBounds(40, 460, 300, 40);
        mainPanel.add(returnButton);
        
        // --- การจัดวาง Layout หลัก ---
        cp.add(menu, BorderLayout.WEST);
        cp.add(mainPanel, BorderLayout.CENTER);
        
        // --- ส่วนท้าย ---
        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 13));
        email.setBorder(new EmptyBorder(0, 35, 2, 0));
        cp.add(email, BorderLayout.SOUTH);
    }

    public void Finally() {
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}