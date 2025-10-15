import CarCard.Vehicle;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class waitPayment {
    private JFrame f;
    private Container cp;
    
    public waitPayment(Vehicle vehicle, BookingInfo bookingInfo) {
        initial();
        setComponent(vehicle, bookingInfo);
        Finally();
    }
   
    public void initial() {
        f = new JFrame("GoDrive");
        cp = f.getContentPane();
        cp.setLayout(new BorderLayout(10, 10));
        cp.setBackground(new Color(235, 243, 250));
    }

    public void setComponent(Vehicle vehicle, BookingInfo bookingInfo) {
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
        
        ImageIcon originalArrowIcon = new ImageIcon(getClass().getResource("/images/back.png"));
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
        
     // --- รูปภาพ Correct ---
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/correct.png"));
        Image scaledImage1 = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH); 
        ImageIcon Correct = new ImageIcon(scaledImage1);
        JLabel CorrectLabel = new JLabel(Correct);
        CrPanel.add(CorrectLabel, BorderLayout.CENTER);
        
        mainPanel.add(CrPanel);

        // --- Panel สรุปข้อมูลด้านขวา ---
        JPanel Booking = new JPanel();
        Booking.setBounds(360, 80, 290, 480); // << CHANGED: เพิ่มความสูง
        Booking.setBackground(new Color(245, 245, 245));
        Booking.setLayout(new BoxLayout(Booking, BoxLayout.Y_AXIS));
        Booking.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel bookingTitle = new JLabel("Booking Summary");
        bookingTitle.setFont(new Font("Arial", Font.BOLD, 16));
        bookingTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel pickupLocationLabel = new JLabel("Location: " + bookingInfo.getPickupLocation());
        pickupLocationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        pickupLocationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel pickupDateTimeLabel = new JLabel(bookingInfo.getPickupDateTimeString());
        pickupDateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        pickupDateTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel returnDateTimeLabel = new JLabel(bookingInfo.getReturnDateTimeString());
        returnDateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        returnDateTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel infoTitle = new JLabel("Vehicle Information");
        infoTitle.setFont(new Font("Arial", Font.BOLD, 16));
        infoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel brandLabel = new JLabel("Brand: " + vehicle.getBrand());
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel modelLabel = new JLabel("Model: " + vehicle.getModel());
        modelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        modelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = new JLabel("Price: " + vehicle.getPrice());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // << NEW: Panel สำหรับรูปรถ
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(245, 245, 245));
        imagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ImageIcon carIcon = new ImageIcon(vehicle.getImagePath());
        Image scaledImage = carIcon.getImage().getScaledInstance(260, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        Booking.add(bookingTitle);
        Booking.add(Box.createRigidArea(new Dimension(0, 8)));
        Booking.add(pickupLocationLabel);
        Booking.add(Box.createRigidArea(new Dimension(0, 4)));
        Booking.add(pickupDateTimeLabel);
        Booking.add(Box.createRigidArea(new Dimension(0, 4)));
        Booking.add(returnDateTimeLabel);
        Booking.add(Box.createRigidArea(new Dimension(0, 15))); 
        Booking.add(infoTitle);
        Booking.add(Box.createRigidArea(new Dimension(0, 8)));
        Booking.add(brandLabel);
        Booking.add(Box.createRigidArea(new Dimension(0, 4)));
        Booking.add(modelLabel);
        Booking.add(Box.createRigidArea(new Dimension(0, 4)));
        Booking.add(priceLabel);
        Booking.add(Box.createRigidArea(new Dimension(0, 15)));
        Booking.add(imagePanel); // << NEW: เพิ่ม Panel รูปภาพ

        mainPanel.add(Booking);
        
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