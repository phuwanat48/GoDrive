package CarCard;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CarCardPN extends JPanel {

    private static final String IMAGE_BASE_PATH = "CarCard/images1/"; //เรียกใช้ไฟล์รูป

    public CarCardPN(String type) {
        // JPanel สำหรับใส่การ์ดรถยนต์
        JPanel cardListPanel = new JPanel();
        cardListPanel.setLayout(new GridLayout(0, 3, 25, 2)); // จัดเรียงเป็นตาราง 3 คอลัมน์
        cardListPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        cardListPanel.setBackground(Color.WHITE);

        // สร้างการ์ดตามประเภทที่กำหนด
        if (type.equals("cars")) {
            addCarCards(cardListPanel);
        } else if (type.equals("motorcycles")) {
            addMotorcycleCards(cardListPanel);
        }

        // ใช้ BorderLayout สำหรับ CarCardPN เพื่อเพิ่มพื้นที่ด้านบนสำหรับหัวข้อ
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        // JScrollPane ทำให้ cardListPanel เลื่อนได้
        JScrollPane scrollPane = new JScrollPane(cardListPanel);
        scrollPane.setBorder(null); // ลบเส้นขอบของ JScrollPane

        add(scrollPane, BorderLayout.CENTER);
    }

    // สร้างการ์ดรถยนต์
    private void addCarCards(JPanel panel) {
        panel.add(createCard("TOYOTA", "YARIS", "599.- / Day", "yaris.png"));
        panel.add(createCard("FORD", "RAPTOR", "799.- / Day", "raptor.png"));
        panel.add(createCard("TOYOTA", "ALPHARD", "1,199.- / Day", "alphard.png"));
        panel.add(createCard("HONDA", "CIVIC", "699.- / Day", "civic.png"));
        panel.add(createCard("ISUZU", "MAXFORCE", "799.- / Day", "isuzu.png"));
        panel.add(createCard("HYUNDAI", "STARIA", "2,199.- / Day", "hyundai.jpg"));
    }

    // สร้างการ์ดมอเตอร์ไซค์
    private void addMotorcycleCards(JPanel panel) {
        panel.add(createCard("HONDA", "WAVE125I", "299.- / Day", "wave.png"));
        panel.add(createCard("YAMAHA", "AEROX", "350.- / Day", "aerox.png"));
        panel.add(createCard("HONDA", "FORZA 350", "599.- / Day", "forza.png"));
        panel.add(createCard("HONDA", "Giorno+ CBS", "399.- / Day", "giorno.jpg"));
        panel.add(createCard("HONDA", "ZOOMER-X", "350.- / Day", "zoomerx.png"));
        panel.add(createCard("HONDA", " Scoopyi CLUB 12", "399.- / Day", "scoopyi.png"));
    }

    private JPanel createCard(String brand, String model, String price, String imageFileName) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        String fullImagePath = IMAGE_BASE_PATH + imageFileName;

        try {
            // 1. พยายามโหลดรูป
            ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource(fullImagePath));
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(180, 130, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel images = new JLabel(scaledIcon);
            images.setHorizontalAlignment(JLabel.CENTER);

            // เพิ่ม imageLabel ลงใน card เมื่อโหลดสำเร็จ
            card.add(images, BorderLayout.CENTER);

        } catch (Exception e) {
            // 2. ถ้าโหลดรูปล้มเหลว ให้สร้าง Panel สีเทาแทน
            System.err.println("Cannot load image: " + fullImagePath);
            JPanel imagePlaceholder = new JPanel(new GridBagLayout()); // Use layout to center text
            imagePlaceholder.setPreferredSize(new Dimension(170, 120));
            imagePlaceholder.setBackground(Color.GRAY);
            imagePlaceholder.add(new JLabel("No Image"));

            // เพิ่ม imagePlaceholder ลงใน card เมื่อโหลดล้มเหลว
            card.add(imagePlaceholder, BorderLayout.CENTER);
        }

        // 3. ย้ายโค้ดส่วนแสดงรายละเอียด (detailsPanel) มาไว้ข้างนอกสุด
        // เพื่อให้มันทำงานทุกครั้ง ไม่ว่าจะโหลดรูปได้หรือไม่
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.setBackground(new Color(245, 245, 245));
        details.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel brandLabel = new JLabel(brand);
        brandLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel modelLabel = new JLabel(model);
        modelLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(new Color(60, 179, 113));

        details.add(brandLabel);
        details.add(modelLabel);
        details.add(Box.createRigidArea(new Dimension(0, 5)));
        details.add(priceLabel);

        card.add(details, BorderLayout.SOUTH);

        return card;
    }
    
}