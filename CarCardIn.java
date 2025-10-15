// FileName: CarCardIn.java (Modified to save as CSV)

import CarCard.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CarCardIn extends JFrame {

    private JPanel dynamic;
    public CarCardPN carPanel;
    public CarCardPN motorcyclePanel;
    private JComboBox<String> typeComboBox;
    private VehicleManager manager;
    private BookingInfo bookingInfo;

    public CarCardIn(VehicleManager manager, BookingInfo bookingInfo) {
        super("GoDrive");
        this.manager = manager;
        this.bookingInfo = bookingInfo;
        Initial();
        setComponent();
        Finally();
    }

    public CarCardPN getCarPanel() {
        return carPanel;
    }
    public CarCardPN getMotorcyclePanel() {
        return motorcyclePanel;
    }

    private void Initial() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(10, 10));
        cp.setBackground(new Color(235, 243, 250));
    }

    private void setComponent() {
        // --- โค้ดส่วน UI อื่นๆ คงเดิม ---
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(235, 243, 250));
        menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        menu.setPreferredSize(new Dimension(200, 0));
        JLabel namebrand = new JLabel("GoDrive");
        namebrand.setFont(new Font("Arial", Font.BOLD, 29));
        JLabel home = new JLabel("Home");
        home.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel rent = new JLabel("Rent");
        rent.setFont(new Font("Arial", Font.BOLD, 18));
        menu.add(namebrand);
        menu.add(Box.createRigidArea(new Dimension(0, 40)));
        menu.add(home);
        menu.add(Box.createRigidArea(new Dimension(0, 15)));
        menu.add(rent);
        menu.add(Box.createVerticalGlue());
        try {
            String arrowIconPath = "images/back.png";
            ImageIcon originalArrowIcon = new ImageIcon(getClass().getClassLoader().getResource(arrowIconPath));
            Image resizedArrowImage = originalArrowIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            ImageIcon arrowIcon = new ImageIcon(resizedArrowImage);
            JButton backButton = new JButton(arrowIcon);
            backButton.setBorderPainted(false);
            backButton.setContentAreaFilled(false);
            backButton.setFocusPainted(false);
            backButton.setOpaque(false);
            backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            menu.add(backButton);
        } catch (Exception e) { System.err.println("Could not load back.png icon."); }
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.setBackground(Color.WHITE);
        ImagePN bannerImage = new ImagePN("CarCard/images1/banner.jpg");
        bannerImage.setLayout(new BorderLayout());
        bannerImage.setPreferredSize(new Dimension(0, 200));
        JPanel textpn = new JPanel();
        textpn.setOpaque(false);
        textpn.setLayout(new BoxLayout(textpn, BoxLayout.Y_AXIS));
        textpn.setBorder(new EmptyBorder(50, 20, 0, 80));
        JLabel bannerText1 = new JLabel("Select Your Vehicle");
        bannerText1.setFont(new Font("Arial", Font.BOLD, 28));
        bannerText1.setForeground(Color.white);
        JLabel bannerText2 = new JLabel("Find the perfect vehicle for your trip");
        bannerText2.setFont(new Font("Arial", Font.PLAIN, 20));
        bannerText2.setForeground(Color.white);
        textpn.add(bannerText1);
        textpn.add(Box.createRigidArea(new Dimension(0, 10)));
        textpn.add(bannerText2);
        bannerImage.add(textpn, BorderLayout.EAST);
        JPanel filter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filter.setBorder(new EmptyBorder(10, 20, 10, 20));
        filter.setBackground(Color.WHITE);
        JLabel carTypeLabel = new JLabel("CarType");
        carTypeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        String[] vehicleTypes = {"Cars", "Motorcycles"};
        typeComboBox = new JComboBox<>(vehicleTypes);
        typeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        filter.add(carTypeLabel);
        filter.add(Box.createRigidArea(new Dimension(10, 0)));
        filter.add(typeComboBox);

        dynamic = new JPanel(new CardLayout());
        
        // << CHANGED: แก้ไข action ให้บันทึกข้อมูลลงไฟล์ CSV
        Consumer<Vehicle> openReservePageAction = (selectedVehicle) -> {
            // --- ส่วนบันทึกไฟล์ ---
            String fileName = "File/selected_car.csv";
            try {
                File file = new File(fileName);
                boolean fileExists = file.exists();
                
                // ใช้ FileWriter ในโหมด append (true)
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                    // ถ้าไฟล์ยังไม่มี ให้เขียน Header ก่อน
                    if (!fileExists) {
                        writer.write("Brand,Model,Price");
                        writer.newLine();
                    }
                    
                    // สร้างข้อมูล 1 บรรทัด
                    String line = String.join(",", 
                        "\"" + selectedVehicle.getBrand() + "\"", 
                        "\"" + selectedVehicle.getModel() + "\"", 
                        "\"" + selectedVehicle.getPrice() + "\""
                    );
                    
                    writer.write(line);
                    writer.newLine(); // ขึ้นบรรทัดใหม่
                    
                    System.out.println("Vehicle data appended to " + fileName);
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Error saving vehicle data.", "File Error", JOptionPane.ERROR_MESSAGE);
            }

            // --- ส่วนไปหน้าถัดไป (เหมือนเดิม) ---
            new Reserve(selectedVehicle, this.bookingInfo);
            this.dispose();
        };
        
        carPanel = new CarCardPN(manager.getCarList(), openReservePageAction);
        motorcyclePanel = new CarCardPN(manager.getMotorcycleList(), openReservePageAction);
        
        dynamic.add(carPanel, "Cars");
        dynamic.add(motorcyclePanel, "Motorcycles");

        JPanel centerContent = new JPanel(new BorderLayout());
        centerContent.add(filter, BorderLayout.NORTH);
        centerContent.add(dynamic, BorderLayout.CENTER);
        bannerPanel.add(bannerImage, BorderLayout.NORTH);
        bannerPanel.add(centerContent, BorderLayout.CENTER);
        
        Container cp = getContentPane();
        cp.add(menu, BorderLayout.WEST);
        cp.add(bannerPanel, BorderLayout.CENTER);

        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 13));
        email.setBorder(new EmptyBorder(0, 35, 2, 0));
        cp.add(email, BorderLayout.SOUTH);
        
        typeComboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout) (dynamic.getLayout());
            cl.show(dynamic, (String) typeComboBox.getSelectedItem());
        });
    }

    private void Finally() {
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}