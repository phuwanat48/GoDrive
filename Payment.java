import CarCard.Vehicle;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

public class Payment {

    private JFrame f;
    private Container cp;
    private JButton confirmButton;
    private JLabel fileNameLabel;
    private File selectedFile; 

    public Payment(Vehicle vehicle, BookingInfo bookingInfo) {
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

        // --- Panel สีเทาที่ครอบ QR Code ---
        JPanel QrPanel = new JPanel();
        QrPanel.setLayout(null);
        QrPanel.setBounds(40, 80, 300, 360); 
        QrPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(QrPanel);
        
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
        JLabel imageLabelCar = new JLabel(new ImageIcon(scaledImage));
        imagePanel.add(imageLabelCar, BorderLayout.CENTER);

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
        Booking.add(Box.createVerticalGlue()); // << CHANGED: ย้ายตัวยืดขึ้นมา
        Booking.add(imagePanel); // << NEW: เพิ่ม Panel รูปภาพ
        
        mainPanel.add(Booking);

        // --- ส่วน QR Code ---
        JPanel Qr = new JPanel();
        Qr.setLayout(new BorderLayout());  
        Qr.setBounds(25, 20, 250, 300);  
        Qr.setBackground(Color.white);
        QrPanel.add(Qr);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/Qr.jpg"));
        Image scaledQrImage = originalIcon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);  
        ImageIcon userIcon = new ImageIcon(scaledQrImage);
        JLabel imageLabelQr = new JLabel(userIcon);
        Qr.add(imageLabelQr, BorderLayout.CENTER);
        
        JLabel totalLabel = new JLabel("Total : "+ bookingInfo.calculateTotalPrice(vehicle)+" THB");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setBounds(25, 330,500 , 20); 
        QrPanel.add(totalLabel);

        // --- ส่วนปุ่ม Upload และ Confirm ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(40, 460, 300, 150); 
        buttonPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(buttonPanel);

        JButton attachSlipButton = new JButton("Upload a picture");
        attachSlipButton.setFont(new Font("Arial", Font.BOLD, 16));
        attachSlipButton.setBackground(new Color(205, 51, 51));
        attachSlipButton.setForeground(Color.WHITE);
        attachSlipButton.setFocusPainted(false);
        attachSlipButton.setBorderPainted(false);
        attachSlipButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        attachSlipButton.setBounds(20, 15, 260, 35);
        buttonPanel.add(attachSlipButton);

        fileNameLabel = new JLabel("Please upload a payment slip.");
        fileNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        fileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fileNameLabel.setBounds(20, 55, 260, 20);
        buttonPanel.add(fileNameLabel);

        confirmButton = new JButton("Confirm Payment");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(new Color(0, 153, 51));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBounds(20, 85, 260, 35);
        confirmButton.setEnabled(false);
        buttonPanel.add(confirmButton);

        attachSlipButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "png", "jpeg");
            fileChooser.setFileFilter(imageFilter);
            int result = fileChooser.showOpenDialog(f);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileNameLabel.setText("File: " + selectedFile.getName());
                confirmButton.setEnabled(true);
            }
        });

        confirmButton.addActionListener(e -> {
            saveSlipAndProceed(vehicle, bookingInfo); 
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
    
    private void saveSlipAndProceed(Vehicle vehicle, BookingInfo bookingInfo) {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(f, "Please upload a slip first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            File slipsDir = new File("slips");
            if (!slipsDir.exists()) {
                slipsDir.mkdir();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String originalFileName = selectedFile.getName();
            String fileExtension = "";
            int i = originalFileName.lastIndexOf('.');
            if (i > 0) {
                fileExtension = originalFileName.substring(i);
            }
            String newFileName = "slip_" + timeStamp + fileExtension;

            Path sourcePath = selectedFile.toPath();
            Path destinationPath = Paths.get(slipsDir.getAbsolutePath(), newFileName);

            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("Slip saved to: " + destinationPath.toString());
            
            new waitPayment(vehicle, bookingInfo); 
            f.dispose();

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(f, "Failed to save the slip.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Finally() {
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}