package GoDrive;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import CarCard.VehicleManager; // <--- เพิ่มบรรทัดนี้

public class Reserve  {

    private JFrame f = new JFrame("GoDrive");
    private JTextField tf1, tf2, tf3,tf5, tf6;
    private JComboBox<String> day, month, year,car; 
    
    public Reserve() {

        JFrame f = new JFrame("GoDrive");
        Container cp = f.getContentPane();
        f.getContentPane().setBackground(new Color(235, 243, 250));
        cp.setLayout(null);

        JLabel l1 = new JLabel("GoDrive");
        l1.setFont(new Font("Arial", Font.BOLD, 29));
        l1.setBounds(25, 25, 126, 25);

        JLabel l2 = new JLabel("Home");
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l2.setBounds(50, 65, 120, 20);

        JLabel l3 = new JLabel("Rent");
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        l3.setBounds(50, 105, 120, 20);

        JPanel p1 = new JPanel();
        p1.setBounds(200, 0, 600, 50);
        p1.setSize(1500, 700);
        p1.setBackground(Color.WHITE);

        JLabel firstname = new JLabel("first name");
        firstname.setBounds(250, 270, 100, 20);
        cp.add(firstname);
        String placeholder1 = "Enter your firstname"; // ตั้งตัวแปร placeholder
        tf1 = new JTextField(placeholder1, 20);
        tf1.setBounds(250, 290, 250, 30); 
        tf1.setForeground(Color.GRAY); 
        addPlaceholderListener(tf1, placeholder1);
        cp.add(tf1);

        JLabel lastname = new JLabel("last name");
        lastname.setBounds(250, 350, 100, 20);
        cp.add(lastname);
        String placeholder2 = "Enter your lastname"; 
        tf2 = new JTextField(placeholder2, 20);
        tf2.setBounds(250, 370, 250, 30); 
        tf2.setForeground(Color.GRAY); 
        addPlaceholderListener(tf2, placeholder2);
        cp.add(tf2);
       

        JLabel driver = new JLabel("Driver's license  number");
        driver.setBounds(250, 430, 100, 20);
        cp.add(driver);
        String placeholder3 = "Enter your driver's license number"; 
        tf3 = new JTextField(placeholder3, 20);
        tf3.setBounds(250, 450, 250, 30); 
        tf3.setForeground(Color.GRAY); 
        addPlaceholderListener(tf3, placeholder3);
        cp.add(tf3);
        tf3.addKeyListener(new java.awt.event.KeyAdapter() {
         public void keyTyped(java.awt.event.KeyEvent evt) {
         char c = evt.getKeyChar();
         String currentText = tf3.getText();

         // 1. ตรวจสอบว่าไม่ใช่ตัวเลข, Backspace หรือ Delete
             if ( !Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE && c != java.awt.event.KeyEvent.VK_DELETE) {
                 evt.consume(); 
                } 
         // 2. ถ้าเป็นตัวเลข และความยาวถึง 8 แล้ว ก็ไม่ให้พิมพ์เพิ่ม
              else if (Character.isDigit(c) && currentText.length() >= 8) {
                 evt.consume();
             }
             }
               });
       
        JLabel PD = new JLabel("Expiration date");
        PD.setBounds(250,500, 100, 30); 
        day = new JComboBox<>();
        day.addItem("DD");
        day.addItem("1");day.addItem("2");day.addItem("3");day.addItem("4");day.addItem("5");
        day.addItem("6");day.addItem("7");day.addItem("8");day.addItem("9");day.addItem("10");
        day.addItem("11");day.addItem("12");day.addItem("13");day.addItem("14");day.addItem("15");
        day.addItem("16");day.addItem("17");day.addItem("18");day.addItem("19");day.addItem("20");
        day.addItem("21");day.addItem("22");day.addItem("23");day.addItem("24");day.addItem("25");
        day.addItem("26");day.addItem("27");day.addItem("28");day.addItem("29");day.addItem("30");
        day.addItem("31");
        day.setBounds(250, 530, 50, 25);
        month = new JComboBox<>();
        month.addItem("MM");
        month.addItem("1");month.addItem("2");month.addItem("3");month.addItem("4");month.addItem("5");
        month.addItem("6");month.addItem("7");month.addItem("8");month.addItem("9");month.addItem("10");
        month.addItem("11");month.addItem("12");
        month.setBounds(300, 530, 50, 25);

        year = new JComboBox<>();
        year.addItem("YYYY");
        year.addItem("2568"); year.addItem("2569"); year.addItem("2570"); year.addItem("2571"); year.addItem("2572");
        year.addItem("2573"); year.addItem("2574"); year.addItem("2575");
        year.setBounds(350, 530, 60, 25);

        car = new JComboBox<>();
        car.addItem("CAR TYPE");
        car.addItem("car"); car.addItem("motorcycle");
        car.setBounds(410, 530, 90, 25);

        cp.add(PD);
        cp.add(day);
        cp.add(month);
        cp.add(year);
        cp.add(car);


        JLabel adress = new JLabel("Email");
        adress.setBounds(550, 350, 100, 20);
        cp.add(adress);
        String placeholder5 = "Enter your email"; 
        tf5 = new JTextField(placeholder5, 20);
        tf5.setBounds(550, 370, 250, 30); 
        tf5.setForeground(Color.GRAY); 
        addPlaceholderListener(tf5, placeholder5);
        cp.add(tf5);
       

        JLabel phone = new JLabel("Phone number");
        phone.setBounds(550, 430, 100, 20);
        cp.add(phone);
        String placeholder6 = "Enter your phone number"; 
        tf6 = new JTextField(placeholder6, 20);
        tf6.setBounds(550, 450, 250, 30); 
        tf6.setForeground(Color.GRAY); 
        addPlaceholderListener(tf6, placeholder6);
        cp.add(tf6);
        tf6.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        String currentText = tf6.getText();
        // 1. ตรวจสอบว่าไม่ใช่ตัวเลข, Backspace หรือ Delete
             if ( !Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE && c != java.awt.event.KeyEvent.VK_DELETE) {
                 evt.consume(); 
                } 
         // 2. ถ้าเป็นตัวเลข และความยาวถึง 10 แล้ว ก็ไม่ให้พิมพ์เพิ่ม
              else if (Character.isDigit(c) && currentText.length() >= 10) {
                 evt.consume();
             }
             }
            });

        
       
        
        JPanel p2 = new JPanel();
        p2.setBounds(550, 20, 400, 300);
        p2.setSize(290, 300);
        p2.setBackground(new Color(246,246,246));

        JButton b1 = new JButton("Booking");
        b1.setFont(new Font("Arial", Font.BOLD, 20));
        b1.setForeground(Color.WHITE);
        b1.setBounds(550,530,250,30);
        b1.setBackground(new Color(215,18,18));
        cp.add(b1);

           b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // ดึงข้อมูลจาก JTextFields
                String firstName = tf1.getText();
                String lastName = tf2.getText();
                String driverLicense = tf3.getText();
                String address = tf5.getText();
                String phoneNumber = tf6.getText();
                String selectedCarType = (String) car.getSelectedItem();


                //่box4
                String selectedDay = (String) day.getSelectedItem();
                String selectedMonth = (String) month.getSelectedItem();
                String selectedYear = (String) year.getSelectedItem();

                // 1. ตรวจสอบว่าเลือกวันที่ครบถ้วนหรือไม่
                if (selectedDay.equals("DD") || selectedMonth.equals("MM") || selectedYear.equals("YYYY")) {
                    JOptionPane.showMessageDialog(f, "Please select a complete Expiration Date.", "Data Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
    int dayInt = Integer.parseInt(selectedDay);
    int monthInt = Integer.parseInt(selectedMonth);

    // ถ้าเดือนคือ 2 (กุมภาพันธ์)
    if (monthInt == 2) {
        // ห้ามเลือกวันที่ 30 หรือ 31 ในเดือนกุมภาพันธ์
        if (dayInt > 29) {
            JOptionPane.showMessageDialog(f, "February cannot have day 30 or 31.", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // *Optional: เพิ่มการตรวจสอบปีอธิกสุรทิน (Leap Year) เพื่อความสมบูรณ์*
        // ในประเทศไทยใช้ปี พ.ศ. (เช่น 2568) ต้องแปลงเป็น ค.ศ. (2025) ก่อน
        if (dayInt == 29) {
            int yearInt = Integer.parseInt(selectedYear);
            // แปลง พ.ศ. เป็น ค.ศ. (ประมาณการ)
            int ceYear = yearInt - 543; 
            
            // ตรวจสอบปีอธิกสุรทิน: หาร 4 ลงตัว ยกเว้นหาร 100 ลงตัว แต่หาร 400 ลงตัว
            boolean isLeap = (ceYear % 4 == 0) && (ceYear % 100 != 0 || ceYear % 400 == 0);

            if (!isLeap) {
                 JOptionPane.showMessageDialog(f, "The selected year (" + selectedYear + ") is not a leap year. February only has 28 days.", "Date Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }
        }
    }
    
          // ตรวจสอบเดือนที่มี 30 วัน (เมษายน, มิถุนายน, กันยายน, พฤศจิกายน: 4, 6, 9, 11)
          if ((monthInt == 4 || monthInt == 6 || monthInt == 9 || monthInt == 11) && dayInt > 30) {
          JOptionPane.showMessageDialog(f, "The selected month only has 30 days.", "Date Error", JOptionPane.ERROR_MESSAGE);
          return;
          }

         } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(f, "Internal date error. Please re-select the date.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
         }
        // 2. สร้าง String วันที่ในรูปแบบ DD/MM/YYYY
        // (ถ้าเป็นตัวเลขเดียว ควรเพิ่ม 0 ข้างหน้า เช่น 1 -> 01)
        String expDay = (selectedDay.length() == 1) ? "0" + selectedDay : selectedDay;
        String expMonth = (selectedMonth.length() == 1) ? "0" + selectedMonth : selectedMonth;
        String expirationDate = expDay + "/" + expMonth + "/" + selectedYear;
               



     //ตรวจสอบการทำงาน tf1
                // 1. ตรวจสอบว่าช่องว่างหรือยังเป็น Placeholder เดิมอยู่หรือไม่
                if (firstName.equals("Enter your firstname") || firstName.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Please enter your First Name.", "Data Required", JOptionPane.WARNING_MESSAGE);
                    return; // หยุดการทำงาน
                }
                
                // 2. ตรวจสอบว่ามีเฉพาะตัวอักษรภาษาอังกฤษเท่านั้น (ห้ามเว้นวรรค, ตัวเลข, อักขระพิเศษ)
                if (!firstName.matches("^[a-zA-Z]+$")) //ตรวจตั้งแต่ตัวแรกถึงตัวสุดท้ายว่ามีแค่ a-zจริงมั้ย
                 {
                    JOptionPane.showMessageDialog(f, 
                        "First Name must contain only English letters (A-Z, a-z) and no spaces.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return; // หยุดการทำงาน
                }

     //ตรวจสอบการทำงาน tf2
              // 1. ตรวจสอบว่าช่องว่างหรือยังเป็น Placeholder เดิมอยู่หรือไม่
                if (lastName.equals("Enter your lastname") || lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Please enter your Last Name.", "Data Required", JOptionPane.WARNING_MESSAGE);
                    return; // หยุดการทำงาน
                }
                
                // 2. ตรวจสอบว่ามีเฉพาะตัวอักษรภาษาอังกฤษเท่านั้น (ห้ามเว้นวรรค, ตัวเลข, อักขระพิเศษ)
                if (!lastName.matches("^[a-zA-Z]+$")) //ตรวจตั้งแต่ตัวแรกถึงตัวสุดท้ายว่ามีแค่ a-zจริงมั้ย
                 {
                    JOptionPane.showMessageDialog(f, 
                        "Last Name must contain only English letters (A-Z, a-z) and no spaces.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return; // หยุดการทำงาน
                }

     //ตรวจสอบการทำงาน tf3
                if (driverLicense.equals("Enter your driver's license number") || driverLicense.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Please enter your Driver's License Number.", "Data Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 2 ตรวจสอบว่ามีเฉพาะตัวเลข 8 หลักเท่านั้น
                if (!driverLicense.matches("^\\d{8}$")) {
                    JOptionPane.showMessageDialog(f, 
                        "Driver's License Number must be exactly 8 digits and contain only numbers (0-9).", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return; }

      //ตรวจสอบการทำงาน tf5

                    // 1. ตรวจสอบว่าช่องว่างหรือยังเป็น Placeholder เดิมอยู่หรือไม่
                if (address.equals("Enter your email") || address.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Please enter your email.", "Data Required", JOptionPane.WARNING_MESSAGE);
                    return; // หยุดการทำงาน
                }
                
               // 2. ตรวจสอบรูปแบบอีเมลที่ถูกต้อง (ต้องมี @ และตามด้วยโดเมน, ห้ามมีเว้นวรรค)
                 if (!address.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$")) {
                 JOptionPane.showMessageDialog(f, 
        "Please enter a valid email format (e.g., user@domain.com).", 
          "Input Error", 
                JOptionPane.ERROR_MESSAGE);
                return; // หยุดการทำงาน
                       }
                 

               
           

        //ตรวจสอบการทำงาน tf6
                if (phoneNumber.equals("Enter your phone number") || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Please enter your phone Number.", "Data Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 2 ตรวจสอบว่ามีเฉพาะตัวเลข 10 หลักเท่านั้น
                if (!phoneNumber.matches("^0\\d{9}$")) {
                    JOptionPane.showMessageDialog(f, 
                        "Phone Number must be exactly 10 digits and contain only numbers (0-9).", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return; }

              //เพิ่มcartypeในcsv
              if (selectedCarType.equals("CAR TYPE")) {
              JOptionPane.showMessageDialog(f, "Please select a Car Type.", "Data Required", JOptionPane.WARNING_MESSAGE);
               return; }  


                 String fileName = "Lib/data/reservations.csv";
                 try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))){
                  //สร้างบรรทัดข้อมูลในรู)แบบcsv
                  String csvLine = String.join(",",firstName,lastName,driverLicense,expirationDate,address,phoneNumber,selectedCarType);
                  //เขียนบรรทัดข้อมูลลงในไฟล์
                  writer.write(csvLine);
                  writer.newLine(); //ขึ้นบรรทัดใหม่

                  //แสดงข้อความยืนยันการบันทึก
                  JOptionPane.showMessageDialog(f, "Booking saved booking : ", "Success",JOptionPane.INFORMATION_MESSAGE );
                 } 
                 catch (IOException ex){
                    //จัดการข้อผิดพลาดถ้าไม่สามารถถ้าเขียนไฟล์ได้
                    JOptionPane.showMessageDialog(b1, "Error saving booking : "+ ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                 }

            
            }
        
        });


    JPanel p3 = new JPanel();
    p3.setBounds(220, 30, 400, 300);
    p3.setSize(300, 200);
    p3.setBackground(new Color(246,246,246));
        
    cp.add(p3);
    cp.add(p2);
    cp.add(l1);
    cp.add(l2);
    cp.add(l3);
    cp.add(p1);
        

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Lib/Img/car_personal.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(userIcon);

        JPanel back = new JPanel();
        back.setBounds(35, 580, 500, 500);
        back.setSize(60, 60);
        back.setBackground(new Color(235,243,250));
        ImageIcon backIcon2 = new ImageIcon(getClass().getResource("/Lib/Img/back.png"));
        Image scaledback = backIcon2.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon backicon = new ImageIcon(scaledback);
        JLabel backLabel = new JLabel(backicon);
        back.add(backLabel);

        back.addMouseListener(new MouseListener() {
                                  @Override
                                  public void mouseClicked(MouseEvent e) {
                                      f.dispose();
                                      new CarCard(new VehicleManager());
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
                              }

        );


        p3.add(imageLabel);
        cp.add(back);
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setResizable(false);


        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    

    private void addPlaceholderListener(JTextField textField, String placeholder) {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); 
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
    
      


}