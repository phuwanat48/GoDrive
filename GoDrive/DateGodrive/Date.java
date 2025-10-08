package GoDrive.DateGodrive;
import GoDrive.CarCard;
import CarCard.VehicleManager; // <--- เพิ่มบรรทัดนี้
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime; // 1. เพิ่ม import ที่ขาดไป
import java.util.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;


public class Date {
    // --- Components / Fields (ประกาศตัวแปรส่วนประกอบต่างๆ) ---
    private JFrame f;
    private JLayeredPane date;
    private JPanel BN, UN, menu, mainPanel, ld;
    
    // Components that need to be accessed by the ActionListener
    private JTextField  hour, minute, hour2, minute2;
    private JComboBox<String> loca,day, month, day2, month2;
    private JButton ok;

    public Date() {
        // 1. Initialize Frame and Layered Pane
        f = new JFrame("GoDrive");
        date = new JLayeredPane();
        date.setPreferredSize(new Dimension(900, 700));

        // 2. Setup Top Panel (BN)
        BN = new JPanel();
        BN.setBackground(new Color(189, 189, 250));
        BN.setBounds(200, 0, 700, 200);
        BN.setLayout(null);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("more/Background.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(700, 200, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);
        imageLabel.setBounds(0, 0, 700, 200);

        JLabel s = new JLabel("Search Car");
        s.setFont(new Font(null, Font.BOLD, 25));
        s.setForeground(Color.WHITE);
        s.setBounds(265, 60, 300, 30); 

        BN.add(s);
        BN.add(imageLabel);

        // 3. Setup Bottom Panel (UN)
        UN = new JPanel();
        UN.setBackground(new Color(243, 243, 243));
        UN.setBounds(200, 500, 700, 200);
        UN.setLayout(null);

        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("more/Bar2.jpg"));
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(670, 170, Image.SCALE_SMOOTH);
        ImageIcon userIcon2 = new ImageIcon(scaledImage2);
        JLabel imageLabel2 = new JLabel(userIcon2);
        imageLabel2.setBounds(5, 5, 675, 165);
        UN.add(imageLabel2);

        // 4. Setup Menu Panel
        menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(235, 243, 250));
        menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        menu.setBounds(0, 0, 200, 700);

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

        // 5. Setup Main Background Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(200, 0, 700, 700);

        // 6. Setup Input Panel (ld)
        ld = new JPanel();
        ld.setBackground(new Color(246, 246, 246));
        ld.setBounds(300, 120, 490, 350);
        ld.setLayout(null);

        // --- Location ---
        JLabel location = new JLabel("-- Pic-up Location ------");
        location.setFont(new Font(null, Font.BOLD, 18));
        location.setBounds(20, 20, 300, 30);
        loca = new JComboBox<>();
        loca.addItem("Godrive Kamphaeng Saem");
        loca.setBounds(20, 50, 200, 30);

        // --- Pick-up Date ---
        JLabel PD = new JLabel("-- Pic-up Date ------");
        PD.setFont(new Font(null, Font.BOLD, 18));
        PD.setBounds(20, 90, 300, 30);
        
        day = new JComboBox<>();
        day.addItem("day");
        for (int i = 1; i <= 31; i++) {
            day.addItem(String.valueOf(i));
        }
        day.setBounds(20, 120, 60, 30);
        
        month = new JComboBox<>();
        month.addItem("month");
        for (int i = 1; i <= 12; i++) {
            month.addItem(String.valueOf(i));
        }
        month.setBounds(100, 120, 80, 30);

        // --- Pick-up Time ---
        JLabel Ptime = new JLabel("-- Pic-up Time ------");
        Ptime.setFont(new Font(null, Font.BOLD, 18));
        Ptime.setBounds(300, 90, 300, 30);
        hour = new JTextField("hour");
        hour.setBounds(320, 120, 60, 30);
        minute = new JTextField("minute");
        minute.setBounds(400, 120, 60, 30);
        JLabel poin = new JLabel(":");
        poin.setFont(new Font(null, Font.BOLD, 18));
        poin.setBounds(387, 110, 10, 50);

        // --- Return Date ---
        JLabel RD = new JLabel("-- Return Date ------");
        RD.setFont(new Font(null, Font.BOLD, 18));
        RD.setBounds(20, 160, 300, 30);
        
        day2 = new JComboBox<>();
        day2.addItem("day");
        for (int i = 1; i <= 31; i++) {
            day2.addItem(String.valueOf(i));
        }
        day2.setBounds(20, 190, 60, 30);
        
        month2 = new JComboBox<>();
        month2.addItem("month");
        for (int i = 1; i <= 12; i++) {
            month2.addItem(String.valueOf(i));
        }
        month2.setBounds(100, 190, 80, 30);

        // --- Return Time ---
        JLabel Rtime = new JLabel("-- Return Time ------");
        Rtime.setFont(new Font(null, Font.BOLD, 18));
        Rtime.setBounds(300, 160, 300, 30);
        hour2 = new JTextField("hour");
        hour2.setBounds(320, 190, 60, 30);
        minute2 = new JTextField("minute");
        minute2.setBounds(400, 190, 60, 30);
        JLabel poin2 = new JLabel(":");
        poin2.setFont(new Font(null, Font.BOLD, 18));
        poin2.setBounds(387, 180, 10, 50);

        // --- Search Button ---
        ok = new JButton("Search");
        ok.setBounds(367, 260, 100, 30);
        ok.setFont(new Font(null, Font.BOLD, 15));
        ok.setForeground(Color.WHITE);
        ok.setBackground(new Color(45, 114, 178));

        // Add components to ld Panel
        ld.add(loca); ld.add(day); ld.add(poin); ld.add(minute2);
        ld.add(location); ld.add(month); ld.add(RD); ld.add(month2); ld.add(ok);
        ld.add(PD); ld.add(minute); ld.add(day2); ld.add(Rtime);
        ld.add(Ptime); ld.add(hour); ld.add(poin2); ld.add(hour2);

        // 7. Add all panels to JLayeredPane
        date.add(menu, JLayeredPane.PALETTE_LAYER);
        date.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        date.add(ld, JLayeredPane.MODAL_LAYER);
        date.add(BN, JLayeredPane.MODAL_LAYER);
        date.add(UN, JLayeredPane.MODAL_LAYER);

        // 8. Finalize Frame Setup
        f.add(date);
        f.setContentPane(date);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        // 2. ปิดการเรียกใช้ Popup และ MainCarCard ที่ทำให้ Error
        Popup popup = new Popup(f);
        popup.setVisible(true);

        // 3. ย้าย ActionListener เข้ามาไว้ใน Constructor
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isInputValid()) {
                    return; // ถ้าข้อมูลไม่ถูกต้อง ให้หยุดการทำงานของเมธอดนี้ทันที
                }
                String Loca = (String)loca.getSelectedItem();
                String Hour = hour.getText();
                String Minute = minute.getText();
                String Hour2 = hour2.getText();
                String Minute2 = minute2.getText();
                String Day = (String)day.getSelectedItem();
                String Month = (String)month.getSelectedItem();
                String Day2 = (String)day2.getSelectedItem();
                String Month2 = (String)month2.getSelectedItem(); 

                String locationFile = "file/Location.csv";
                try (BufferedWriter Locat = new BufferedWriter(new FileWriter(locationFile,true))){
                    String location1 = String.join(",",Loca);
                    Locat.write(location1);
                    Locat.newLine();

                    String picupFile = "file/Pic_up.csv";
                    try (BufferedWriter picup = new BufferedWriter(new FileWriter(picupFile,true))){
                        String TDpicup = String.join(",","Pic-up:"+Day+"/"+Month+" "+Hour+"."+Minute);
                        picup.write(TDpicup);
                        picup.newLine();

                        String returnFile = "file/Return.csv";
                        try (BufferedWriter ReT = new BufferedWriter(new FileWriter(returnFile,true))){
                            String TDreturn = String.join(",","Return:"+Day2+"/"+Month2+" "+Hour2+"."+Minute2);
                            ReT.write(TDreturn);
                            ReT.newLine();

                            // --- ✨ จุดที่แก้ไข ---

                            f.dispose();          // ย้ายมาไว้ตรงนี้แทน! เพื่อปิดหน้าต่างหลังทำงานเสร็จ

                             new CarCard(new VehicleManager()); // แล้วค่อยเปิดหน้าต่างใหม่ (ถ้ามี)
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }

        });
    } // <-- **จุดสำคัญ**: วงเล็บปีกกานี้คือตัวปิดของ Constructor

    // 4. ย้ายเมธอด isInputValid มาอยู่นอก Constructor แต่ยังอยู่ใน Class
    private boolean isInputValid() {
        try {
            if (day.getSelectedItem().equals("day") || month.getSelectedItem().equals("month") ||
                day2.getSelectedItem().equals("day") || month2.getSelectedItem().equals("month")) {
                JOptionPane.showMessageDialog(f, "Please select the day and month completely", "Information is incomplete", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // สมมติว่าใช้ปีปัจจุบัน (2025)
            int currentYear = 2025;

            int pYear = currentYear;
            int pMonth = Integer.parseInt((String) month.getSelectedItem());
            int pDay = Integer.parseInt((String) day.getSelectedItem());
            int pHour = Integer.parseInt(hour.getText().trim());
            int pMinute = Integer.parseInt(minute.getText().trim());

            int rYear = currentYear;
            int rMonth = Integer.parseInt((String) month2.getSelectedItem());
            int rDay = Integer.parseInt((String) day2.getSelectedItem());
            int rHour = Integer.parseInt(hour2.getText().trim());
            int rMinute = Integer.parseInt(minute2.getText().trim());

            if (pHour < 0 || pHour > 23 || pMinute < 0 || pMinute > 59 || rHour < 0 || rHour > 23 || rMinute < 0 || rMinute > 59) {
                JOptionPane.showMessageDialog(f, "Invalid time format (hours must be 0-23 and minutes must be 0-59)", "Time error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            LocalDateTime pickUpDateTime = LocalDateTime.of(pYear, pMonth, pDay, pHour, pMinute);
            LocalDateTime returnDateTime = LocalDateTime.of(rYear, rMonth, rDay, rHour, rMinute);

            if (!returnDateTime.isAfter(pickUpDateTime)) {
                JOptionPane.showMessageDialog(f, "The return date and time must be after the pick-up date and time.", "The timeline is incorrect.", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(f, "Please enter hours and minutes as numbers only.", "Invalid data format.", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (java.time.DateTimeException e) {
            JOptionPane.showMessageDialog(f, "The selected date does not exist (e.g. February 30)", "Invalid date", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
} 