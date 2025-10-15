// FileName: Date.java (Modified)

import CarCard.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // << NEW: เพิ่ม import สำหรับจัดรูปแบบวันที่
import javax.swing.*;
import javax.swing.border.*;

public class Date {
    private JFrame f;
    private JLayeredPane date;
    private JPanel BN, UN, menu, mainPanel, ld;

    private JTextField  hour, minute, hour2, minute2;
    private JComboBox<String> loca,day, month, day2, month2;
    private JButton ok,Admin_login;

    // << NOTE: You will need a VehicleManager instance. For now, we assume it's created here.
    // In a real application, you might pass this in or use a Singleton pattern.
    private VehicleManager vehicleManager;

    public Date() {
        this.vehicleManager = new VehicleManager(); // Assuming VehicleManager can be instantiated.

        f = new JFrame("GoDrive");
        date = new JLayeredPane();
        date.setPreferredSize(new Dimension(900, 700));

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
        Admin_login = new JButton("Admin Login");

        Admin_login.setForeground(Color.WHITE);
        Admin_login.setBackground(Color.red);
        // --- ส่วนที่เพิ่มเข้ามาสำหรับกำหนดขนาด ---
// 1. สร้าง object Dimension เพื่อกำหนดขนาด (กว้าง, สูง)
        Dimension buttonSize = new Dimension(120, 30);

// 2. กำหนดขนาดสูงสุดที่ปุ่มสามารถขยายได้
        Admin_login.setMaximumSize(buttonSize);

// 3. กำหนดขนาดที่ปุ่มอยากจะเป็น (ควรตั้งค่าคู่กัน)
        Admin_login.setPreferredSize(buttonSize);
// ------------------------------------

// จัดตำแหน่งปุ่มให้ชิดซ้าย (สำคัญมาก)
        Admin_login.setAlignmentX(Component.LEFT_ALIGNMENT);

// ... จากนั้นค่อย add เข้าไปใน menu panel ...
        Admin_login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.dispose();
                new Login();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        menu.add(Admin_login);


        menu.add(l1);
        menu.add(Box.createRigidArea(new Dimension(0, 40)));
        menu.add(l2);
        menu.add(Box.createRigidArea(new Dimension(0, 15)));
        menu.add(l3);
        menu.add(Box.createRigidArea(new Dimension(0, 450))); // ลดระยะห่างหน่อย
        menu.add(Admin_login); // << เพิ่มปุ่มเข้ามาตรงนี้



        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(200, 0, 700, 700);

        ld = new JPanel();
        ld.setBackground(new Color(246, 246, 246));
        ld.setBounds(300, 120, 490, 350);
        ld.setLayout(null);

        JLabel location = new JLabel("-- Pick-up Location ------");
        location.setFont(new Font(null, Font.BOLD, 18));
        location.setBounds(20, 20, 300, 30);
        loca = new JComboBox<>();
        loca.addItem("Godrive Kamphaeng Saen");
        loca.setBounds(20, 50, 200, 30);

        JLabel PD = new JLabel("-- Pick-up Date ------");
        PD.setFont(new Font(null, Font.BOLD, 18));
        PD.setBounds(20, 90, 300, 30);

        day = new JComboBox<>();
        day.addItem("Day"); // Default value from image
        for (int i = 1; i <= 31; i++) day.addItem(String.valueOf(i));
        day.setBounds(20, 120, 60, 30);

        month = new JComboBox<>();
        month.addItem("Month"); // Default value from image
        for (int i = 1; i <= 12; i++) month.addItem(String.valueOf(i));
        month.setBounds(100, 120, 80, 30);

        JLabel Ptime = new JLabel("-- Pick-up Time ------");
        Ptime.setFont(new Font(null, Font.BOLD, 18));
        Ptime.setBounds(20, 160, 300, 30);
        hour = new JTextField("Hours");
        hour.setBounds(20, 190, 60, 30);
        minute = new JTextField("Minute");
        minute.setBounds(100, 190, 80, 30);
        JLabel poin = new JLabel(":");
        poin.setFont(new Font(null, Font.BOLD, 18));
        poin.setBounds(87, 177, 10, 50);

        JLabel RD = new JLabel("-- Return Date ------");
        RD.setFont(new Font(null, Font.BOLD, 18));
        RD.setBounds(250, 90, 300, 30);

        day2 = new JComboBox<>();
        day2.addItem("Day"); // Default value from image
        for (int i = 1; i <= 31; i++) day2.addItem(String.valueOf(i));
        day2.setBounds(270, 120, 60, 30);
        month2 = new JComboBox<>();
        month2.addItem("Month"); // Default value from image
        for (int i = 1; i <= 12; i++) month2.addItem(String.valueOf(i));
        month2.setBounds(350, 120, 80, 30);

        JLabel Rtime = new JLabel("-- Return Time ------");
        Rtime.setFont(new Font(null, Font.BOLD, 18));
        Rtime.setBounds(250, 160, 300, 30);
        hour2 = new JTextField("Hours");
        hour2.setBounds(270, 190, 60, 30);
        minute2 = new JTextField("Minute");
        minute2.setBounds(350, 190, 60, 30);
        JLabel poin2 = new JLabel(":");
        poin2.setFont(new Font(null, Font.BOLD, 18));
        poin2.setBounds(337, 177, 10, 50);

        ok = new JButton("Search");
        ok.setBounds(367, 260, 100, 30);
        ok.setFont(new Font(null, Font.BOLD, 15));
        ok.setForeground(Color.WHITE);
        ok.setBackground(new Color(45, 114, 178));



        ld.add(loca); ld.add(day); ld.add(poin); ld.add(minute2);
        ld.add(location); ld.add(month); ld.add(RD); ld.add(month2); ld.add(ok);
        ld.add(PD); ld.add(minute); ld.add(day2); ld.add(Rtime);
        ld.add(Ptime); ld.add(hour); ld.add(poin2); ld.add(hour2);
        menu.add(Admin_login);
        date.add(menu, JLayeredPane.PALETTE_LAYER);
        date.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        date.add(ld, JLayeredPane.MODAL_LAYER);
        date.add(BN, JLayeredPane.MODAL_LAYER);
        date.add(UN, JLayeredPane.MODAL_LAYER);

        f.add(date);
        f.setContentPane(date);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        addPlaceholderBehavior(hour, "Hours");
        addPlaceholderBehavior(minute, "Minute");
        addPlaceholderBehavior(hour2, "Hours");
        addPlaceholderBehavior(minute2, "Minute");
        // ใส่หน้าpop up ให้เด้งขึ้นมา
        Popup popup = new Popup(f);
        popup.setVisible(true);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // << CHANGED: We now create a BookingInfo object and pass it to the next screen.

                // 1. Validate the input first.
                if (!isInputValid()) {
                    return;
                }

                // 2. Get validated date-time objects from the validation method.
                LocalDateTime[] dateTimes = getValidatedDateTimes();
                if (dateTimes == null) return; // Should not happen if isInputValid passed.
                LocalDateTime pickUpDateTime = dateTimes[0];
                LocalDateTime returnDateTime = dateTimes[1];

                // 3. Get other data and format strings for display.
                String pickupLocation = (String) loca.getSelectedItem();
                String pickupDisplayString = "Pic-up: " + day.getSelectedItem() + "/" + month.getSelectedItem() + " " + hour.getText() + ":" + minute.getText();
                String returnDisplayString = "Return: " + day2.getSelectedItem() + "/" + month2.getSelectedItem() + " " + hour2.getText() + ":" + minute2.getText();

                // 4. Create the BookingInfo object to hold all data.
                BookingInfo bookingInfo = new BookingInfo(pickupLocation, pickupDisplayString, returnDisplayString, pickUpDateTime, returnDateTime);

                // << NEW: เรียกใช้เมธอดสำหรับบันทึกข้อมูลลงไฟล์
                saveBookingToFile(bookingInfo);

                // 5. Open the CarCard screen and pass the BookingInfo object to it.
                new CarCardIn(vehicleManager, bookingInfo); // Pass manager and booking info
                f.dispose(); // Close the current date selection window.
            }
        });
    }

    private void addPlaceholderBehavior(JTextField textField, String placeholderText) {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // เมื่อคลิกที่ช่องข้อความ
                // ถ้าข้อความปัจจุบันคือ placeholder ให้ลบออก
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // เปลี่ยนสีตัวอักษรเป็นสีปกติ
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // เมื่อออกจากช่องข้อความ
                // ถ้าช่องว่างเปล่า ให้ใส่ placeholder กลับเข้าไป
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY); // เปลี่ยนสีเป็นสีเทา (เหมือน placeholder)
                    textField.setText(placeholderText);
                }
            }
        });

        // ตั้งค่าเริ่มต้น (เผื่อช่องนั้นไม่มี focus ตั้งแต่แรก)
        if (textField.getText().equals(placeholderText)) {
            textField.setForeground(Color.GRAY);
        }
    }

    // บันทึกข้อมูลการจองลงไฟล์ .csv
    private void saveBookingToFile(BookingInfo info) {
        String fileName = "File/booking_log.csv";
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        try {
            File file = new File(fileName);
            boolean fileExists = file.exists();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { // true = append
                if (!fileExists) {
                    writer.write("SearchTimestamp,PickupLocation,PickupDateTime,ReturnDateTime");
                    writer.newLine();
                }

                String searchTimestamp = LocalDateTime.now().format(fileFormatter);
                String pickupLocation = info.getPickupLocation();
                String pickupDateTime = info.getPickupDateTime().format(dataFormatter);
                String returnDateTime = info.getReturnDateTime().format(dataFormatter);

                String line = String.join(",", searchTimestamp, "\"" + pickupLocation + "\"", pickupDateTime, returnDateTime);

                writer.write(line);
                writer.newLine();

                System.out.println("Booking data saved to " + fileName);
            }

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + fileName);
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Could not save booking history to file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private LocalDateTime[] getValidatedDateTimes() {
        // Helper method to avoid code duplication. Returns validated LocalDateTime objects.
        try {
            int currentYear = 2025; // Assuming current year for validation

            LocalDateTime pickUpDateTime = LocalDateTime.of(
                    currentYear,
                    Integer.parseInt((String) month.getSelectedItem()),
                    Integer.parseInt((String) day.getSelectedItem()),
                    Integer.parseInt(hour.getText().trim()),
                    Integer.parseInt(minute.getText().trim())
            );

            LocalDateTime returnDateTime = LocalDateTime.of(
                    currentYear,
                    Integer.parseInt((String) month2.getSelectedItem()),
                    Integer.parseInt((String) day2.getSelectedItem()),
                    Integer.parseInt(hour2.getText().trim()),
                    Integer.parseInt(minute2.getText().trim())
            );
            return new LocalDateTime[]{pickUpDateTime, returnDateTime};
        } catch (Exception e) {
            return null; // Return null if parsing fails
        }
    }

    private boolean isInputValid() {
        try {
            if (day.getSelectedIndex() == 0 || month.getSelectedIndex() == 0 ||
                    day2.getSelectedIndex() == 0 || month2.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(f, "Please select the day and month completely", "Information is incomplete", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            LocalDateTime[] dateTimes = getValidatedDateTimes();
            if (dateTimes == null) {
                JOptionPane.showMessageDialog(f, "Invalid date, time, or number format.", "Invalid Data", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            LocalDateTime pickUpDateTime = dateTimes[0];
            LocalDateTime returnDateTime = dateTimes[1];

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