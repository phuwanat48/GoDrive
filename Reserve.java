import CarCard.Vehicle;
import CarCard.VehicleManager;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Reserve {

    private JFrame f;
    private JTextField tf1, tf2, tf3, tf5, tf6;
    private JComboBox<String> day, month, year, car;
    private Vehicle selectedVehicle;
    private BookingInfo bookingInfo; 

    public Reserve(Vehicle vehicle, BookingInfo bookingInfo) {
        this.selectedVehicle = vehicle;
        this.bookingInfo = bookingInfo; 

        f = new JFrame("GoDrive");
        Container cp = f.getContentPane();
        f.getContentPane().setBackground(new Color(235, 243, 250));
        cp.setLayout(null);

        JLabel l1 = new JLabel("GoDrive");
        l1.setFont(new Font("Arial", Font.BOLD, 29));
        l1.setBounds(25, 25, 126, 25);
        JLabel l2 = new JLabel("Home");
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l2.setBounds(50, 65, 120, 20);
        l2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Date();
            }
        });
        
        JPanel p1 = new JPanel();
        p1.setBounds(200, 0, 600, 50);
        p1.setSize(1500, 700);
        p1.setBackground(Color.WHITE);
        
        JLabel firstname = new JLabel("first name");
        firstname.setBounds(250, 270, 100, 20);
        cp.add(firstname);
        String placeholder1 = "Enter your firstname";
        tf1 = new JTextField(placeholder1, 20);
        tf1.setBounds(250, 290, 250, 30);
        tf1.setForeground(Color.GRAY);
        addPlaceholderListener(tf1, placeholder1);
        cp.add(tf1);
        
        JLabel lastname = new JLabel("last name");
        lastname.setBounds(250, 350, 100, 20);
        cp.add(lastname);
        String placeholder2 = "Enter your lastname";
        tf2 = new JTextField(placeholder2, 20);
        tf2.setBounds(250, 370, 250, 30);
        tf2.setForeground(Color.GRAY);
        addPlaceholderListener(tf2, placeholder2);
        cp.add(tf2);
        JLabel driver = new JLabel("Driver's license  number");
        driver.setBounds(250, 430, 200, 20);
        cp.add(driver);
        String placeholder3 = "Enter your driver's license number";
        tf3 = new JTextField(placeholder3, 20);
        tf3.setBounds(250, 450, 250, 30);
        tf3.setForeground(Color.GRAY);
        addPlaceholderListener(tf3, placeholder3);
        tf3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                String currentText = tf3.getText();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE && c != java.awt.event.KeyEvent.VK_DELETE) {
                    evt.consume();
                } else if (Character.isDigit(c) && currentText.length() >= 8) {
                    evt.consume();
                }
            }
        });
        cp.add(tf3);
        
        // --- ส่วนเลือกวันหมดอายุ (Expiration Date) ---
        JLabel PD = new JLabel("Expiration date");
        PD.setBounds(250, 500, 100, 30);
        
        day = new JComboBox<>();
        day.addItem("DD");
        for (int i = 1; i <= 31; i++) day.addItem(String.valueOf(i));
        day.setBounds(250, 530, 50, 25);
        
        month = new JComboBox<>();
        month.addItem("MM");
        for (int i = 1; i <= 12; i++) month.addItem(String.valueOf(i));
        month.setBounds(300, 530, 50, 25);
        
        year = new JComboBox<>();
        year.addItem("YYYY");
        for (int i = 2569; i <= 2575; i++) year.addItem(String.valueOf(i));
        year.setBounds(350, 530, 60, 25);
        
        car = new JComboBox<>();
        car.addItem("CAR TYPE");
        car.addItem("car");
        car.addItem("motorcycle");
        car.setBounds(410, 530, 90, 25);
        
        cp.add(PD);
        cp.add(day);
        cp.add(month);
        cp.add(year);
        cp.add(car);

        // *** NEW: เพิ่ม Listener ให้ Month และ Year เพื่ออัพเดท Day ***
        ActionListener dateListener = e -> updateDays();
        month.addActionListener(dateListener);
        year.addActionListener(dateListener);
        // *******************************************************
        
        JLabel adress = new JLabel("Email");
        adress.setBounds(550, 350, 100, 20);
        cp.add(adress);
        String placeholder5 = "Enter your email";
        tf5 = new JTextField(placeholder5, 20);
        tf5.setBounds(550, 370, 250, 30);
        tf5.setForeground(Color.GRAY);
        addPlaceholderListener(tf5, placeholder5);
        cp.add(tf5);
        JLabel phone = new JLabel("Phone number");
        phone.setBounds(550, 430, 100, 20);
        cp.add(phone);
        String placeholder6 = "Enter your phone number";
        tf6 = new JTextField(placeholder6, 20);
        tf6.setBounds(550, 450, 250, 30);
        tf6.setForeground(Color.GRAY);
        addPlaceholderListener(tf6, placeholder6);
        tf6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                String currentText = tf6.getText();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE && c != java.awt.event.KeyEvent.VK_DELETE) {
                    evt.consume();
                } else if (Character.isDigit(c) && currentText.length() >= 10) {
                    evt.consume();
                }
            }
        });
        cp.add(tf6);
        
        JButton b1 = new JButton("Booking");
        b1.setFont(new Font("Arial", Font.BOLD, 20));
        b1.setForeground(Color.WHITE);
        b1.setBounds(550, 530, 250, 30);
        b1.setBackground(new Color(215, 18, 18));
        cp.add(b1);
        
        b1.addActionListener(e -> {
            saveBookingAndProceed();
        });
        
        JPanel summaryPanel = new JPanel();
        summaryPanel.setBounds(550, 40, 290, 280);
        summaryPanel.setBackground(new Color(246, 246, 246));
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
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
        
        JLabel brandLabel = new JLabel("Brand: " + selectedVehicle.getBrand());
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel modelLabel = new JLabel("Model: " + selectedVehicle.getModel());
        modelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        modelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = new JLabel("Price: " + selectedVehicle.getPrice());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        summaryPanel.add(Box.createVerticalGlue()); 
        summaryPanel.add(bookingTitle);
        summaryPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        summaryPanel.add(pickupLocationLabel);
        summaryPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        summaryPanel.add(pickupDateTimeLabel);
        summaryPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        summaryPanel.add(returnDateTimeLabel);
        summaryPanel.add(Box.createRigidArea(new Dimension(0, 12))); 
        summaryPanel.add(infoTitle);
        summaryPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        summaryPanel.add(brandLabel);
        summaryPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        summaryPanel.add(modelLabel);
        summaryPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        summaryPanel.add(priceLabel);
        summaryPanel.add(Box.createVerticalGlue()); 

        JPanel p3 = new JPanel();
        p3.setBounds(220, 40, 300, 200);
        p3.setBackground(Color.WHITE);
        
        ImageIcon originalIcon = new ImageIcon(selectedVehicle.getImagePath());
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon carIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(carIcon);
        p3.add(imageLabel);

        JPanel back = new JPanel();
        back.setBounds(35, 580, 60, 60);
        back.setBackground(new Color(235, 243, 250));
        ImageIcon backIcon2 = new ImageIcon(getClass().getResource("/images/back.png"));
        Image scaledback = backIcon2.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon backicon = new ImageIcon(scaledback);
        JLabel backLabel = new JLabel(backicon);
        back.add(backLabel);
        // << เพิ่ม ActionListener ให้ปุ่ม Back
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose(); // ปิดหน้าปัจจุบัน (Reserve)
                VehicleManager vehicleManager = new VehicleManager(); // สร้าง VehicleManager ใหม่
                // เปิดหน้า CarCardIn ใหม่ โดยใช้ manager และ bookingInfo เดิม
                new CarCardIn(vehicleManager, bookingInfo); 
            }
        });
        
            
        
        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 12));
        email.setBounds(35, 625, 200, 60);

        cp.add(email);
        cp.add(back);
        cp.add(p3);
        cp.add(summaryPanel);
        cp.add(l1);
        cp.add(l2);
        
        cp.add(p1);

        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // ******************************************************
    // *** NEW: เมธอดสำหรับอัพเดทจำนวนวันตามเดือนและปี ***
    // ******************************************************
    private void updateDays() {
        String selectedMonthStr = (String) month.getSelectedItem();
        String selectedYearStr = (String) year.getSelectedItem();

        if (selectedMonthStr.equals("MM") || selectedYearStr.equals("YYYY")) {
            // ไม่ต้องทำอะไรหากยังไม่ได้เลือกเดือนหรือปี
            return; 
        }

        try {
            int selectedMonth = Integer.parseInt(selectedMonthStr);
            int selectedYear = Integer.parseInt(selectedYearStr) - 543; // แปลง พ.ศ. เป็น ค.ศ. (เช่น 2569 -> 2026)
            int daysInMonth;

            // ตรวจสอบเดือนที่มี 30 วัน
            if (selectedMonth == 4 || selectedMonth == 6 || selectedMonth == 9 || selectedMonth == 11) {
                daysInMonth = 30;
            } 
            // ตรวจสอบเดือนกุมภาพันธ์
            else if (selectedMonth == 2) {
                // ตรวจสอบปีอธิกสุรทิน (Leap Year)
                boolean isLeap = (selectedYear % 4 == 0 && selectedYear % 100 != 0) || (selectedYear % 400 == 0);
                daysInMonth = isLeap ? 29 : 28;
            } 
            // เดือนที่เหลือมี 31 วัน
            else {
                daysInMonth = 31;
            }
            
            // เก็บวันที่ถูกเลือกไว้ก่อน
            String currentDay = (String) day.getSelectedItem();
            int currentDayInt = (currentDay.equals("DD") || currentDay == null) ? 1 : Integer.parseInt(currentDay);
            
            // ล้าง ComboBox ของวัน
            day.removeAllItems();
            day.addItem("DD");

            // เพิ่มจำนวนวันใหม่
            for (int i = 1; i <= daysInMonth; i++) {
                day.addItem(String.valueOf(i));
            }
            
            // คืนค่าวันที่ถูกเลือกกลับไป หากวันที่นั้นยังอยู่ในช่วงที่ถูกต้อง
            if (currentDayInt <= daysInMonth) {
                day.setSelectedItem(String.valueOf(currentDayInt));
            } else {
                day.setSelectedItem("DD"); // ตั้งค่าเป็น DD หากวันที่เดิมเกินขีดจำกัด
            }


        } catch (NumberFormatException ex) {
            // ควรจะไม่เกิดเพราะเรากรองค่า MM/YYYY ไปแล้ว
            ex.printStackTrace();
        }
    }
    // ******************************************************

    private void addPlaceholderListener(JTextField textField, String placeholder) {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void saveBookingAndProceed() {
        String firstName = tf1.getText();
        String lastName = tf2.getText();
        String licenseNumber = tf3.getText();
        String email = tf5.getText();
        String phone = tf6.getText();
        
        String expDay = (String) day.getSelectedItem();
        String expMonth = (String) month.getSelectedItem();
        String expYear = (String) year.getSelectedItem();
        String carType = (String) car.getSelectedItem();


        // Check for required fields and placeholders
        if (firstName.isEmpty() || lastName.isEmpty() || licenseNumber.isEmpty() || email.isEmpty() || phone.isEmpty() ||
            firstName.equals("Enter your firstname") || lastName.equals("Enter your lastname") ||
            licenseNumber.equals("Enter your driver's license number") || email.equals("Enter your email") ||
            phone.equals("Enter your phone number")) {
            JOptionPane.showMessageDialog(f, "Please fill in all required fields.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // ******************************************************
        // *** NEW: ตรวจสอบความถูกต้องของ Expiration Date (ต้องไม่เป็น DD, MM, YYYY) ***
        if (expDay.equals("DD") || expMonth.equals("MM") || expYear.equals("YYYY") || carType.equals("CAR TYPE")) {
             JOptionPane.showMessageDialog(f, "Please select a valid Expiration Date and Car Type.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // ******************************************************


        // 1. First Name Validation (English letters only, no spaces, no numbers)
        if (!firstName.matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(f, "First name must contain English letters only, no spaces, and no numbers.", "Invalid First Name", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Last Name Validation (English letters only, no spaces, no numbers)
        if (!lastName.matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(f, "Last name must contain English letters only, no spaces, and no numbers.", "Invalid Last Name", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 3. Email Validation (Basic Gmail format check)
        // This regex checks for the basic structure: localpart@domain.tld
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(f, "Please enter a valid email address.", "Invalid Email", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 4. Phone Number Validation (10 digits, no repeating all 10 digits)
        if (phone.length() != 10 || !phone.matches("\\d{10}")) {
              JOptionPane.showMessageDialog(f, "Phone number must be exactly 10 digits.", "Invalid Phone Number", JOptionPane.WARNING_MESSAGE);
              return;
        }
        
        // Check for 10 repeating digits (e.g., 9999999999)
        char firstDigit = phone.charAt(0);
        boolean allSame = true;
        for (int i = 1; i < phone.length(); i++) {
            if (phone.charAt(i) != firstDigit) {
                allSame = false;
                break;
            }
        }
        if (allSame) {
            JOptionPane.showMessageDialog(f, "Phone number cannot consist of 10 identical digits (e.g., 9999999999).", "Invalid Phone Number", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // ******************************************************************
        // *** NEW: บันทึกชื่อและนามสกุลลงใน BookingInfo เพื่อส่งต่อไปหน้า Payment ***
        bookingInfo.setFirstName(firstName);
        bookingInfo.setLastName(lastName);
        // ******************************************************************


        String fileName = "File/personal.csv";
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String pickupDateTime = bookingInfo.getPickupDateTime().format(dataFormatter);
        String returnDateTime = bookingInfo.getReturnDateTime().format(dataFormatter);
        String vehicleBrand = selectedVehicle.getBrand();
        String vehicleModel = selectedVehicle.getModel();
        String vehiclePrice = selectedVehicle.getPrice();
        double totalprice = bookingInfo.calculateTotalPrice(selectedVehicle);
        String status = "Pending";
        
        boolean isSaveSuccessful = false;

        try {
            File file = new File(fileName);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            boolean fileExists = file.exists();
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                if (!fileExists) {
                    writer.write("Status,FirstName,LastName,LicenseNumber,Email,Phone,VehicleBrand,VehicleModel,Price,TotalPrice,PickupDateTime,ReturnDateTime");
                    writer.newLine();
                }

                String line = String.join(",", 
                    "\"" + status + "\"", "\"" + firstName + "\"", "\"" + lastName + "\"", "\"" + licenseNumber + "\"", 
                    "\"" + email + "\"", "\"" + phone + "\"", "\"" + vehicleBrand + "\"",
                    "\"" + vehicleModel + "\"", "\"" + vehiclePrice + "\"", "\"" + totalprice +"\"", "\"" + pickupDateTime + "\"",
                    "\"" + returnDateTime + "\""
                );
                
                writer.write(line);
                writer.newLine(); 
                isSaveSuccessful = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Could not save booking to file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if (isSaveSuccessful) {
            
            new Recheck(selectedVehicle, bookingInfo); 
            f.dispose();
            
            // เพื่อให้โค้ดรันได้โดยไม่ติด Error ถ้าไม่มี Recheck class:
            JOptionPane.showMessageDialog(f, "Booking saved successfully! Proceeding to the next step.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}