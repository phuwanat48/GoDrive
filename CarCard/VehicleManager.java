package CarCard;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleManager {
    private final List<Vehicle> carList = new ArrayList<>();
    private final List<Vehicle> motorcycleList = new ArrayList<>();
    private final String fileName = "File/vehicles.csv";

    public VehicleManager() {
        loadDataFromFile();
    }

    public List<Vehicle> getCarList() {
        return carList;
    }

    public List<Vehicle> getMotorcycleList() {
        return motorcycleList;
    }

    public void addVehicle(Vehicle vehicle, String type) {
        if ("Cars".equals(type)) {
            carList.add(vehicle);
        } else {
            motorcycleList.add(vehicle);
        }
        saveDataToFile();
    }

    public void removeVehicle(Vehicle vehicle, String type) {
        if ("Cars".equals(type)) {
            carList.remove(vehicle);
        } else {
            motorcycleList.remove(vehicle);
        }
        saveDataToFile();
    }

    private void loadDataFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", 5); // Split into 5 parts
                if (data.length == 5) {
                    String type = data[0];
                    Vehicle vehicle = new Vehicle(data[1], data[2], data[3], data[4]);
                    if ("Car".equals(type)) {
                        carList.add(vehicle);
                    } else if ("Motorcycle".equals(type)) {
                        motorcycleList.add(vehicle);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }

    private void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Vehicle v : carList) {
                // Format: Type,Brand,Model,Price,ImagePath
                writer.println("Car," + v.getBrand() + "," + v.getModel() + "," + v.getPrice() + "," + v.getImagePath());
            }
            for (Vehicle v : motorcycleList) {
                writer.println("Motorcycle," + v.getBrand() + "," + v.getModel() + "," + v.getPrice() + "," + v.getImagePath());
            }
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }
}