// FileName: BookingInfo.java (Modified with calculation method)

import CarCard.Vehicle; // << ADDED: ต้อง import คลาส Vehicle เพื่อนำมาใช้
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; // << ADDED: สำหรับการคำนวณระยะเวลา

public class BookingInfo {
    private String pickupLocation;
    private String pickupDateTimeString;
    private String returnDateTimeString;
    private LocalDateTime pickupDateTime;
    private LocalDateTime returnDateTime;

    public BookingInfo(String pickupLocation, String pickupDateTimeString, String returnDateTimeString, LocalDateTime pickupDateTime, LocalDateTime returnDateTime) {
        this.pickupLocation = pickupLocation;
        this.pickupDateTimeString = pickupDateTimeString;
        this.returnDateTimeString = returnDateTimeString;
        this.pickupDateTime = pickupDateTime;
        this.returnDateTime = returnDateTime;
    }

    // --- Getters (เหมือนเดิม) ---
    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getPickupDateTimeString() {
        return pickupDateTimeString;
    }

    public String getReturnDateTimeString() {
        return returnDateTimeString;
    }
    
    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    // << NEW METHOD: Added for calculating the total rental price >>
    /**
     * คำนวณราคารวมค่าเช่ารถตามข้อมูลการจองนี้
     * @param vehicle อ็อบเจกต์ของรถที่ถูกจอง เพื่อดึงราคาต่อวัน
     * @return ราคารวมค่าเช่าทั้งหมด (double)
     */
    public double calculateTotalPrice(Vehicle vehicle) {
        // 1. ตรวจสอบข้อมูลเบื้องต้น
        if (pickupDateTime == null || returnDateTime == null || returnDateTime.isBefore(pickupDateTime)) {
            return 0.0; // คืนค่า 0 ถ้าข้อมูลวัน-เวลาไม่ถูกต้อง
        }

        // --- ส่วนที่ปรับปรุง: การคำนวณจำนวนวันเช่า ---
        // 2. คำนวณจำนวนนาทีที่เช่าทั้งหมด (แม่นยำสูงสุด)
        long rentalMinutes = ChronoUnit.MINUTES.between(pickupDateTime, returnDateTime);

        // 3. แปลงนาทีเป็นวัน โดยปัดเศษขึ้นเสมอ (1 วัน = 1440 นาที)
        long rentalDays;
        if (rentalMinutes <= 0) {
            rentalDays = 0;
        } else {
            // เทคนิคการปัดเศษขึ้น: (จำนวนนาที + 1440 - 1) / 1440
            rentalDays = (rentalMinutes + 1439) / 1440;
        }
        // --- สิ้นสุดการปรับปรุง ---

        // 4. แปลงราคาจาก String เป็น double (พร้อมป้องกัน Error)
        double pricePerDay;
        try {
            String rawPriceString = vehicle.getPrice();
            // ทำความสะอาด String: ลบทุกอย่างที่ไม่ใช่ตัวเลข (0-9) และจุดทศนิยม (.)
            String cleanedPriceString = rawPriceString.replaceAll("[^\\d.]", "");
            pricePerDay = Double.parseDouble(cleanedPriceString);

        } catch (NumberFormatException | NullPointerException e) {
            System.err.println("Error parsing vehicle price after cleaning: " + vehicle.getPrice());
            return 0.0; // คืนค่า 0 ถ้ายังคงแปลงไม่ได้
        }

        // 5. คำนวณราคาสุดท้ายแล้วส่งค่ากลับ
        return rentalDays * pricePerDay;
    }
}