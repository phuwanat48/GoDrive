import CarCard.Vehicle;
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
        JLabel l3 = new JLabel("Rent");
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        l3.setBounds(50, 105, 120, 20);
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
        JLabel driver = new JLabel("Driver's license  number");
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
        for (int i = 2568; i <= 2575; i++) year.addItem(String.valueOf(i));
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
        
        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 12));
        email.setBounds(35, 625, 200, 60);

        cp.add(email);
        cp.add(back);
        cp.add(p3);
        cp.add(summaryPanel);
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
        if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf5.getText().isEmpty() || tf6.getText().isEmpty() ||
            tf1.getText().equals("Enter your firstname") || tf2.getText().equals("Enter your lastname")) {
            JOptionPane.showMessageDialog(f, "Please fill in all required fields.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String fileName = "File/personal.csv";
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String pickupDateTime = bookingInfo.getPickupDateTime().format(dataFormatter);
        String returnDateTime = bookingInfo.getReturnDateTime().format(dataFormatter);
        String vehicleBrand = selectedVehicle.getBrand();
        String vehicleModel = selectedVehicle.getModel();
        String vehiclePrice = selectedVehicle.getPrice();
        String firstName = tf1.getText();
        String lastName = tf2.getText();
        String licenseNumber = tf3.getText();
        String email = tf5.getText();
        String phone = tf6.getText();
        
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
                    writer.write("FirstName,LastName,LicenseNumber,Email,Phone,VehicleBrand,VehicleModel,Price,PickupDateTime,ReturnDateTime");
                    writer.newLine();
                }

                String line = String.join(",", 
                    "\"" + firstName + "\"", "\"" + lastName + "\"", "\"" + licenseNumber + "\"", 
                    "\"" + email + "\"", "\"" + phone + "\"", "\"" + vehicleBrand + "\"",
                    "\"" + vehicleModel + "\"", "\"" + vehiclePrice + "\"", "\"" + pickupDateTime + "\"",
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
        }
    }
}