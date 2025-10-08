package GoDrive.Payment;
import GoDrive.Personal;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

public class Payment {

    private JFrame f;
    private Container cp;
    private JButton confirmButton;
    private JLabel fileNameLabel;

    public Payment() {
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
        
        ImageIcon originalArrowIcon = new ImageIcon(getClass().getResource("/GoDrive/Payment/imagess/back.png"));
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
            f.dispose();
            new Personal();
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
        
        // --- Panel สรุปข้อมูลด้านขวา (นำกลับมาครบถ้วน) ---
        JPanel Booking = new JPanel();
        Booking.setBounds(410, 60, 240, 350);
        Booking.setBackground(new Color(245, 245, 245));
        Booking.setLayout(new BoxLayout(Booking, BoxLayout.Y_AXIS));
        Booking.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Booking Summary");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
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

        JLabel carTypeTitle = new JLabel("Car Type:");
        carTypeTitle.setFont(new Font("Arial", Font.BOLD, 12));
        carTypeTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField carTypeField = new JTextField("... awaiting data ...");
        carTypeField.setFont(new Font("Arial", Font.PLAIN, 12));
        carTypeField.setEditable(false);
        carTypeField.setBackground(new Color(245, 245, 245));
        carTypeField.setBorder(null);
        carTypeField.setAlignmentX(Component.LEFT_ALIGNMENT);

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
        mainPanel.add(Booking);

        // --- ส่วน QR Code ---
        JPanel Qr = new JPanel();
        Qr.setLayout(new BorderLayout()); 
        Qr.setBounds(42, 30, 250, 300); 
        Qr.setBackground(Color.white);
        QrPanel.add(Qr);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/GoDrive/Payment/imagess/Qr.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH); 
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);
        Qr.add(imageLabel, BorderLayout.CENTER);

        // --- ส่วนปุ่ม Upload และ Confirm ---
        JButton attachSlipButton = new JButton("Upload a picture");
        attachSlipButton.setFont(new Font("Arial", Font.BOLD, 16));
        attachSlipButton.setBackground(new Color(205, 51, 51));
        attachSlipButton.setForeground(Color.WHITE);
        attachSlipButton.setFocusPainted(false);
        attachSlipButton.setBorderPainted(false);
        attachSlipButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        attachSlipButton.setBounds(50, 530, 330, 40);
        mainPanel.add(attachSlipButton);

        fileNameLabel = new JLabel("Please upload a payment slip.");
        fileNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        fileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fileNameLabel.setBounds(50, 575, 330, 20);
        mainPanel.add(fileNameLabel);

        confirmButton = new JButton("Confirm Payment");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(new Color(0, 153, 51));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBounds(50, 600, 330, 40);
        confirmButton.setEnabled(false);
        mainPanel.add(confirmButton);

        attachSlipButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "png", "jpeg");
            fileChooser.setFileFilter(imageFilter);
            int result = fileChooser.showOpenDialog(f);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fileNameLabel.setText("File: " + selectedFile.getName());
                confirmButton.setEnabled(true);
            }
        });

        confirmButton.addActionListener(e -> {
            new waitPayment(); // ไปยังหน้า waitPayment
            f.dispose();
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