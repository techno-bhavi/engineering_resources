import java.util.ArrayList;
import java.util.Scanner;

class Vehicle {
    String vehicleNumber;
    String vehicleType;
    int slotNumber;
    long entryTime;

    Vehicle(String vehicleNumber, String vehicleType, int slotNumber) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.slotNumber = slotNumber;
        this.entryTime = System.currentTimeMillis();
    }
}

public class ParkingManagementSystem {

    static Scanner scanner = new Scanner(System.in);

    static final int TOTAL_SLOTS = 10;

    static ArrayList<Vehicle> parkedVehicles = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("      PARKING MANAGEMENT SYSTEM");
        System.out.println("======================================");

        while (true) {

            System.out.println("\n========== MENU ==========");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. View Parked Vehicles");
            System.out.println("4. View Available Slots");
            System.out.println("5. Search Vehicle");
            System.out.println("6. Exit");
            System.out.println("==========================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    parkVehicle();
                    break;

                case 2:
                    removeVehicle();
                    break;

                case 3:
                    viewParkedVehicles();
                    break;

                case 4:
                    viewAvailableSlots();
                    break;

                case 5:
                    searchVehicle();
                    break;

                case 6:
                    System.out.println("\nThank you for using the system!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice!");
            }
        }
    }

    // Park a vehicle
    static void parkVehicle() {

        if (parkedVehicles.size() >= TOTAL_SLOTS) {
            System.out.println("\nParking is full!");
            return;
        }

        scanner.nextLine();

        System.out.print("\nEnter vehicle number: ");
        String vehicleNumber = scanner.nextLine();

        // Check if vehicle is already parked
        for (Vehicle vehicle : parkedVehicles) {

            if (vehicle.vehicleNumber.equalsIgnoreCase(vehicleNumber)) {

                System.out.println("This vehicle is already parked!");
                return;
            }
        }

        System.out.print("Enter vehicle type (Car/Bike/Auto): ");
        String vehicleType = scanner.nextLine();

        int slot = findAvailableSlot();

        Vehicle vehicle =
                new Vehicle(vehicleNumber, vehicleType, slot);

        parkedVehicles.add(vehicle);

        System.out.println("\nVehicle parked successfully!");
        System.out.println("Vehicle Number : " + vehicleNumber);
        System.out.println("Vehicle Type   : " + vehicleType);
        System.out.println("Parking Slot   : " + slot);
    }

    // Find available slot
    static int findAvailableSlot() {

        for (int slot = 1; slot <= TOTAL_SLOTS; slot++) {

            boolean occupied = false;

            for (Vehicle vehicle : parkedVehicles) {

                if (vehicle.slotNumber == slot) {
                    occupied = true;
                    break;
                }
            }

            if (!occupied) {
                return slot;
            }
        }

        return -1;
    }

    // Remove vehicle
    static void removeVehicle() {

        scanner.nextLine();

        System.out.print("\nEnter vehicle number: ");
        String vehicleNumber = scanner.nextLine();

        Vehicle foundVehicle = null;

        for (Vehicle vehicle : parkedVehicles) {

            if (vehicle.vehicleNumber.equalsIgnoreCase(vehicleNumber)) {
                foundVehicle = vehicle;
                break;
            }
        }

        if (foundVehicle == null) {

            System.out.println("Vehicle not found!");
            return;
        }

        long exitTime = System.currentTimeMillis();

        long durationMillis =
                exitTime - foundVehicle.entryTime;

        long durationHours =
                (durationMillis / (1000 * 60 * 60));

        // Minimum one hour charge
        if (durationHours < 1) {
            durationHours = 1;
        }

        double hourlyRate;

        if (foundVehicle.vehicleType.equalsIgnoreCase("Car")) {
            hourlyRate = 50;
        } else if (foundVehicle.vehicleType.equalsIgnoreCase("Bike")) {
            hourlyRate = 20;
        } else {
            hourlyRate = 30;
        }

        double parkingFee = durationHours * hourlyRate;

        System.out.println("\n========== PARKING BILL ==========");
        System.out.println("Vehicle Number : " + foundVehicle.vehicleNumber);
        System.out.println("Vehicle Type   : " + foundVehicle.vehicleType);
        System.out.println("Parking Slot   : " + foundVehicle.slotNumber);
        System.out.println("Duration       : " + durationHours + " hour(s)");
        System.out.println("Rate           : Rs. " + hourlyRate);
        System.out.println("----------------------------------");
        System.out.println("Total Fee      : Rs. " + parkingFee);
        System.out.println("==================================");

        parkedVehicles.remove(foundVehicle);

        System.out.println("\nVehicle removed successfully!");
    }

    // Display parked vehicles
    static void viewParkedVehicles() {

        if (parkedVehicles.isEmpty()) {

            System.out.println("\nNo vehicles are currently parked.");
            return;
        }

        System.out.println("\n========== PARKED VEHICLES ==========");

        System.out.printf(
                "%-15s %-10s %-10s%n",
                "Vehicle No.",
                "Type",
                "Slot"
        );

        System.out.println("-------------------------------------");

        for (Vehicle vehicle : parkedVehicles) {

            System.out.printf(
                    "%-15s %-10s %-10d%n",
                    vehicle.vehicleNumber,
                    vehicle.vehicleType,
                    vehicle.slotNumber
            );
        }

        System.out.println("=====================================");
    }

    // Display available slots
    static void viewAvailableSlots() {

        System.out.println("\n========== PARKING SLOTS ==========");

        for (int slot = 1; slot <= TOTAL_SLOTS; slot++) {

            boolean occupied = false;

            for (Vehicle vehicle : parkedVehicles) {

                if (vehicle.slotNumber == slot) {
                    occupied = true;
                    break;
                }
            }

            if (occupied) {
                System.out.println(
                        "Slot " + slot + " : OCCUPIED"
                );
            } else {
                System.out.println(
                        "Slot " + slot + " : AVAILABLE"
                );
            }
        }

        System.out.println("===================================");
    }

    // Search vehicle
    static void searchVehicle() {

        scanner.nextLine();

        System.out.print("\nEnter vehicle number: ");
        String vehicleNumber = scanner.nextLine();

        for (Vehicle vehicle : parkedVehicles) {

            if (vehicle.vehicleNumber.equalsIgnoreCase(vehicleNumber)) {

                System.out.println("\nVehicle Found!");
                System.out.println("-------------------------");
                System.out.println(
                        "Vehicle Number : " + vehicle.vehicleNumber
                );
                System.out.println(
                        "Vehicle Type   : " + vehicle.vehicleType
                );
                System.out.println(
                        "Parking Slot   : " + vehicle.slotNumber
                );
                System.out.println("-------------------------");

                return;
            }
        }

        System.out.println("Vehicle not found!");
    }
}
