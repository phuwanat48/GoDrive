import CarCard.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainFrame {
    private JPanel dynamic;
    private CarCardPN car;
    private CarCardPN motorcycle;
    private JComboBox<String> typeComboBox;

    public MainFrame() {
        JFrame f = new JFrame("GoDrive");
        Container cp = f.getContentPane();
        cp.setLayout(new BorderLayout(10, 10));
        cp.setBackground(new Color(235, 243, 250));

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(235, 243, 250));
        menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        menu.setPreferredSize(new Dimension(200, 0));

        JLabel l1 = new JLabel("GoDrive");
        l1.setFont(new Font("Arial", Font.BOLD, 29));

        
        // เป็น JButton เพื่อให้กดได้
        // ปุ่ม Home
        JButton home = new JButton("Home");
        home.setFont(new Font("Arial", Font.BOLD, 18));
        home.setBorderPainted(false);         // ไม่ต้องวาดขอบ
        home.setContentAreaFilled(false);   // ไม่ต้องเติมสีพื้นหลัง
        home.setFocusPainted(false);          // ไม่ต้องแสดงกรอบเมื่อ focus
        home.setCursor(new Cursor(Cursor.HAND_CURSOR)); // เปลี่ยนเมาส์เป็นรูปมือ
        // จัดให้ชิดซ้าย
        home.setHorizontalAlignment(SwingConstants.LEFT);
        home.setAlignmentX(Component.LEFT_ALIGNMENT);


        // ปุ่ม Rent
        JButton rent = new JButton("Rent");
        rent.setFont(new Font("Arial", Font.BOLD, 18));
        rent.setBorderPainted(false);
        rent.setContentAreaFilled(false);
        rent.setFocusPainted(false);
        rent.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // จัดให้ชิดซ้าย
        rent.setHorizontalAlignment(SwingConstants.LEFT);
        rent.setAlignmentX(Component.LEFT_ALIGNMENT);

        // เพิ่ม Action ให้กับปุ่ม 
        home.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, " Return to Home ?");
        });

        rent.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, " Start Booking ?");
        });

        JLabel l4 = new JLabel("godriveofficial@gmail.com");
        l4.setFont(new Font("Arial", Font.PLAIN, 13));
        l4.setBorder(new EmptyBorder(0, 35, 2, 0));
        cp.add(l4, BorderLayout.SOUTH);

        menu.add(l1);
        menu.add(Box.createRigidArea(new Dimension(0, 40)));
        
    
        // เพิ่มปุ่มที่สร้างใหม่เข้าไปในเมนูแทน JLabel เดิม
        menu.add(home);
        menu.add(Box.createRigidArea(new Dimension(0, 15)));
        menu.add(rent);
    

        menu.add(Box.createVerticalGlue());

        String arrowIconPath = "images/back.png";
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
            System.out.println("Refresh click!");
            JOptionPane.showMessageDialog(f, "Are you sure you want to go back? ");
        });

        menu.add(refresh);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        ImagePN Banner = new ImagePN("CarCard/images1/banner.jpg");
        Banner.setLayout(new BorderLayout());
        Banner.setPreferredSize(new Dimension(0, 200));

        JPanel textpn = new JPanel();
        textpn.setOpaque(false);
        textpn.setLayout(new BoxLayout(textpn, BoxLayout.Y_AXIS));
        textpn.setBorder(new EmptyBorder(50, 20, 0, 80));

        JLabel BannerText1 = new JLabel("Select Your Vehicle");
        BannerText1.setFont(new Font("Arial", Font.BOLD, 28));
        BannerText1.setForeground(Color.white);

        JLabel BannerText2 = new JLabel("Find the perfect vehicle for your trip");
        BannerText2.setFont(new Font("Arial", Font.PLAIN, 20));
        BannerText2.setForeground(Color.white);

        textpn.add(BannerText1);
        textpn.add(Box.createRigidArea(new Dimension(0, 10)));
        textpn.add(BannerText2);

        Banner.add(textpn, BorderLayout.EAST);

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

        car = new CarCardPN("cars");
        motorcycle = new CarCardPN("motorcycles");

        dynamic.add(car, "Cars");
        dynamic.add(motorcycle, "Motorcycles");

        JPanel centerContent = new JPanel(new BorderLayout());
        centerContent.setBackground(Color.WHITE);
        centerContent.add(filter, BorderLayout.NORTH);
        centerContent.add(dynamic, BorderLayout.CENTER);

        mainPanel.add(Banner, BorderLayout.NORTH);
        mainPanel.add(centerContent, BorderLayout.CENTER);

        cp.add(menu, BorderLayout.WEST);
        cp.add(mainPanel, BorderLayout.CENTER);

        typeComboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout)(dynamic.getLayout());
            String selectedItem = (String) typeComboBox.getSelectedItem();
            if ("Cars".equals(selectedItem)) {
                cl.show(dynamic, "Cars");
            } else if ("Motorcycles".equals(selectedItem)) {
                cl.show(dynamic, "Motorcycles");
            }
        });

        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}