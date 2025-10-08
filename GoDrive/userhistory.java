package GoDrive;
import CarCard.CarCardPN;
import CarCard.VehicleManager;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class userhistory {

    public JFrame frame = new JFrame("GoDrive - Rental History");
    public JPanel dataPanel = new JPanel();
    public JTextField searchField = new JTextField();

    private JLabel imageLabel;

    public List<String[]> allRentalData;
    public final String csvFilePath;

    // Fields to store the state from Addnewcar
    private final VehicleManager manager;
    private final CarCardPN carPanel;
    private final CarCardPN motorcyclePanel;

    // Updated Constructor
    public userhistory(String csvFilePath, VehicleManager manager, CarCardPN carPanel, CarCardPN motorcyclePanel) {
        this.csvFilePath = csvFilePath;
        this.manager = manager;
        this.carPanel = carPanel;
        this.motorcyclePanel = motorcyclePanel;

        this.allRentalData = loadDataFromCsv(this.csvFilePath);
        setComponent();
        Finally();
        createHeaderAndSearch();
        createDataContainer();
        displayData(this.allRentalData);
        frame.setVisible(true);
    }

    public void setComponent(){
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Lib/Img/back.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon userIcon = new ImageIcon(scaledImage);

        // <<< 2. ลบ JLabel ด้านหน้าออก ให้ใช้ตัวแปรของคลาสแทน >>>
        imageLabel = new JLabel(userIcon); // ไม่ต้องมี JLabel ข้างหน้า

        imageLabel.setBounds(5, 550, 80, 80);
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose(); // Close current history window
                SwingUtilities.invokeLater(() -> {
                    // Recreate the Addnewcar window using the stored manager and panels
                    new Addnewcar(manager, carPanel, motorcyclePanel);
                });
            }
        });


    }

        public void Finally() {
        frame.add(imageLabel);
        frame.setSize(900, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
    }

    public List<String[]> loadDataFromCsv(String filePath) {

        List<String[]> records = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] data = line.split(",", -1);
                    if (data.length >= 5) {
                        records.add(data);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public void saveDataToCsv(String filePath, List<String[]> data) {
        String[] header = {"Status", "Car Brand", "Order Date", "Customer Name", "Price(THB)"};
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println(String.join(",", header));
            for (String[] rowData : data) {
                pw.println(String.join(",", rowData));
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving data to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteRecord(String[] recordToDelete) {
        allRentalData.remove(recordToDelete);
        saveDataToCsv(this.csvFilePath, allRentalData);
        searchField.setText("");
        performSearch();
        JOptionPane.showMessageDialog(frame, "Record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public Color getStatusColor(String status) {
        switch (status.trim().toLowerCase()) {
            case "processing": return new Color(255, 165, 0);
            case "returned": return new Color(60, 179, 113);
            case "canceled": return new Color(220, 20, 60);
            case "paid": return new Color(30, 144, 255);
            case "picked up": return Color.LIGHT_GRAY;
            default: return Color.WHITE;
        }
    }

    public void createHeaderAndSearch() {
        JLabel titleLabel = new JLabel("Search Rental History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(20, 10, 400, 30);
        frame.add(titleLabel);
        searchField.setBounds(20, 50, 750, 30);
        frame.add(searchField);
        RoundedButton searchButton = new RoundedButton("Search", 15);
        searchButton.setBounds(780, 50, 90, 30);
        searchButton.setBackground(new Color(0, 154, 255));
        frame.add(searchButton);
        searchButton.addActionListener(e -> performSearch());
        searchField.addActionListener(e -> performSearch());
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 90, 900, 30);
        headerPanel.setBackground(new Color(230, 230, 230));
        JLabel h1 = new JLabel("Status"); h1.setBounds(65, 0, 100, 30); h1.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel h2 = new JLabel("Car Brand"); h2.setBounds(185, 0, 100, 30); h2.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel h3 = new JLabel("Order Date"); h3.setBounds(325, 0, 150, 30); h3.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel h4 = new JLabel("Customer Name"); h4.setBounds(450, 0, 150, 30); h4.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel h5 = new JLabel("Price (THB)"); h5.setBounds(620, 0, 100, 30); h5.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(h1); headerPanel.add(h2); headerPanel.add(h3); headerPanel.add(h4); headerPanel.add(h5);
        frame.add(headerPanel);
    }

    public void createDataContainer() {
        dataPanel.setLayout(null);
        dataPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(dataPanel);
        scrollPane.setBounds(0, 120, 885, 490);
        frame.add(scrollPane);
    }

    public void performSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            displayData(allRentalData);
            return;
        }
        List<String[]> filteredData = new ArrayList<>();
        for (String[] row : allRentalData) {
            if (row.length >= 4) {
                String customerName = row[3].toLowerCase();
                if (customerName.contains(searchText)) {
                    filteredData.add(row);
                }
            }
        }
        if (filteredData.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Search result not found.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            displayData(filteredData);
        }
    }

    public void displayData(List<String[]> dataToDisplay) {

        dataPanel.removeAll();
        int yPosition = 10;
        for (String[] data : dataToDisplay) {
            if (data.length < 5) continue;

            RoundedLabel statusLabel = new RoundedLabel(data[0], 15);
            statusLabel.setBounds(35, yPosition, 100, 25);
            statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
            statusLabel.setForeground(Color.WHITE);
            statusLabel.setBackground(getStatusColor(data[0]));

            JLabel brandLabel = new JLabel(data[1]); brandLabel.setBounds(160, yPosition, 120, 30);
            brandLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel dateLabel = new JLabel(data[2]); dateLabel.setBounds(280, yPosition, 160, 30);
            dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel nameLabel = new JLabel(data[3]); nameLabel.setBounds(460, yPosition, 150, 30);
            JLabel priceLabel = new JLabel(data[4]); priceLabel.setBounds(600, yPosition, 100, 30);
            priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

            RoundedButton viewButton = new RoundedButton("View", 15);
            viewButton.setBounds(720, yPosition, 70, 25);
            viewButton.setBackground(new Color(240, 240, 240));
            viewButton.setForeground(Color.BLACK);

            RoundedButton deleteButton = new RoundedButton("Delete", 15);
            deleteButton.setBounds(800, yPosition, 73, 25);
            deleteButton.setBackground(Color.RED);
            deleteButton.setForeground(Color.WHITE);

            viewButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Viewing details for: " + data[3]));
            final String[] currentRowData = data;
            deleteButton.addActionListener(e -> {
                int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the record for " + currentRowData[3] + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    deleteRecord(currentRowData);
                }
            });

            dataPanel.add(statusLabel); dataPanel.add(brandLabel); dataPanel.add(dateLabel);
            dataPanel.add(nameLabel); dataPanel.add(priceLabel); dataPanel.add(viewButton);
            dataPanel.add(deleteButton);
            yPosition += 40;
        }
        dataPanel.setPreferredSize(new Dimension(880, yPosition));
        dataPanel.revalidate();
        frame.repaint();


    }
}