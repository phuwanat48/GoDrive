package CarCard;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CarCardPN extends JPanel {

    private static final String IMAGE_BASE_PATH = "CarCard/images1/";
    private final JPanel cardListPanel;

    public CarCardPN(String type) {
        cardListPanel = new JPanel();
        cardListPanel.setLayout(new GridLayout(0, 3, 25, 20));
        cardListPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        cardListPanel.setBackground(Color.WHITE);

        if (type.equalsIgnoreCase("cars")) {
            addCarCards(cardListPanel);
        } else if (type.equalsIgnoreCase("motorcycles")) {
            addMotorcycleCards(cardListPanel);
        }

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(cardListPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addVehicleCard(String brand, String model, String price, ImageIcon icon) {
        JButton newCard = createCardFromData(brand, model, price, icon);
        cardListPanel.add(newCard);
        cardListPanel.revalidate();
        cardListPanel.repaint();
    }
    
    // ... (เมธอด addCarCards, addMotorcycleCards, createCard คงไว้เหมือนเดิม) ...
    private void addCarCards(JPanel panel) {
        panel.add(createCard("TOYOTA", "YARIS", "599.- / Day", "yaris.png"));
        panel.add(createCard("FORD", "RAPTOR", "799.- / Day", "raptor.png"));
        panel.add(createCard("TOYOTA", "ALPHARD", "1,199.- / Day", "alphard.png"));
        panel.add(createCard("HONDA", "CIVIC", "699.- / Day", "civic.png"));
        panel.add(createCard("ISUZU", "MAXFORCE", "799.- / Day", "isuzu.png"));
        panel.add(createCard("HYUNDAI", "STARIA", "2,199.- / Day", "hyundai.jpg"));
    }
    private void addMotorcycleCards(JPanel panel) {
        panel.add(createCard("HONDA", "WAVE125I", "299.- / Day", "wave.png"));
        panel.add(createCard("YAMAHA", "AEROX", "350.- / Day", "aerox.png"));
        panel.add(createCard("HONDA", "FORZA 350", "599.- / Day", "forza.png"));
        panel.add(createCard("HONDA", "Giorno+ CBS", "399.- / Day", "giorno.jpg"));
        panel.add(createCard("HONDA", "ZOOMER-X", "350.- / Day", "zoomerx.png"));
        panel.add(createCard("HONDA", "Scoopyi CLUB 12", "399.- / Day", "scoopyi.png"));
    }
    private JButton createCard(String brand, String model, String price, String imageFileName) {
        String fullImagePath = IMAGE_BASE_PATH + imageFileName;
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource(fullImagePath));
            return createCardFromData(brand, model, price, originalIcon);
        } catch (Exception e) {
            System.err.println("Cannot load image: " + fullImagePath);
            return createCardFromData(brand, model, price, null);
        }
    }


    // <<< แก้ไข: เพิ่มการทำงานของเมนูคลิกขวาในเมธอดนี้ <<<
    private JButton createCardFromData(String brand, String model, String price, ImageIcon icon) {
        JButton card = new JButton();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ... (โค้ดส่วนแสดงรูปและรายละเอียดคงไว้เหมือนเดิม) ...
        if (icon != null) {
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(180, 130, Image.SCALE_SMOOTH);
            JLabel images = new JLabel(new ImageIcon(scaledImage));
            images.setHorizontalAlignment(JLabel.CENTER);
            card.add(images, BorderLayout.CENTER);
        } else {
            JPanel imagePlaceholder = new JPanel(new GridBagLayout());
            imagePlaceholder.setPreferredSize(new Dimension(170, 120));
            imagePlaceholder.setBackground(Color.GRAY);
            imagePlaceholder.add(new JLabel("No Image"));
            card.add(imagePlaceholder, BorderLayout.CENTER);
        }
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.setBackground(new Color(245, 245, 245));
        details.setBorder(new EmptyBorder(10, 10, 10, 10));
        details.add(new JLabel(brand) {{ setFont(new Font("Arial", Font.BOLD, 14)); }});
        details.add(new JLabel(model) {{ setFont(new Font("Arial", Font.PLAIN, 12)); }});
        details.add(Box.createRigidArea(new Dimension(0, 5)));
        details.add(new JLabel(price) {{ setFont(new Font("Arial", Font.BOLD, 16)); setForeground(new Color(60, 179, 113)); }});
        card.add(details, BorderLayout.SOUTH);
        
        
        card.addActionListener(e -> JOptionPane.showMessageDialog(null, "You Choose : " + brand + " " + model));

        return card;
    }
}