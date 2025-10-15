
package CarCard;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CarCardPN extends JPanel {

    private final JPanel cardListPanel;
    private final List<Vehicle> vehicleList;
    private final Consumer<Vehicle> onCarSelectAction;

    public CarCardPN(List<Vehicle> vehicleList, Consumer<Vehicle> onCarSelectAction) {
        this.vehicleList = vehicleList;
        this.onCarSelectAction = onCarSelectAction;
        
        // <<-- กลับมาใช้ GridLayout(0, 3, ...)
        cardListPanel = new JPanel(new GridLayout(0, 3, 25, 20));
        cardListPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        cardListPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(cardListPanel);
        // ป้องกันแถบเลื่อนแนวนอน และให้แถบเลื่อนแนวตั้งแสดงเมื่อจำเป็น
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
        
        refreshPanel();
    }
    
    // ทำให้การเพิ่ม/ลบ เรียก refreshPanel เพื่อความถูกต้อง
    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
        refreshPanel();
    }
    public void removeVehicle(Vehicle vehicle) {
        this.vehicleList.remove(vehicle);
        refreshPanel();
    }

    private void refreshPanel() {
        cardListPanel.removeAll();
        for (Vehicle v : this.vehicleList) {
            if (v.getCardComponent() == null) {
                v.setCardComponent(createCardFromData(v));
            }
            cardListPanel.add(v.getCardComponent());
        }
        
        // <<-- เพิ่มโค้ดสำหรับเติม Panel ที่มองไม่เห็นเข้าไปในแถวสุดท้าย
        int vehicleCount = this.vehicleList.size();
        if (vehicleCount > 0) {
            // คำนวณหาจำนวน panel ที่ต้องเติมเพื่อให้ครบ 3
            int placeholders = (3 - (vehicleCount % 3)) % 3;
            for (int i = 0; i < placeholders; i++) {
                JPanel placeholder = new JPanel();
                placeholder.setOpaque(false); // ทำให้โปร่งใส
                cardListPanel.add(placeholder);
            }
        }
        
        revalidateAndRepaint();
    }

    private void revalidateAndRepaint() {
        cardListPanel.revalidate();
        cardListPanel.repaint();
    }

    // เมธอด createCardFromData ไม่ต้องแก้ไข ใช้ของเดิมได้เลย
    private JButton createCardFromData(Vehicle vehicle) {
        JButton card = new JButton();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (vehicle.getIcon() != null) {
            Image image = vehicle.getIcon().getImage().getScaledInstance(180, 130, Image.SCALE_SMOOTH);
            // ใช้ JLabel เพื่อให้รูปอยู่ตรงกลาง ไม่ขยายตามขนาดปุ่ม
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(imageLabel, BorderLayout.CENTER);
        }
        
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.setBorder(new EmptyBorder(10, 10, 10, 10));
        details.setBackground(new Color(245, 245, 245));

        Font brandFont = new Font("Arial", Font.BOLD, 16);
        Font modelFont = new Font("Arial", Font.PLAIN, 14); 
        Font priceFont = new Font("Arial", Font.BOLD, 14);
        Color priceColor = new Color(0, 51, 102); 

        JLabel brandLabel = new JLabel(vehicle.getBrand());
        brandLabel.setFont(brandFont);
        JLabel modelLabel = new JLabel(vehicle.getModel());
        modelLabel.setFont(modelFont);
        JLabel priceLabel = new JLabel(vehicle.getPrice());
        priceLabel.setFont(priceFont);
        priceLabel.setForeground(priceColor); 

        details.add(brandLabel);
        details.add(Box.createRigidArea(new Dimension(0, 2)));
        details.add(modelLabel);
        details.add(Box.createRigidArea(new Dimension(0, 5)));
        details.add(priceLabel);
        
        card.add(details, BorderLayout.SOUTH);
        
        card.addActionListener(e -> {
            if (onCarSelectAction != null) {
                onCarSelectAction.accept(vehicle);
            }
        });
        return card;
    }
}