package CarCard;

import java.awt.Component;
import javax.swing.ImageIcon;

public class Vehicle {
    private final String brand;
    private final String model;
    private final String price;
    private final String imagePath;
    private ImageIcon icon; 
    private Component cardComponent;

    public Vehicle(String brand, String model, String price, String imagePath) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.imagePath = imagePath;
    }

    // Getter methods
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getPrice() { return price; }
    public String getImagePath() { return imagePath; }

    public Component getCardComponent() { return cardComponent; }
    public void setCardComponent(Component cardComponent) { this.cardComponent = cardComponent; }

    // โหลดรูปภาพเมื่อมีการเรียกใช้จริงๆ เท่านั้น
    public ImageIcon getIcon() {
        if (icon == null && imagePath != null && !imagePath.isEmpty()) {
            icon = new ImageIcon(imagePath);
        }
        return icon;
    }

    @Override
    public String toString() {
        return brand + " " + model;
    }
}