package CarCard;
import GoDrive.CarCard;
import GoDrive.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CarCardPN extends JPanel {

    private final JPanel cardListPanel;
    private final List<Vehicle> vehicleList;

    public CarCardPN(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
        
        cardListPanel = new JPanel(new GridLayout(0, 3, 25, 20));
        cardListPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        cardListPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(cardListPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
        
        refreshPanel();
    }

    public void addVehicle(Vehicle vehicle) {
        JButton card = createCardFromData(vehicle);
        vehicle.setCardComponent(card);
        this.vehicleList.add(vehicle);
        cardListPanel.add(card);
        revalidateAndRepaint();
    }
    
    public void removeVehicle(Vehicle vehicle) {
        if (vehicle.getCardComponent() != null) {
            this.vehicleList.remove(vehicle);
            cardListPanel.remove(vehicle.getCardComponent());
            revalidateAndRepaint();
        }
    }
    
    private void refreshPanel() {
        cardListPanel.removeAll();
        for (Vehicle v : this.vehicleList) {
            if (v.getCardComponent() == null) {
                v.setCardComponent(createCardFromData(v));
            }
            cardListPanel.add(v.getCardComponent());
        }
        revalidateAndRepaint();
    }

    private void revalidateAndRepaint() {
        cardListPanel.revalidate();
        cardListPanel.repaint();
    }

    private JButton createCardFromData(Vehicle vehicle) {
        JButton card = new JButton();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (vehicle.getIcon() != null) {
            Image image = vehicle.getIcon().getImage().getScaledInstance(180, 130, Image.SCALE_SMOOTH);
            card.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
        }
        
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.setBorder(new EmptyBorder(10, 10, 10, 10));
        details.setBackground(new Color(245, 245, 245));

        
        // 1. สร้าง Font และ สี ที่ต้องการ
        Font brandFont = new Font("Arial", Font.BOLD, 16);
        Font modelFont = new Font("Arial", Font.PLAIN, 14); 
        Font priceFont = new Font("Arial", Font.BOLD, 14);
        Color priceColor = new Color(0, 51, 102); 

        // 2. สร้าง JLabel และกำหนดค่า
        JLabel brandLabel = new JLabel(vehicle.getBrand());
        brandLabel.setFont(brandFont);

        JLabel modelLabel = new JLabel(vehicle.getModel());
        modelLabel.setFont(modelFont);

        JLabel priceLabel = new JLabel(vehicle.getPrice());
        priceLabel.setFont(priceFont);
        priceLabel.setForeground(priceColor); 

        // 3. เพิ่ม JLabel ลงใน Panel
        details.add(brandLabel);
        details.add(Box.createRigidArea(new Dimension(0, 2)));
        details.add(modelLabel);
        details.add(Box.createRigidArea(new Dimension(0, 5)));
        details.add(priceLabel);
        

        card.add(details, BorderLayout.SOUTH);

        card.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You Choose : " + vehicle);

                // ส่ง "card" ซึ่งเป็น component ที่ถูกกดเข้าไปในเมธอด
                newpage(card);
            }
        });
        return card;
    }

    private void newpage(Component component) {
        // ค้นหา Window (ซึ่งอาจเป็น JFrame หรือ JDialog) ที่ component นี้อยู่
        Window window = SwingUtilities.getWindowAncestor(component);
        if (window != null) {
            window.dispose(); // สั่งปิด Window ที่เจอ
        }
        new Reserve();
    }
}