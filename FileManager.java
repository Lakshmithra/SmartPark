
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FileManager {

    private static final String USER_FILE = "users.txt";
    private static final String VEHICLE_FILE = "vehicles.txt";
    private static final String RECORD_FILE = "parking_records.txt";

    // Save users
    public static void saveUsers(ArrayList<User> users) {

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(USER_FILE))) {

            for (User user : users) {

                if (user instanceof Student) {

                    Student s = (Student) user;

                    writer.println("Student|" + s.getUserId() + "|"
                            + s.getName() + "|" + s.getDepartment()
                            + "|" + s.getYear() + "|" + s.getPhone());

                } else if (user instanceof Staff) {

                    writer.println("Staff|" + user.getUserId() + "|"
                            + user.getName() + "|"
                            + user.getDepartment() + "|"
                            + user.getPhone());
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Load users
    public static ArrayList<User> loadUsers() {

        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(USER_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                try {
                    String[] p = line.split("\\|", -1);

                    if (p[0].equals("Student") && p.length == 6) {

                        users.add(new Student(
                            p[1], p[2], p[3],
                            Integer.parseInt(p[4]), p[5]
                        ));

                    } else if (p[0].equals("Staff") && p.length == 5) {

                        users.add(new Staff(
                            p[1], p[2], p[3], p[4]
                        ));
                    }

                } catch (Exception e) {
                    System.out.println("Skipping invalid user record.");
                }
            }

        } catch (IOException e) {
            // File may not exist during the first run.
        }

        return users;
    }

    // Save vehicles
    public static void saveVehicles(ArrayList<Vehicle> vehicles) {

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(VEHICLE_FILE))) {

            for (Vehicle v : vehicles) {

                writer.println(v.getVehicleType() + "|"
                        + v.getVehicleNumber() + "|"
                        + v.getOwnerId() + "|"
                        + v.getModel());
            }

        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }

    // Load vehicles
    public static ArrayList<Vehicle> loadVehicles() {

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(VEHICLE_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                try {
                    String[] p = line.split("\\|", -1);

                    if (p.length != 4) {
                        continue;
                    }

                    if (p[0].equals("Two-Wheeler")) {

                        vehicles.add(new TwoWheeler(
                            p[1], p[2], p[3]
                        ));

                    } else if (p[0].equals("Four-Wheeler")) {

                        vehicles.add(new FourWheeler(
                            p[1], p[2], p[3]
                        ));
                    }

                } catch (Exception e) {
                    System.out.println("Skipping invalid vehicle record.");
                }
            }

        } catch (IOException e) {
            // File may not exist during the first run.
        }

        return vehicles;
    }

    // Save parking records
    public static void saveParkingRecords(
            ArrayList<ParkingRecord> records) {

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(RECORD_FILE))) {

            for (ParkingRecord record : records) {
                writer.println(record.toFileString());
            }

        } catch (IOException e) {
            System.out.println("Error saving parking records: "
                    + e.getMessage());
        }
    }

    // Load parking records
    public static ArrayList<ParkingRecord> loadParkingRecords() {

        ArrayList<ParkingRecord> records = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(RECORD_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                try {
                    String[] p = line.split("\\|", -1);

                    if (p.length != 4) {
                        continue;
                    }

                    LocalDateTime entry =
                        LocalDateTime.parse(p[2]);

                    LocalDateTime exit = p[3].isEmpty()
                        ? null : LocalDateTime.parse(p[3]);

                    records.add(new ParkingRecord(
                        p[0], p[1], entry, exit
                    ));

                } catch (Exception e) {
                    System.out.println("Skipping invalid parking record.");
                }
            }

        } catch (IOException e) {
            // File may not exist during the first run.
        }

        return records;
    }
}
