// FileName: Recheck.java (Modified to open Payment and Reserve windows)

import CarCard.Vehicle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; // << NEW
import javax.swing.*; // << NEW

// import org.w3c.dom.events.MouseEvent; // REMOVED: Replaced with java.awt.event.MouseEvent

public class Recheck {
    
    public Recheck (Vehicle vehicle, BookingInfo bookingInfo) {
        JFrame f = new JFrame("GoDrive");
        Container cp = f.getContentPane();
        f.getContentPane().setBackground(new Color(235, 243, 250));
        cp.setLayout(null); 

        // --- ส่วนเมนูซ้าย ---
        JLabel l1 = new JLabel("GoDrive");
        l1.setFont(new Font("Arial", Font.BOLD, 29));
        l1.setBounds(25, 25, 126, 25);
        JLabel l2 = new JLabel("Home");
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l2.setBounds(50, 65, 120, 20);
        l2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Date();
            }
        });

        // --- พื้นหลังหลักสีขาว ---
        JPanel p1 = new JPanel();
        p1.setBounds(200, 0, 1500, 700);
        p1.setBackground(Color.WHITE);
        
        JLabel mainTitle = new JLabel("Booking Confirmation");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 24));
        mainTitle.setBounds(400, 20, 300, 30);
        
        // ---- ส่วนรูปรถ ----
        ImageIcon carIcon = new ImageIcon(vehicle.getImagePath());
        Image scaledCarImage = carIcon.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH); 
        ImageIcon finalCarIcon = new ImageIcon(scaledCarImage);
        JLabel imageLabel = new JLabel(finalCarIcon);
        imageLabel.setBounds(350, 60, 350, 200);

        // ---- ส่วนข้อมูลการจอง ----
        JLabel bookingTitle = new JLabel("Booking Summary");
        bookingTitle.setFont(new Font("Arial", Font.BOLD, 18));
        bookingTitle.setBounds(320, 270, 400, 30);
        
        String pickupText = bookingInfo.getPickupDateTimeString().replace("Pic-up:", "Pic-up date :");
        String returnText = bookingInfo.getReturnDateTimeString().replace("Return:", "Return date :");
        
        JLabel pd = new JLabel(pickupText);
        pd.setFont(new Font("Arial", Font.PLAIN, 18)); 
        pd.setBounds(320, 300, 400, 30);
        
        JLabel rd = new JLabel(returnText);
        rd.setFont(new Font("Arial", Font.PLAIN, 18));
        rd.setBounds(320, 330, 400, 30);

        // ---- ส่วนข้อมูลรถ ----
        JLabel vehicleTitle = new JLabel("Vehicle Information");
        vehicleTitle.setFont(new Font("Arial", Font.BOLD, 18));
        vehicleTitle.setBounds(320, 380, 400, 30);

        JLabel bs = new JLabel("BRAND : " + vehicle.getBrand().toUpperCase());
        bs.setFont(new Font("Arial", Font.PLAIN, 18));
        bs.setBounds(320, 410, 400, 30);

        JLabel m = new JLabel("MODEL : " + vehicle.getModel().toUpperCase());
        m.setFont(new Font("Arial", Font.PLAIN, 18));
        m.setBounds(320, 440, 400, 30);

        JLabel p = new JLabel("PRICE : " + vehicle.getPrice());
        p.setFont(new Font("Arial", Font.PLAIN, 18));
        p.setBounds(320, 470, 400, 30);
        
        // ---- ส่วนสรุปราคา ----
        JPanel p8 = new JPanel();
        p8.setBounds(300, 520, 450, 1);
        p8.setBackground(Color.BLACK); 

        JLabel total = new JLabel("Total : " + bookingInfo.calculateTotalPrice(vehicle)+" THB");
        total.setFont(new Font("Arial", Font.BOLD, 20));
        total.setBounds(320, 530, 500, 20);

        JButton b1 = new JButton("Make payment");
        b1.setFont(new Font("Arial", Font.BOLD, 22));
        b1.setForeground(Color.WHITE);
        b1.setBounds(320, 570, 400, 35);
        b1.setBackground(new Color(215, 18, 18));
        
        // Action Listener สำหรับปุ่ม Make payment
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Payment(vehicle, bookingInfo); // เปิดหน้า Payment พร้อมส่งข้อมูลไป
                f.dispose(); // ปิดหน้าปัจจุบัน
            }
        });
        
        // ---- ส่วนท้าย (ปุ่ม Back) ----
        JPanel back = new JPanel();
        back.setBounds(35, 580, 60, 60);
        back.setBackground(new Color(235, 243, 250));
        // ตรวจสอบให้แน่ใจว่า path /images/back.png ถูกต้องและไฟล์มีอยู่จริง
        ImageIcon backIcon2 = new ImageIcon(getClass().getResource("/images/back.png")); 
        Image scaledback = backIcon2.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon backicon = new ImageIcon(scaledback);
        JLabel backLabel = new JLabel(backicon);
        back.add(backLabel);
        
        // << CHANGED: เพิ่ม MouseListener ให้กับ JPanel 'back' เพื่อให้กดได้
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // สมมติว่ามีคลาสชื่อ Reserve อยู่
                new Reserve(vehicle, bookingInfo); // เปิดหน้า Reserve
                f.dispose(); // ปิดหน้า Recheck ปัจจุบัน
            }
            // เพิ่ม mousePressed, mouseReleased, mouseEntered, mouseExited เพื่อให้มีพฤติกรรมคล้ายปุ่มได้
        });
        
        
        JLabel email = new JLabel("godriveofficial@gmail.com");
        email.setFont(new Font("Arial", Font.PLAIN, 12));
        email.setBounds(35, 625, 200, 60);

        // --- เพิ่มทุกอย่างลงใน Frame ---
        cp.add(l1);
        cp.add(l2);
        cp.add(email); 
        cp.add(back);
        
        cp.add(mainTitle);
        cp.add(imageLabel);
        cp.add(bookingTitle); 
        cp.add(pd);
        cp.add(rd);
        cp.add(vehicleTitle);
        cp.add(bs);
        cp.add(m);
        cp.add(p);
        cp.add(p8);
        cp.add(total);
        cp.add(b1);
        
        cp.add(p1);

        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}