package GoDrive;
import CarCard.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CarCard extends JFrame {

    private Container cp;
    private JPanel dynamic;
    public static CarCardPN car;
    public static CarCardPN motorcycle;
    
    private JComboBox<String> typeComboBox;

    public CarCard() {
        super("GoDrive"); // เปลี่ยน Title เล็กน้อย
        Initial();
        setComponent();
        Finally();
    }

    public void Initial() {
        cp = getContentPane();
        cp.setLayout(new BorderLayout(10, 10));
        cp.setBackground(new Color(235, 243, 250));
    }

    public void setComponent() {
        // ---- เมนูด้านซ้าย ----
        // (โค้ดส่วนเมนูเหมือนเดิม แต่จะลบ MouseListener ของ rent ออก)
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(235, 243, 250));
        menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        menu.setPreferredSize(new Dimension(200, 0));

        JLabel namebrand = new JLabel("GoDrive");
        namebrand.setFont(new Font("Arial", Font.BOLD, 29));
        JLabel home = new JLabel("Home");
        home.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel rent = new JLabel("Rent"); // กลับมาเป็น JLabel ธรรมดา
        rent.setFont(new Font("Arial", Font.BOLD, 18));
        menu.add(namebrand);
        menu.add(Box.createRigidArea(new Dimension(0, 40)));
        menu.add(home);
        menu.add(Box.createRigidArea(new Dimension(0, 15)));
        menu.add(rent);
        menu.add(Box.createVerticalGlue());
        
      
        try {
            String arrowIconPath = "../Lib/Img/back.png";
            ImageIcon originalArrowIcon = new ImageIcon(getClass().getClassLoader().getResource(arrowIconPath));
            Image resizedArrowImage = originalArrowIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            ImageIcon arrowIcon = new ImageIcon(resizedArrowImage);
            JButton refresh = new JButton(arrowIcon);
            refresh.setBorderPainted(false);
            refresh.setContentAreaFilled(false);
            refresh.setFocusPainted(false);
            refresh.setOpaque(false);
            refresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
            refresh.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Are you sure you want to go back?");
            });
            menu.add(refresh);
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

        // ---- ส่วน Filter และ Dropdown ----
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

        // ---- ส่วนแสดงผลแบบสลับ (CardLayout) ----
        dynamic = new JPanel(new CardLayout());
        car = new CarCardPN("cars");           // กำหนดค่าให้ตัวแปร static 'car'
        motorcycle = new CarCardPN("motorcycles"); // กำหนดค่าให้ตัวแปร static 'motorcycle'
        dynamic.add(car, "Cars");
        dynamic.add(motorcycle, "Motorcycles");

        // ... (โค้ดส่วนที่เหลือเหมือนเดิม) ...
        JPanel centerContent = new JPanel(new BorderLayout());
        centerContent.setBackground(Color.WHITE);
        centerContent.add(filter, BorderLayout.NORTH);
        centerContent.add(dynamic, BorderLayout.CENTER);
        bannerPanel.add(bannerImage, BorderLayout.NORTH);
        bannerPanel.add(centerContent, BorderLayout.CENTER);
        cp.add(menu, BorderLayout.WEST);
        cp.add(bannerPanel, BorderLayout.CENTER);
        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 13));
        email.setBorder(new EmptyBorder(0, 35, 2, 0));
        cp.add(email, BorderLayout.SOUTH);
        typeComboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout) (dynamic.getLayout());
            String selectedItem = (String) typeComboBox.getSelectedItem();
            cl.show(dynamic, selectedItem);
        });
    }

    private void Finally() {
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
}