package GoDrive;
import java.awt.*;
import javax.swing.*;

// คลาสนี้สืบทอด (extends) คุณสมบัติทุกอย่างมาจาก JLabel
public class RoundedLabel extends JLabel {
    private int cornerRadius;

    // Constructor ของคลาส รับข้อความและค่าความโค้งของขอบ
    public RoundedLabel(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        // ตั้งค่าให้พื้นหลังโปร่งใส เพื่อให้เราวาดพื้นหลังเองได้
        setOpaque(false);
    }

    // นี่คือหัวใจหลัก: เราเขียนทับเมธอดที่ใช้วาด Component นี้
    @Override
    protected void paintComponent(Graphics g) {
        // แปลง Graphics เป็น Graphics2D เพื่อใช้ฟีเจอร์ขั้นสูง
        Graphics2D g2 = (Graphics2D) g.create();

        // เปิด Anti-aliasing เพื่อให้ขอบโค้งดูเรียบเนียน ไม่เป็นรอยหยัก
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // วาดสี่เหลี่ยมขอบมนที่เป็นพื้นหลัง โดยใช้สี Background ที่เราตั้งค่าไว้
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();

        // เรียกเมธอดเดิมของ JLabel เพื่อให้มันวาดข้อความทับลงบนพื้นหลังที่เราวาดไว้
        super.paintComponent(g);
    }
}