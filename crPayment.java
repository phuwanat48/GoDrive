// crPayment.java (Confirmed Payment Success Screen)

import CarCard.Vehicle;
import java.awt.*; // ต้องมีการ Import นี้
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.*;
// สมมติว่าคลาส BookingInfo อยู่ใน Package เดียวกัน หรือถูก Import ไว้แล้ว
// ถ้า BookingInfo อยู่ใน package อื่น ต้อง Import ให้ถูกต้องด้วย เช่น import myproject.BookingInfo;

public class crPayment {
    private JFrame f;
    private Container cp;
    
    // 💡 FIX: ประกาศ Field เพื่อให้ทุกเมธอดเข้าถึงข้อมูลได้
    private Vehicle vehicle; 
    private BookingInfo bookingInfo; 
    
    // 🛠️ FIX: ใช้ Constructor ที่รับค่ามา และเก็บค่าไว้ใน Field
    public crPayment(Vehicle vehicle, BookingInfo bookingInfo) {
        this.vehicle = vehicle;
        this.bookingInfo = bookingInfo;
        
        initial();
        setComponent();
        Finally();
    }
    
    // **Constructor สำรอง (ถ้าต้องการทดสอบโดยไม่มีข้อมูล)**
    // public crPayment() {
    //     // ตั้งค่า dummy data สำหรับทดสอบ ถ้าคลาส Vehicle และ BookingInfo มี Constructor ว่าง
    //     // this.vehicle = new Vehicle("Toyota", "Vios", 500.0, "/images/default_car.png");
    //     // this.bookingInfo = new BookingInfo("Airport", "Pic-up: 2024-12-01 10:00", "Return: 2024-12-05 10:00");
    //     // initial();
    //     // setComponent();
    //     // Finally();
    // }
    
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

        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Date();
            }
            
        });
        
        menu.add(name);
        menu.add(Box.createRigidArea(new Dimension(0, 40)));
        menu.add(home);
        menu.add(Box.createRigidArea(new Dimension(0, 15)));
        
        
        // --- Panel หลักตรงกลาง ---
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        // --- Panel สีเทาด้านซ้าย (Confirmation) ---
        JPanel CrPanel = new JPanel();
        CrPanel.setLayout(null);
        CrPanel.setBounds(40, 80, 300, 360);
        CrPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(CrPanel);

        // --- Panel สรุปข้อมูลด้านขวา ---
        JPanel Booking = new JPanel();
        Booking.setBounds(360, 80, 290, 480);
        Booking.setBackground(new Color(245, 245, 245));
        Booking.setLayout(new BoxLayout(Booking, BoxLayout.Y_AXIS));
        Booking.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JLabel bookingTitle = new JLabel("Booking Summary");
        bookingTitle.setFont(new Font("Arial", Font.BOLD, 16));
        bookingTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // 🛠️ FIX: ใช้ this.bookingInfo
        JLabel pickupLocationLabel = new JLabel("Location: " + this.bookingInfo.getPickupLocation());
        pickupLocationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        pickupLocationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // 🛠️ FIX: ใช้ this.bookingInfo
        JLabel pickupDateTimeLabel = new JLabel(this.bookingInfo.getPickupDateTimeString());
        pickupDateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        pickupDateTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // 🛠️ FIX: ใช้ this.bookingInfo
        JLabel returnDateTimeLabel = new JLabel(this.bookingInfo.getReturnDateTimeString());
        returnDateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        returnDateTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel infoTitle = new JLabel("Vehicle Information");
        infoTitle.setFont(new Font("Arial", Font.BOLD, 16));
        infoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // 🛠️ FIX: ใช้ this.vehicle
        JLabel brandLabel = new JLabel("Brand: " + this.vehicle.getBrand());
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // 🛠️ FIX: ใช้ this.vehicle
        JLabel modelLabel = new JLabel("Model: " + this.vehicle.getModel());
        modelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        modelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 🛠️ FIX: ใช้ this.vehicle
        JLabel priceLabel = new JLabel("Price: " + this.vehicle.getPrice());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Panel สำหรับรูปรถ
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(245, 245, 245));
        imagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // 🛠️ FIX: ใช้ this.vehicle
        ImageIcon carIcon = new ImageIcon(this.vehicle.getImagePath()); 
        Image scaledImageCar = carIcon.getImage().getScaledInstance(260, 150, Image.SCALE_SMOOTH);
        JLabel carImageLabel = new JLabel(new ImageIcon(scaledImageCar)); // เปลี่ยนชื่อตัวแปรเป็น carImageLabel
        imagePanel.add(carImageLabel, BorderLayout.CENTER); 

        // เพิ่ม Component เข้า Booking Panel
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
        Booking.add(imagePanel);
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
        returnButton.addActionListener(e -> {
            f.dispose(); // ปิดหน้าปัจจุบัน
            new Date(); // เปิดหน้า Date (Homepage)
        });
        
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