
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        ParkingManager manager = new ParkingManager();

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("       SMARTPARK SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Register User");
            System.out.println("2. Register Vehicle");
            System.out.println("3. Park Vehicle");
            System.out.println("4. Remove Vehicle");
            System.out.println("5. View Parking Availability");
            System.out.println("6. Search Vehicle");
            System.out.println("7. View Parking History");
            System.out.println("8. View Dashboard");
            System.out.println("9. Exit");
            System.out.println("==================================");

            choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerUser(manager);
                    break;

                case 2:
                    registerVehicle(manager);
                    break;

                case 3:
                    String parkNumber = readString("Enter vehicle number: ");
                    manager.parkVehicle(parkNumber);
                    break;

                case 4:
                    String exitNumber = readString("Enter vehicle number: ");
                    manager.removeVehicle(exitNumber);
                    break;

                case 5:
                    manager.viewAvailability();
                    break;

                case 6:
                    String searchNumber = readString("Enter vehicle number: ");
                    manager.searchVehicle(searchNumber);
                    break;

                case 7:
                    manager.viewHistory();
                    break;

                case 8:
                    manager.displayDashboard();
                    break;

                case 9:
                    System.out.println("Thank you for using SmartPark!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 9);

        sc.close();
    }

    // Register a student or staff member
    private static void registerUser(ParkingManager manager) {

        System.out.println("\n--- USER REGISTRATION ---");
        System.out.println("1. Student");
        System.out.println("2. Staff");

        int type = readInteger("Select user type: ");

        if (type != 1 && type != 2) {
            System.out.println("Invalid user type.");
            return;
        }

        String id = readString("Enter user ID: ");
        String name = readString("Enter name: ");
        String department = readString("Enter department: ");
        String phone = readString("Enter phone number: ");

        if (type == 1) {

            int year = readInteger("Enter year of study (1-6): ");

            if (year < 1 || year > 6) {
                System.out.println("Invalid year of study.");
                return;
            }

            Student student = new Student(
                id, name, department, year, phone
            );

            manager.registerUser(student);

        } else {

            Staff staff = new Staff(
                id, name, department, phone
            );

            manager.registerUser(staff);
        }
    }

    // Register a two-wheeler or four-wheeler
    private static void registerVehicle(ParkingManager manager) {

        System.out.println("\n--- VEHICLE REGISTRATION ---");
        System.out.println("1. Two-Wheeler");
        System.out.println("2. Four-Wheeler");

        int type = readInteger("Select vehicle type: ");

        if (type != 1 && type != 2) {
            System.out.println("Invalid vehicle type.");
            return;
        }

        String number = readString("Enter vehicle number: ");
        String ownerId = readString("Enter owner ID: ");
        String model = readString("Enter vehicle model: ");

        if (type == 1) {

            TwoWheeler vehicle = new TwoWheeler(
                number, ownerId, model
            );

            manager.registerVehicle(vehicle);

        } else {

            FourWheeler vehicle = new FourWheeler(
                number, ownerId, model
            );

            manager.registerVehicle(vehicle);
        }
    }

    // Read an integer safely
    private static int readInteger(String message) {

        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Read a non-empty string
    private static String readString(String message) {

        while (true) {
            System.out.print(message);

            String input = sc.nextLine().trim();

            if (!input.isEmpty() && !input.contains("|")) {
                return input;
            }

            System.out.println("Input cannot be empty or contain |.");
        }
    }
}

