package GoDrive;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

public class Addnewcar extends JFrame {

    private JTextField brandTextField;
    private JTextField modelTextField;
    private JTextField priceTextField;
    private JLabel imageLabel;
    private JPanel imagePanel;
    private JButton addButton;
    private JComboBox<String> typeComboBox;
    private ImageIcon selectedIcon;

    public Addnewcar() {
        super("GoDrive - Add New Vehicle");
        Initial();
        setComponent();
        createListeners();
        Finally();
    }

    private void Initial() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
    }

    private void setComponent() {
        brandTextField = new JTextField(15);
        modelTextField = new JTextField(15);
        priceTextField = new JTextField(15);
        addButton = new JButton("Add New");
        imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(233, 229, 222));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imagePanel.setPreferredSize(new Dimension(350, 250));
        imageLabel = new JLabel("Add Picture", SwingConstants.CENTER);
        imageLabel.setFont(new Font("Arial", Font.BOLD, 30));
        imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        menuPanel.setPreferredSize(new Dimension(200, 0));
        JLabel nameBrandLabel = new JLabel("GoDrive");
        nameBrandLabel.setFont(new Font("Arial", Font.BOLD, 29));
        nameBrandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameBrandLabel.setBorder(new EmptyBorder(0, 10, 20, 0));
        menuPanel.add(nameBrandLabel);
        
        

        JLabel UserH = new JLabel("UserHistory");
        UserH.setFont(new Font("Arial", Font.BOLD, 20));
        UserH.setAlignmentX(Component.CENTER_ALIGNMENT);
        UserH.setBorder(new EmptyBorder(10, 0, 20, 0));
        UserH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String filePath = "./Lib/data/UserRentalHistory.csv"; 
            new userhistory(filePath);
               
                 dispose(); // ปิดหน้าต่างปัจจุบัน
                 
    }
            
        });
        menuPanel.add(UserH);

        JPanel inputFieldsPanel = new JPanel(new GridBagLayout());
        inputFieldsPanel.setBackground(new Color(235, 243, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        gbc.gridy = 0;
        inputFieldsPanel.add(new JLabel("Vehicle Type") {
            {
                setFont(labelFont);
            }
        }, gbc);
        String[] vehicleTypes = {"Cars", "Motorcycles"};
        typeComboBox = new JComboBox<>(vehicleTypes);
        gbc.gridy = 1;
        inputFieldsPanel.add(typeComboBox, gbc);
        gbc.gridy = 2;
        inputFieldsPanel.add(new JLabel("Input brand") {
            {
                setFont(labelFont);
            }
        }, gbc);
        gbc.gridy = 3;
        inputFieldsPanel.add(brandTextField, gbc);
        gbc.gridy = 4;
        inputFieldsPanel.add(new JLabel("Input model") {
            {
                setFont(labelFont);
            }
        }, gbc);
        gbc.gridy = 5;
        inputFieldsPanel.add(modelTextField, gbc);
        gbc.gridy = 6;
        inputFieldsPanel.add(new JLabel("Input price (per day)") {
            {
                setFont(labelFont);
            }
        }, gbc);
        gbc.gridy = 7;
        inputFieldsPanel.add(priceTextField, gbc);
        JPanel imageAndButtonPanel = new JPanel(new BorderLayout(0, 15));
        imageAndButtonPanel.setOpaque(false);
        imageAndButtonPanel.add(imagePanel, BorderLayout.CENTER);
        imageAndButtonPanel.add(addButton, BorderLayout.SOUTH);
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainContentPanel.setBackground(new Color(235, 243, 250));
        mainContentPanel.add(imageAndButtonPanel);
        mainContentPanel.add(inputFieldsPanel);
        Container cp = getContentPane();
        cp.add(menuPanel, BorderLayout.WEST);
        cp.add(mainContentPanel, BorderLayout.CENTER);
    }

    private void createListeners() {
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
                fileChooser.setFileFilter(filter);
                if (fileChooser.showOpenDialog(Addnewcar.this) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image scaledImage = selectedIcon.getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
                    imageLabel.setText("");
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                }
            }
        });

        // 
        addButton.addActionListener(e -> {
            String brand = brandTextField.getText();
            String model = modelTextField.getText();
            String price = priceTextField.getText();

            if (brand.trim().isEmpty() || model.trim().isEmpty() || price.trim().isEmpty() || selectedIcon == null) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields and select an image.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String vehicleType = (String) typeComboBox.getSelectedItem();
            String priceFormatted = price.trim() + ".- / Day";

            if ("Cars".equals(vehicleType)) {
                CarCard.car.addVehicleCard(brand.trim(), model.trim(), priceFormatted, selectedIcon);
            } else {
                CarCard.motorcycle.addVehicleCard(brand.trim(), model.trim(), priceFormatted, selectedIcon);
            }

            // แสดงข้อความยืนยัน แต่ไม่ต้องปิดหน้าต่าง ให้เพิ่มรถคันต่อไปได้
            JOptionPane.showMessageDialog(this, "Vehicle '" + brand + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // เคลียร์ฟอร์มให้ว่างเพื่อรอรับข้อมูลใหม่
            brandTextField.setText("");
            modelTextField.setText("");
            priceTextField.setText("");
            imageLabel.setText("Add Picture");
            imageLabel.setIcon(null);
            selectedIcon = null;
        });
    }

    public void Finally() {
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
