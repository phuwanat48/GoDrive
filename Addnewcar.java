import CarCard.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;


public class Addnewcar extends JFrame {

    private JTextField brandTextField, modelTextField, priceTextField;
    private JLabel imageLabel;
    private JButton addButton, deleteButton, nextPageButton; // เหลือแค่ปุ่ม nextPageButton
    private JComboBox<String> addTypeComboBox;
    private JComboBox<Vehicle> deleteComboBox;
    private ImageIcon selectedIcon;
    private String selectedImagePath;
    private VehicleManager manager;
    private CarCardPN carPanel;
    private CarCardPN motorcyclePanel;

    public Addnewcar(VehicleManager manager, CarCardPN carPanel, CarCardPN motorcyclePanel) {
        super("GoDrive");
        this.manager = manager;
        this.carPanel = carPanel;
        this.motorcyclePanel = motorcyclePanel;
        setComponent();
        createListeners();
        Finally();
        updateDeleteComboBox();
    }

    private void setComponent() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        Font customFont = new Font("Arial", Font.PLAIN, 14);
        Font titleFont = new Font("Arial", Font.BOLD, 16);

        // ---- ส่วน "เพิ่มรถ" (Add Vehicle) ----
        JPanel addPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        brandTextField = new JTextField(15);
        modelTextField = new JTextField(15);
        priceTextField = new JTextField(15);
        addButton = new JButton("Add New");
        addTypeComboBox = new JComboBox<>(new String[]{"Cars", "Motorcycles"});
        imageLabel = new JLabel("Click to add picture", SwingConstants.CENTER);

        brandTextField.setFont(customFont);
        modelTextField.setFont(customFont);
        priceTextField.setFont(customFont);
        addButton.setFont(customFont);
        addTypeComboBox.setFont(customFont);
        imageLabel.setFont(customFont);
        
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imageLabel.setPreferredSize(new Dimension(250, 180));
        imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel typeLabel = new JLabel("Type:");
        JLabel brandLabel = new JLabel("Brand:");
        JLabel modelLabel = new JLabel("Model:");
        JLabel priceLabel = new JLabel("Price/Day:");
        typeLabel.setFont(customFont);
        brandLabel.setFont(customFont);
        modelLabel.setFont(customFont);
        priceLabel.setFont(customFont);

        gbc.gridx = 0; gbc.gridy = 0; addPanel.add(typeLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; addPanel.add(addTypeComboBox, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; addPanel.add(brandLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; addPanel.add(brandTextField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; addPanel.add(modelLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; addPanel.add(modelTextField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; addPanel.add(priceLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; addPanel.add(priceTextField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addPanel.add(imageLabel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; addPanel.add(addButton, gbc);
        
        // ---- สร้าง Panel ครอบ และเพิ่มปุ่ม Next ----
        JPanel addPanelContainer = new JPanel(new BorderLayout());
        TitledBorder addBorder = BorderFactory.createTitledBorder("Add New Vehicle");
        addBorder.setTitleFont(titleFont);
        addPanelContainer.setBorder(addBorder);

        // โหลดและย่อขนาดรูปภาพสำหรับปุ่ม Next
        ImageIcon nextPageIcon = new ImageIcon(getClass().getResource("/images/next.png"));
        Image img = nextPageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        nextPageButton = new JButton(new ImageIcon(img));
        styleNavButton(nextPageButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nextPageButton);

        addPanelContainer.add(addPanel, BorderLayout.CENTER);
        addPanelContainer.add(buttonPanel, BorderLayout.SOUTH);

        // ---- ส่วน "ลบรถ" (Delete Vehicle) ----
        JPanel deletePanel = new JPanel(new BorderLayout(10, 10));
        TitledBorder deleteBorder = BorderFactory.createTitledBorder("Delete Vehicle");
        deleteBorder.setTitleFont(titleFont);
        deletePanel.setBorder(deleteBorder);

        deleteComboBox = new JComboBox<>();
        deleteButton = new JButton("Delete Selected Vehicle");
        JLabel deleteLabel = new JLabel("Select vehicle to delete:");

        deleteComboBox.setFont(customFont);
        deleteButton.setFont(customFont);
        deleteLabel.setFont(customFont);
        
        deletePanel.add(deleteLabel, BorderLayout.NORTH);
        deletePanel.add(deleteComboBox, BorderLayout.CENTER);
        deletePanel.add(deleteButton, BorderLayout.SOUTH);
        
        // ---- ประกอบร่าง ----
        mainPanel.add(addPanelContainer, BorderLayout.CENTER); // ใช้ Panel ที่มีปุ่ม Next แล้ว
        mainPanel.add(deletePanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void styleNavButton(JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void updateDeleteComboBox() {
        deleteComboBox.removeAllItems();
        
        List<Vehicle> cars = manager.getCarList();
        for (Vehicle v : cars) {
            deleteComboBox.addItem(v);
        }
        
        List<Vehicle> motorcycles = manager.getMotorcycleList();
        for (Vehicle v : motorcycles) {
            deleteComboBox.addItem(v);
        }
    }
    
    private void createListeners() {
        // ---- Listener สำหรับปุ่ม Next Page ----
        nextPageButton.addActionListener(e -> {
          JOptionPane.showMessageDialog(this, "Next to back");
        });

        // ---- Listener เดิม ----
        imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(Addnewcar.this) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    selectedImagePath = file.getAbsolutePath();
                    selectedIcon = new ImageIcon(selectedImagePath);
                    imageLabel.setText("");
                    imageLabel.setIcon(new ImageIcon(selectedIcon.getImage().getScaledInstance(250, 180, Image.SCALE_SMOOTH)));
                }
            }
        });

        addButton.addActionListener(e -> {
            String brand = brandTextField.getText().trim();
            String model = modelTextField.getText().trim();
            String priceText = priceTextField.getText().trim();

            if (brand.isEmpty() || model.isEmpty() || priceText.isEmpty() || selectedImagePath == null) {
                JOptionPane.showMessageDialog(this, "Please fill in the information completely.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String price = priceText + ".- / Day";
            Vehicle newVehicle = new Vehicle(brand, model, price, selectedImagePath);
            String type = (String) addTypeComboBox.getSelectedItem();

            manager.addVehicle(newVehicle, type);

            if (carPanel != null && "Cars".equals(type)) {
                carPanel.addVehicle(newVehicle);
            } else if (motorcyclePanel != null && "Motorcycles".equals(type)) {
                motorcyclePanel.addVehicle(newVehicle);
            }
            updateDeleteComboBox();
            
            brandTextField.setText("");
            modelTextField.setText("");
            priceTextField.setText("");
            imageLabel.setIcon(null);
            imageLabel.setText("Click to add picture");
            selectedImagePath = null;
        });
        
        deleteButton.addActionListener(e -> {
            Vehicle vehicleToDelete = (Vehicle) deleteComboBox.getSelectedItem();
            if (vehicleToDelete == null) return;
            
            int confirm = JOptionPane.showConfirmDialog(this, "Confirm to delete '" + vehicleToDelete + "'?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                String type = manager.getCarList().contains(vehicleToDelete) ? "Cars" : "Motorcycles";

                manager.removeVehicle(vehicleToDelete, type);

                if (carPanel != null && "Cars".equals(type)) {
                    carPanel.removeVehicle(vehicleToDelete);
                } else if (motorcyclePanel != null && "Motorcycles".equals(type)) {
                    motorcyclePanel.removeVehicle(vehicleToDelete);
                }
                updateDeleteComboBox();
            }
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