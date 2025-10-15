import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*; // เพิ่ม Import สำหรับ Pattern


public class UserHistory extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    // ตัวกรอง (Sorter) ที่ใช้ในการค้นหา
    private TableRowSorter<TableModel> sorter;
    // JFrame สำหรับใช้เป็น Parent Frame ใน JOptionPane
    private final JFrame parentFrame; 

    // ชื่อไฟล์ CSV
    private static final String CSV_FILE_NAME = "./File/personal.csv"; 
    // Directory สำหรับเก็บสลิป
    private static final String SLIPS_DIR = "./slips";

    public UserHistory() {
        this.parentFrame = this; // กำหนดค่า parentFrame เป็น JFrame ตัวนี้
        setTitle("GoDrive");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ส่วนหัว
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("UserHistory", JLabel.LEFT);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        topPanel.add(title, BorderLayout.NORTH);

        // ช่องค้นหา
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        JButton searchBtn = new JButton("🔍");
        
        // ผูก Event Listener กับปุ่มค้นหา (เผื่อผู้ใช้คลิก)
        searchBtn.addActionListener(this::filterTable); 
        
        // ผูก DocumentListener กับช่องค้นหา เพื่อกรองข้อมูลทันทีเมื่อข้อความเปลี่ยน (Live Filtering)
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(null);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(null);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(null);
            }
        });

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        topPanel.add(searchPanel, BorderLayout.NORTH); // แก้เป็น BorderLayout.NORTH เพื่อให้ Title อยู่บนสุด
        add(topPanel, BorderLayout.NORTH);

        // ตาราง
        String[] columnNames = {"Status", "Brand", "Order Date", "Buyer", "Price(Baht)", "Payment", "Details", "Delete"};
        
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 5; 
            }
        };
        
        table = new JTable(model);
        table.setRowHeight(35);
        
        loadCSV(CSV_FILE_NAME); 

        // *** การเรียงลำดับ: วันที่เก่าที่สุดไว้บนสุด (Ascending) ***
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        int orderDateColumnIndex = 2;
        sorter.setComparator(orderDateColumnIndex, new DateComparator());
        sorter.setSortKeys(List.of(new SortKey(orderDateColumnIndex, SortOrder.ASCENDING)));
        sorter.sort();
        // ******************************************************

        // เพิ่มปุ่มในตาราง
        addButtonToTable("Payment");
        addButtonToTable("Details");
        addButtonToTable("Delete");

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = new JButton("⬅ Back");
        add(backBtn, BorderLayout.SOUTH);
        backBtn.addActionListener(e -> {
            this.dispose();
            // ต้องเปลี่ยนตามโครงสร้างจริงของคุณ
             new Addnewcar(null, null, null); 
        });

        setVisible(true);
    }
    
    // --- เมธอดค้นหาและกรองตาราง (Live Filtering) ---
    private void filterTable(ActionEvent e) {
        String searchText = searchField.getText().trim();
        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                // กรองแบบ Case-Insensitive ในทุกคอลัมน์
                RowFilter<TableModel, Object> rf = RowFilter.regexFilter("(?i)" + searchText);
                sorter.setRowFilter(rf);
            } catch (PatternSyntaxException ex) {
                sorter.setRowFilter(null);
            }
        }
    }

    // --- เมธอดสำหรับบันทึกการเปลี่ยน Status ใน CSV และ JTable ---
    
    /** * อัปเดตสถานะ (Status) ของรายการในไฟล์ CSV โดยค้นหาจาก OrderDate 
     */
    private void updateCSVStatus(int modelRow, String newStatus) {
        String orderDate = (String) model.getValueAt(modelRow, 2);
        
        try {
            File inputFile = new File(CSV_FILE_NAME);
            if (!inputFile.exists()) {
                 JOptionPane.showMessageDialog(parentFrame, "ไม่พบไฟล์ CSV: " + CSV_FILE_NAME, "CSV Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }
            
            File tempFile = new File(inputFile.getParent(), "temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            // อ่าน Header Line และเขียนกลับไป
            currentLine = reader.readLine();
            writer.write(currentLine + System.getProperty("line.separator"));
            
            boolean updated = false;
            while((currentLine = reader.readLine()) != null) {
                String cleanLine = currentLine.replace("\"", "");
                String[] data = cleanLine.split(",");
                
                if (data.length >= 12) {
                    String csvOrderDate = data[10]; 
                    
                    if (csvOrderDate.equals(orderDate) && !updated) { 
                        data[0] = newStatus; 
                        writer.write(String.join(",", data) + System.getProperty("line.separator"));
                        updated = true;
                        model.setValueAt(newStatus, modelRow, 0); 
                    } else {
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                }
            }

            writer.close(); 
            reader.close(); 
            
            // 3. แทนที่ไฟล์เดิม
            if (!inputFile.delete()) {
                JOptionPane.showMessageDialog(parentFrame, "ไม่สามารถลบไฟล์เก่าได้ กรุณาตรวจสอบสิทธิ์การเข้าถึง", "CSV Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(parentFrame, "ไม่สามารถเปลี่ยนชื่อไฟล์ชั่วคราวได้", "CSV Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(parentFrame, "Payment Status successfully changed to '" + newStatus + "' and saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parentFrame, "Error saving CSV file: " + ex.getMessage(), "CSV Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // ******************************************************
    // ** เมธอดใหม่: สำหรับลบรายการในไฟล์ CSV **
    // ******************************************************
    /**
     * ลบรายการที่ระบุในไฟล์ CSV โดยค้นหาจาก OrderDate
     * @param orderDate ค่า Order Date จากตาราง (ใช้ระบุบรรทัดใน CSV)
     */
    private void deleteRowFromCSV(String orderDate) {
        try {
            File inputFile = new File(CSV_FILE_NAME);
            if (!inputFile.exists()) return;
            
            File tempFile = new File(inputFile.getParent(), "temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            // อ่าน Header Line และเขียนกลับไป
            currentLine = reader.readLine();
            writer.write(currentLine + System.getProperty("line.separator"));
            
            boolean deleted = false;
            while((currentLine = reader.readLine()) != null) {
                String cleanLine = currentLine.replace("\"", "");
                String[] data = cleanLine.split(",");
                
                if (data.length >= 12) {
                    String csvOrderDate = data[10]; 
                    
                    // หาก Order Date ตรงกัน และยังไม่ได้ลบรายการ (ป้องกันข้อมูลซ้ำ) ให้ "ข้าม" การเขียนบรรทัดนี้
                    if (csvOrderDate.equals(orderDate) && !deleted) { 
                        // ไม่เขียนบรรทัดนี้ลงใน tempFile เพื่อให้เกิดการลบ
                        deleted = true; 
                    } else {
                        // เขียนบรรทัดที่ไม่เกี่ยวข้องลงใน tempFile
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                }
            }

            writer.close(); 
            reader.close(); 
            
            // 3. แทนที่ไฟล์เดิม
            if (!inputFile.delete()) {
                JOptionPane.showMessageDialog(parentFrame, "ไม่สามารถลบไฟล์เก่าได้", "CSV Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(parentFrame, "ไม่สามารถเปลี่ยนชื่อไฟล์ชั่วคราวได้", "CSV Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parentFrame, "Error deleting from CSV file: " + ex.getMessage(), "CSV Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // ******************************************************
    // ** เมธอดใหม่: สำหรับดึงรายละเอียดจากไฟล์ CSV (รวมวันที่รับ-คืน) **
    // ******************************************************
    /**
     * ดึงข้อมูลรายละเอียดการจองทั้งหมดจาก CSV โดยใช้ Order Date (PickupDateTime) เป็น Key
     * @param orderDate ค่า Order Date (PickupDateTime) จากตาราง
     * @return String Array ที่มีข้อมูลครบถ้วน (ยาวอย่างน้อย 12) หรือ null หากไม่พบ
     */
    private String[] getDetailsFromCSV(String orderDate) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;
            br.readLine(); // ข้าม header
            while ((line = br.readLine()) != null) {
                String cleanLine = line.replace("\"", ""); 
                String[] data = cleanLine.split(",");
                
                // ตรวจสอบความยาวข้อมูล: ต้องมีอย่างน้อย 12 คอลัมน์ (0-11)
                if (data.length >= 12) { 
                    String csvOrderDate = data[10]; // Order Date (PickupDateTime) คือ index 10
                    if (csvOrderDate.equals(orderDate)) {
                        return data; // พบรายการที่ต้องการ
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Error reading CSV for details: " + e.getMessage(), "CSV Error", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }
        return null; // ไม่พบรายการ
    }
    
    // ******************************************************
    // ** เมธอดใหม่: สำหรับค้นหา path ของไฟล์สลิป **
    // ******************************************************
    /**
     * ค้นหา Path เต็มของไฟล์สลิปจาก directory 'slips'
     * ไฟล์สลิปจะมีรูปแบบ: FirstName_LastName_slip_YYYYMMDD_HHmmss.ext
     * @param firstName ชื่อ
     * @param lastName นามสกุล
     * @return Path เต็มของไฟล์สลิป หรือ null หากไม่พบ
     */
    private String findSlipPath(String firstName, String lastName) {
        File slipsDir = new File(SLIPS_DIR);
        if (!slipsDir.exists() || !slipsDir.isDirectory()) {
            System.out.println("Slip directory not found: " + SLIPS_DIR);
            return null;
        }

        // ชื่อที่ใช้ในการค้นหา (Sanitized: ชื่อ_นามสกุล)
        String searchPrefix = (firstName + "_" + lastName).replaceAll("[^a-zA-Z0-9_]", "");
        
        // รูปแบบ RegEx สำหรับค้นหาไฟล์: [searchPrefix]_slip_YYYYMMDD_HHmmss.ext
        // (?i) ทำให้ไม่สนใจตัวพิมพ์เล็ก/ใหญ่
        // Pattern.quote() เพื่อป้องกันอักขระพิเศษในชื่อ
        Pattern pattern = Pattern.compile("(?i)^" + Pattern.quote(searchPrefix) + "_slip_\\d{8}_\\d{6}\\.(jpg|jpeg|png)$");

        for (File file : slipsDir.listFiles()) {
            if (file.isFile()) {
                if (pattern.matcher(file.getName()).matches()) {
                    return file.getAbsolutePath();
                }
            }
        }
        return null; 
    }
    // ******************************************************
    

    // --- ส่วนการจัดการวันที่ ---
    
    /**
     * Comparator สำหรับเปรียบเทียบ String ที่เก็บวันที่ในรูปแบบ "dd/MM/yyyy HH:mm"
     */
    class DateComparator implements Comparator<String> {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        @Override
        public int compare(String dateStr1, String dateStr2) {
            try {
                Date date1 = dateFormat.parse(dateStr1);
                Date date2 = dateFormat.parse(dateStr2);
                return date1.compareTo(date2); 
            } catch (Exception e) {
                return 0;
            }
        }
    }

    // --- ส่วนการโหลด CSV ---

    private void loadCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // ข้าม header
            while ((line = br.readLine()) != null) {
                String cleanLine = line.replace("\"", ""); 
                String[] data = cleanLine.split(",");
                
                if (data.length >= 12) { 
                    
                    String status = data[0]; 
                    String buyer = data[1] + " " + data[2]; 
                    String brand = data[6] + " " + data[7]; 
                    String price = data[9] + " Baht";
                    String orderDate = data[10];
                    
                    
                    model.addRow(new Object[]{
                        status,
                        brand,
                        orderDate, 
                        buyer,
                        price,
                        "Payment", 
                        "Details", 
                        "Delete" 
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ไม่พบไฟล์ CSV หรือเกิดข้อผิดพลาดในการอ่าน/แปลงข้อมูล: " + e.getMessage(), "CSV Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- ส่วนการจัดการปุ่ม ---
    
    private void addButtonToTable(String columnName) {
        TableColumn column = table.getColumn(columnName);
        column.setCellRenderer(new ButtonRenderer());
        column.setCellEditor(new ButtonEditor(new JCheckBox(), table, model, this.parentFrame)); 
    }

    // ปุ่มแสดงใน JTable
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() { setOpaque(true); }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                      boolean isSelected, boolean hasFocus,
                                                      int row, int column) {
            setText((value == null) ? "" : value.toString());
            // เพิ่มสีปุ่ม
            switch (value.toString()) {
                case "Payment": setBackground(new Color(255, 165, 0)); break; // ส้ม
                case "Details": setBackground(new Color(65, 105, 225)); break; // น้ำเงิน
                case "Delete": setBackground(Color.RED); setForeground(Color.WHITE); break;
            }
            return this;
        }
    }

    // คลิกปุ่มใน JTable 
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private int row, col;
        private JTable table;
        private DefaultTableModel model;
        private JFrame parentFrame;

        public ButtonEditor(JCheckBox checkBox, JTable table, DefaultTableModel model, JFrame parentFrame) {
            super(checkBox);
            this.table = table;
            this.model = model;
            this.parentFrame = parentFrame;
            
            button = new JButton();
            button.setOpaque(true);
            
            button.addActionListener((ActionEvent e) -> {
                // ต้องแปลง row index จาก view ไป model เสมอ เมื่อมีการเรียงลำดับ/กรอง
                int modelRow = table.convertRowIndexToModel(row);
                
                // ดึงข้อมูลแถวจาก modelRow (ข้อมูลที่แสดงในตารางหลัก)
                Object status = model.getValueAt(modelRow, 0);
                Object brand = model.getValueAt(modelRow, 1);
                Object orderDate = model.getValueAt(modelRow, 2); // PickupDateTime
                Object buyer = model.getValueAt(modelRow, 3); 
                Object price = model.getValueAt(modelRow, 4); 
                
                
                
                // Logic ตามปุ่มที่กด
                switch (label) {
                    case "Payment":
                        if (status.toString().equals("Success")) {
                            JOptionPane.showMessageDialog(parentFrame, "This order has been successfully paid.", "Payment Info", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                        int payConfirm = JOptionPane.showConfirmDialog(parentFrame, 
                                "Confirm payment and change status to 'Success' for: " + brand + " (Date: " + orderDate + ")?", 
                                "Confirm Payment", JOptionPane.YES_NO_OPTION);
                        
                        if (payConfirm == JOptionPane.YES_OPTION) {
                            updateCSVStatus(modelRow, "Success"); 
                        } 
                        break;
                    case "Details":
                        // ******************************************************
                        // ** แก้ไขส่วนนี้เพื่อดึงรายละเอียดและแสดงสลิป **
                        // ******************************************************
                        String[] allDetails = getDetailsFromCSV(orderDate.toString());
                        String pickupDate = orderDate.toString(); // OrderDate คือ Pickup Date
                        String returnDate = "N/A";
                        String firstName = "N/A";
                        String lastName = "N/A";
                        
                        if (allDetails != null && allDetails.length >= 12) {
                            // ReturnDateTime คือ index 11
                            returnDate = allDetails[11];
                            // FirstName คือ index 1, LastName คือ index 2
                            firstName = allDetails[1];
                            lastName = allDetails[2];
                        }
                        
                        String slipPath = findSlipPath(firstName, lastName);
                        Icon slipIcon = null;
                        if (slipPath != null) {
                            ImageIcon originalIcon = new ImageIcon(slipPath);
                            // ปรับขนาดรูปให้เหมาะสมกับการแสดงใน JOptionPane (เช่น 400x500)
                            Image scaledImage = originalIcon.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
                            slipIcon = new ImageIcon(scaledImage);
                        }
                        
                        JPanel detailPanel = new JPanel(new BorderLayout(10, 10));
                        JTextArea detailArea = new JTextArea(
                            "Veiw Details:\n" + 
                            "  Buyer: " + buyer + "\n" +
                            "  Brand: " + brand + "\n" +
                            "  Status: " + status + "\n" +
                            "  Total Price: " + price + "\n" +
                            "  Pickup Date: " + pickupDate + "\n" +
                            "  Return Date: " + returnDate
                        );
                        detailArea.setEditable(false);
                        detailArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                        detailPanel.add(detailArea, BorderLayout.NORTH);
                        
                        if (slipIcon != null) {
                            detailPanel.add(new JLabel("Payment Slip:"), BorderLayout.CENTER);
                            detailPanel.add(new JLabel(slipIcon), BorderLayout.SOUTH);
                        } else {
                            detailPanel.add(new JLabel("Payment Slip: NOT FOUND (Searching for: " + firstName + "_" + lastName + "_slip_...)", SwingConstants.CENTER), BorderLayout.CENTER);
                        }

                        // ใช้ JOptionPane.showMessageDialog โดยส่ง detailPanel เข้าไป
                        JOptionPane.showMessageDialog(parentFrame, detailPanel, "Order Details", JOptionPane.PLAIN_MESSAGE);
                        
                        break;
                        // ******************************************************
                    case "Delete":
                        int confirm = JOptionPane.showConfirmDialog(parentFrame, 
                                "Remove this order ?: " + brand + " Date " + orderDate , 
                                "Confrim Delete", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            // 1. ลบออกจาก JTable Model
                            model.removeRow(modelRow);
                            // 2. ลบออกจาก CSV
                            deleteRowFromCSV(orderDate.toString());
                            
                            JOptionPane.showMessageDialog(parentFrame, "Deleted");
                        }
                        break;
                }
                fireEditingStopped(); // หยุดการแก้ไขเซลล์
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int col) {
            this.row = row; 
            this.col = col; 
            label = (value == null) ? "" : value.toString();
            button.setText(label);

            switch (label) {
                case "Payment": button.setBackground(new Color(255, 165, 0)); break;
                case "Details": button.setBackground(new Color(65, 105, 225)); break;
                case "Delete": button.setBackground(Color.RED); button.setForeground(Color.WHITE); break;
            }
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label; 
        }
    }
}