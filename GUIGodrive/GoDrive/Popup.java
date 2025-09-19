package GoDrive;
import java.awt.*;
import javax.swing.*;

public class Popup extends JDialog {

    public Popup(JFrame parentFrame) { 

        // เรียก con ของ JFrame 
        super(parentFrame, "Agreement", true); // ทำให้เป็น model
        setSize(600, 350); 
        setLocationRelativeTo(parentFrame); 

        // สร้างเพื่อใส่ข้อความ
        JTextArea textArea = new JTextArea(getAgreementText());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        // เอาข้อมูลมาใส่ใน scrollPane
        JScrollPane scrollPane = new JScrollPane(textArea); 

       //เพิ่ม ปุ่ม I agree ใน popup และให้กดเพื่อต้องการออกจากหน้า pop up 
        JButton agreeButton = new JButton("I Agree");
        agreeButton.addActionListener(e -> { // กดเพื่อออก
            this.dispose(); 
        });

        // สร้างเพื่อใส่ปุ่ม
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agreeButton);

        this.add(scrollPane, BorderLayout.CENTER); // ให้ตัวข้อมูลใน scroll อยู่ตรงกลาง
        this.add(buttonPanel, BorderLayout.SOUTH); // ให้ปุ่มกดอยู่ข้างล่าง
    }
    
    private String getAgreementText() {
        return  " 1. Booking and Payment:\n" +
            " - Full Payment: Full payment of the rental fee is required before you can pick up the vehicle.\n" +
            " - Security Deposit: A security deposit may be required to cover potential damages. This deposit will be refunded to you when the vehicle is returned in good condition.\n\n" +
            " 2. Renter and Driver Qualifications:\n" +
            " - Driver's License: The driver must have a valid and current driver's license.\n" +
            " - Authorized Driver: Only the person(s) whose name is listed on the rental agreement is authorized to drive the vehicle.\n\n" +
            " 3. Vehicle Pickup and Return:\n" +
            " - Scheduled Time and Location: You must pick up and return the vehicle on the specified date, time, and at the agreed-upon location.\n" +
            " - Vehicle Inspection: You are responsible for inspecting the vehicle and all its equipment before you accept it.\n" +
            " - Vehicle Condition: The vehicle must be returned in a similar condition to when it was rented. A cleaning fee may apply if the vehicle is excessively dirty.\n\n" +
            " 4. Liability:\n" +
            " - Damage Responsibility: You are responsible for any damages to the vehicle, including the tires and windows.\n" +
            " - Accident Reporting: In the event of an accident, you must notify the company immediately and cooperate with the insurance provider.\n" +
            " - Prohibited Use: You may not use the vehicle for any illegal activities or modify it in any way.\n" +
            " - Loss of Property: You are responsible for the loss of the vehicle's keys or documents.";
    }
}