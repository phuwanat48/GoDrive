package GoDrive.Payment;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class crPayment {
    private JFrame f;
    private Container cp;
    
    //Constructor ของคลาส จะเรียกเมธอดต่างๆ ตามลำดับ
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
        
        String arrowIconPath = "GoDrive/Payment/imagess/back.png";
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

        JLabel sus = new JLabel("Your car booking is confirmed.");
        sus.setBounds(80, 400, 300, 40);
        sus.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(sus);

        // Panel สีเทาที่ครอบรูปภาพ
        JPanel CrPanel = new JPanel();
        CrPanel.setLayout(null);
        CrPanel.setBounds(50, 60, 330, 450);
        CrPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(CrPanel);

        // 1. สร้าง Panel สรุปข้อมูลด้านขวา
        JPanel Booking = new JPanel();
        Booking.setBounds(410, 60, 240, 350);
        Booking.setBackground(new Color(245, 245, 245));
        Booking.setLayout(new BoxLayout(Booking, BoxLayout.Y_AXIS));
        Booking.setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- หัวข้อ ---
        JLabel titleLabel = new JLabel("Booking Summary");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // --- ข้อมูลการรับรถ ---
        JLabel pickupDateTitle = new JLabel("Pickup Date & Time:");
        pickupDateTitle.setFont(new Font("Arial", Font.BOLD, 12));
        pickupDateTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField pickupDateField = new JTextField("... awaiting data ...");
        pickupDateField.setFont(new Font("Arial", Font.PLAIN, 12));
        pickupDateField.setEditable(false);
        pickupDateField.setBackground(new Color(245, 245, 245));
        pickupDateField.setBorder(null);
        pickupDateField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel pickupLocationTitle = new JLabel("Pickup Location:");
        pickupLocationTitle.setFont(new Font("Arial", Font.BOLD, 12));
        pickupLocationTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField pickupLocationField = new JTextField("... awaiting data ...");
        pickupLocationField.setFont(new Font("Arial", Font.PLAIN, 12));
        pickupLocationField.setEditable(false);
        pickupLocationField.setBackground(new Color(245, 245, 245));
        pickupLocationField.setBorder(null);
        pickupLocationField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- ข้อมูลรถ ---
        JLabel carTypeTitle = new JLabel("Car Type:");
        carTypeTitle.setFont(new Font("Arial", Font.BOLD, 12));
        carTypeTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField carTypeField = new JTextField("... awaiting data ...");
        carTypeField.setFont(new Font("Arial", Font.PLAIN, 12));
        carTypeField.setEditable(false);
        carTypeField.setBackground(new Color(245, 245, 245));
        carTypeField.setBorder(null);
        carTypeField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- เพิ่มคอมโพเนนต์ทั้งหมดลงใน Booking Panel ---
        Booking.add(titleLabel);
        Booking.add(Box.createRigidArea(new Dimension(0, 20)));
        Booking.add(pickupDateTitle);
        Booking.add(pickupDateField);
        Booking.add(Box.createRigidArea(new Dimension(0, 15)));
        Booking.add(pickupLocationTitle);
        Booking.add(pickupLocationField);
        Booking.add(Box.createRigidArea(new Dimension(0, 15)));
        Booking.add(carTypeTitle);
        Booking.add(carTypeField);
        
        // 2.  นำ Booking panel ไปใส่ใน mainPanel
        mainPanel.add(Booking);

        // Panel สำหรับรูปภาพ
        JPanel cr = new JPanel();
        cr.setLayout(new BorderLayout()); 
        cr.setBounds(80, 80, 180, 180); 
        cr.setBackground(new Color(245, 245, 245));
        CrPanel.add(cr);

        // รูปภาพ Correct
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("GoDrive/Payment/imagess/correct.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);
        cr.add(imageLabel, BorderLayout.CENTER);

        // ปุ่ม "Return to Homepage"
        JButton attachSlipButton = new JButton("Return to Homepage");
        attachSlipButton.setFont(new Font("Arial", Font.BOLD, 16));
        attachSlipButton.setBackground(new Color(205, 51, 51));
        attachSlipButton.setForeground(Color.WHITE);
        attachSlipButton.setFocusPainted(false);
        attachSlipButton.setBorderPainted(false);
        attachSlipButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        attachSlipButton.setBounds(50, 530, 330, 40);
        mainPanel.add(attachSlipButton);
        
        // --- การจัดวาง Layout หลัก ---
        cp.add(menu, BorderLayout.WEST);
        cp.add(mainPanel, BorderLayout.CENTER);
        
        // --- ส่วนท้าย ---
        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 13));
        email.setBorder(new EmptyBorder(0, 35, 2, 0));
        cp.add(email, BorderLayout.SOUTH);
    }

    /**
     * เมธอดสำหรับตั้งค่าสุดท้ายของ Frame ก่อนแสดงผล
     */
    public void Finally() {
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

}