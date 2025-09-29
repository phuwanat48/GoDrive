package GoDrive;

import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton {
    private int cornerRadius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;

        // ทำให้พื้นหลังของปุ่มโปร่งใส เพราะเราจะวาดพื้นหลังเอง
        setContentAreaFilled(false);
        // ไม่ต้องวาดเส้นกรอบสี่เหลี่ยมของปุ่ม
        setBorderPainted(false);
        // ไม่ต้องวาดเส้นประรอบข้อความเมื่อ focus
        setFocusPainted(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // ตรวจสอบสถานะของปุ่มเพื่อเปลี่ยนสี
        if (getModel().isPressed()) {
            // สีตอนกำลังกดปุ่ม (ทำให้เข้มขึ้น)
            g2.setColor(getBackground().brighter());
        } else if (getModel().isRollover()) {
            // สีตอนเอาเมาส์มาวาง (ทำให้สว่างขึ้น)
            g2.setColor(getBackground().darker());
        } else {
            // สีปกติ
            g2.setColor(getBackground());
        }

        // วาดพื้นหลังขอบมน
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();

        // เรียกเมธอดเดิมเพื่อให้วาดข้อความ ("View", "Delete") ทับลงไป
        super.paintComponent(g);
    }
}
