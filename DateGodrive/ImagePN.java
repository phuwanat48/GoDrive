package DateGodrive;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePN extends JPanel {
    private Image backgroundImage;

    public ImagePN(String imagePath) {
        try {
            backgroundImage = ImageIO.read(getClass().getClassLoader().getResource(imagePath));
        } catch (IOException e) {
            System.err.println("์Non " + imagePath);
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}