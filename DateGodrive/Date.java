package DateGodrive;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
public class Date {
    public Date() {
        JFrame f = new JFrame("GoDrive");

        // ใช้ JLayeredPane แทน Container ปกติ
        JLayeredPane date = new JLayeredPane();
        date.setPreferredSize(new Dimension(900, 700));

        // --- จุดแก้ไขที่ 1: โหลดรูปภาพด้วยวิธีที่ถูกต้องและปรับขนาด ---
        /*ImageIcon banner = new ImageIcon(getClass().getResource("/car/banner.jpg"));
        Image scaledImage = banner.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);*/

         // 1. วางรูปภาพไว้บนสุด
        //imageLabel.setBounds(105, 80, 80, 80); // x, y, width, height

        // Menu Panel
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(235, 243, 250));
        menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        menu.setBounds(0, 0, 200, 700); // ตำแหน่งแบบ absolute

        JLabel l1 = new JLabel("GoDrive");
        l1.setFont(new Font("Arial", Font.BOLD, 29));
        JLabel l2 = new JLabel("Home");
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel l3 = new JLabel("Rent");
        l3.setFont(new Font("Arial", Font.BOLD, 18));

        menu.add(l1);
        menu.add(Box.createRigidArea(new Dimension(0, 40)));
        menu.add(l2);
        menu.add(Box.createRigidArea(new Dimension(0, 15)));
        menu.add(l3);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(200, 0, 700, 700); 

        // ld Panel 
        JPanel ld = new JPanel();
        ld.setBackground(new Color(189, 189, 189));
        ld.setBounds(300, 120, 490, 350);
        ld.setLayout(null); // ต้องเพิ่มบรรทัดนี้เพื่อใช้ setBounds กับ component ด้านใน

        JLabel location = new JLabel("-- Pic-up Location ------");
        location.setFont(new Font(null, Font.BOLD, 18));
        location.setBounds(20, 20, 300, 30); 

        JTextField loca = new JTextField("Location");
        loca.setBounds(20, 50, 360, 30);

        JLabel PD = new JLabel("-- Pic-up Date ------");
        PD.setFont(new Font(null, Font.BOLD, 18));
        PD.setBounds(20,90, 300, 30); 
        JComboBox<String> day = new JComboBox<>();
        day.addItem("day");
        day.addItem("1");day.addItem("2");day.addItem("3");day.addItem("4");day.addItem("5");
        day.addItem("6");day.addItem("7");day.addItem("8");day.addItem("9");day.addItem("10");
        day.addItem("11");day.addItem("12");day.addItem("13");day.addItem("14");day.addItem("15");
        day.addItem("16");day.addItem("17");day.addItem("18");day.addItem("19");day.addItem("20");
        day.addItem("21");day.addItem("22");day.addItem("23");day.addItem("24");day.addItem("25");
        day.addItem("26");day.addItem("27");day.addItem("28");day.addItem("29");day.addItem("30");
        day.addItem("31");
        day.setBounds(20, 120, 60, 30);
        JComboBox<String> month = new JComboBox<>();
        month.addItem("month");
        month.addItem("1");month.addItem("2");month.addItem("3");month.addItem("4");month.addItem("5");
        month.addItem("6");month.addItem("7");month.addItem("8");month.addItem("9");month.addItem("10");
        month.addItem("11");month.addItem("12");
        month.setBounds(100, 120, 80, 30);

        JLabel Ptime = new JLabel("-- Pic-up Time ------");
        Ptime.setFont(new Font(null, Font.BOLD, 18));
        Ptime.setBounds(300,90, 300, 30); 
        JTextField hour = new JTextField("hour");
        hour.setBounds(320, 120, 60, 30);
        JTextField minute = new JTextField("minute");
        minute.setBounds(400, 120, 60, 30);
        JLabel poin = new JLabel(":");
        poin.setFont(new Font(null, Font.BOLD, 18));
        poin.setBounds(387, 110, 10, 50); 

//-------------------------------------------------------------------------------------------------------

        JLabel RD = new JLabel("-- Return Date ------");
        RD.setFont(new Font(null, Font.BOLD, 18));
        RD.setBounds(20,160, 300, 30); 
        JComboBox<String> day2 = new JComboBox<>();
        day2.addItem("day");
        day2.addItem("1");day2.addItem("2");day2.addItem("3");day2.addItem("4");day2.addItem("5");
        day2.addItem("6");day2.addItem("7");day2.addItem("8");day2.addItem("9");day2.addItem("10");
        day2.addItem("11");day2.addItem("12");day2.addItem("13");day2.addItem("14");day2.addItem("15");
        day2.addItem("16");day2.addItem("17");day2.addItem("18");day2.addItem("19");day2.addItem("20");
        day2.addItem("21");day2.addItem("22");day2.addItem("23");day2.addItem("24");day2.addItem("25");
        day2.addItem("26");day2.addItem("27");day2.addItem("28");day2.addItem("29");day2.addItem("30");
        day2.addItem("31");
        day2.setBounds(20, 190, 60, 30);
        JComboBox<String> month2 = new JComboBox<>();
        month2.addItem("month");
        month2.addItem("1");month2.addItem("2");month2.addItem("3");month2.addItem("4");month2.addItem("5");
        month2.addItem("6");month2.addItem("7");month2.addItem("8");month2.addItem("9");month2.addItem("10");
        month2.addItem("11");month2.addItem("12");
        month2.setBounds(100, 190, 80, 30);

        JLabel Rtime = new JLabel("-- Return Time ------");
        Rtime.setFont(new Font(null, Font.BOLD, 18));
        Rtime.setBounds(300,160, 300, 30); 
        JTextField hour2 = new JTextField("hour");
        hour2.setBounds(320, 190, 60, 30);
        JTextField minute2 = new JTextField("minute");
        minute2.setBounds(400, 190, 60, 30);
        JLabel poin2 = new JLabel(":");
        poin2.setFont(new Font(null, Font.BOLD, 18));
        poin2.setBounds(387, 180, 10, 50); 

//-------------------------------------------------------------------------------------------------------    

        JButton ok = new JButton("OK");
        ok.setBounds(387, 250, 80, 40); 

//-------------------------------------------------------------------------------------------------------

        JPanel under = new JPanel();
        under.setBackground(new Color(189, 189, 189));
        under.setBounds(200, 550, 700, 400);
        under.setLayout(null); // ต้องเพิ่มบรรทัดนี้เพื่อใช้ setBounds กับ component ด้านใน

          JLabel u1 = new JLabel("Rent a car with GoDrive");
        u1.setFont(new Font("Arial", Font.BOLD, 20));
        u1.setBounds(10, 10, 300, 18);

        JLabel u2 = new JLabel("What are the advantages of renting a car with us?");
        u2.setFont(new Font("Arial", Font.BOLD, 13));
        u2.setBounds(10, 30, 1000, 20);

        under.add(u1); under.add(u2);

        //-------------------------------------------------------------------------------------------------------

        ld.add(loca); ld.add(day); ld.add(poin); ld.add(poin);ld.add(minute2);
        ld.add(location); ld.add(month); ld.add(RD);ld.add(month2);ld.add(ok);
        ld.add(PD); ld.add(minute);ld.add(day2);ld.add(Rtime);
        ld.add(Ptime); ld.add(hour);ld.add(poin2);ld.add(hour2);

        // เพิ่มทุก panel ลงใน layeredPane พร้อมกำหนด layer
        date.add(menu, JLayeredPane.PALETTE_LAYER);
        date.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        //date.add(imageLabel);
        date.add(ld, JLayeredPane.MODAL_LAYER); 
        date.add(under,JLayeredPane.MODAL_LAYER);

        f.add(date);
       f.setContentPane(date);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        
        Popup popup = new Popup(f);
        popup.setVisible(true);

    }
}