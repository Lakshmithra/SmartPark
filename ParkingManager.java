
import java.util.ArrayList;

public class ParkingManager {

    private ArrayList<User> users;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<ParkingSlot> slots;
    private ArrayList<ParkingRecord> records;

    // Constructor
    public ParkingManager() {

        users = FileManager.loadUsers();
        vehicles = FileManager.loadVehicles();
        records = FileManager.loadParkingRecords();

        slots = new ArrayList<>();

        createSlots();
        restoreParkingStatus();
    }

    // Create 30 two-wheeler and 20 four-wheeler slots
    private void createSlots() {

        for (int i = 1; i <= 30; i++) {

            slots.add(new ParkingSlot(
                String.format("T%02d", i),
                "Two-Wheeler"
            ));
        }

        for (int i = 1; i <= 20; i++) {

            slots.add(new ParkingSlot(
                String.format("F%02d", i),
                "Four-Wheeler"
            ));
        }
    }

    // Find user
    private User findUser(String id) {

        for (User user : users) {

            if (user.getUserId().equalsIgnoreCase(id)) {
                return user;
            }
        }

        return null;
    }

    // Find vehicle
    private Vehicle findVehicle(String number) {

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleNumber()
                    .equalsIgnoreCase(number)) {
                return vehicle;
            }
        }

        return null;
    }

    // Find slot
    private ParkingSlot findSlot(String id) {

        for (ParkingSlot slot : slots) {

            if (slot.getSlotId().equalsIgnoreCase(id)) {
                return slot;
            }
        }

        return null;
    }

    // Find active parking record
    private ParkingRecord findActiveRecord(String number) {

        for (ParkingRecord record : records) {

            if (record.getVehicleNumber().equalsIgnoreCase(number)
                    && record.isActive()) {
                return record;
            }
        }

        return null;
    }

    // Restore occupied slots after program restart
    private void restoreParkingStatus() {

        ArrayList<String> restoredVehicles = new ArrayList<>();
        ArrayList<String> restoredSlots = new ArrayList<>();

        for (ParkingRecord record : records) {

            if (!record.isActive()) {
                continue;
            }

            Vehicle vehicle = findVehicle(record.getVehicleNumber());
            ParkingSlot slot = findSlot(record.getSlotId());

            if (vehicle == null || slot == null) {
                continue;
            }

            if (!vehicle.getVehicleType().equals(slot.getVehicleType())) {
                continue;
            }

            if (restoredVehicles.contains(
                    record.getVehicleNumber().toUpperCase())
                    || restoredSlots.contains(
                    record.getSlotId().toUpperCase())) {
                continue;
            }

            slot.occupy();

            restoredVehicles.add(
                record.getVehicleNumber().toUpperCase()
            );

            restoredSlots.add(
                record.getSlotId().toUpperCase()
            );
        }
    }

    // Register user
    public void registerUser(User user) {

        if (findUser(user.getUserId()) != null) {

            System.out.println("User ID already exists.");
            return;
        }

        users.add(user);
        FileManager.saveUsers(users);

        System.out.println("User registered successfully.");
    }

    // Register vehicle
    public void registerVehicle(Vehicle vehicle) {

        if (findUser(vehicle.getOwnerId()) == null) {

            System.out.println("Owner is not registered.");
            return;
        }

        if (findVehicle(vehicle.getVehicleNumber()) != null) {

            System.out.println("Vehicle number already exists.");
            return;
        }

        for (Vehicle v : vehicles) {

            if (v.getOwnerId().equalsIgnoreCase(
                    vehicle.getOwnerId())) {

                System.out.println(
                    "This user already has a registered vehicle."
                );
                return;
            }
        }

        vehicles.add(vehicle);
        FileManager.saveVehicles(vehicles);

        System.out.println("Vehicle registered successfully.");
    }

    // Park vehicle automatically
    public void parkVehicle(String number) {

        Vehicle vehicle = findVehicle(number);

        if (vehicle == null) {

            System.out.println("Vehicle is not registered.");
            return;
        }

        if (findActiveRecord(number) != null) {

            System.out.println("Vehicle is already parked.");
            return;
        }

        ParkingSlot availableSlot = null;

        for (ParkingSlot slot : slots) {

            if (!slot.isOccupied()
                    && slot.getVehicleType().equals(
                    vehicle.getVehicleType())) {

                availableSlot = slot;
                break;
            }
        }

        if (availableSlot == null) {

            System.out.println("No compatible parking slot available.");
            return;
        }

        availableSlot.occupy();

        ParkingRecord record = new ParkingRecord(
            vehicle.getVehicleNumber(),
            availableSlot.getSlotId()
        );

        records.add(record);

        FileManager.saveParkingRecords(records);

        System.out.println("\nVehicle parked successfully!");
        System.out.println("Vehicle Number : "
                + vehicle.getVehicleNumber());
        System.out.println("Allocated Slot : "
                + availableSlot.getSlotId());
        System.out.println("Entry Time     : "
                + record.getEntryTimeText());
    }

    // Remove vehicle
    public void removeVehicle(String number) {

        ParkingRecord record = findActiveRecord(number);

        if (record == null) {

            System.out.println("Vehicle is not currently parked.");
            return;
        }

        ParkingSlot slot = findSlot(record.getSlotId());

        if (slot != null) {
            slot.release();
        }

        record.closeRecord();

        FileManager.saveParkingRecords(records);

        System.out.println("\nVehicle removed successfully!");
        System.out.println("Vehicle Number : "
                + record.getVehicleNumber());
        System.out.println("Released Slot  : "
                + record.getSlotId());
        System.out.println("Exit Time      : "
                + record.getExitTimeText());
    }

    // Display parking availability
    public void viewAvailability() {

        System.out.println("\n========== PARKING AVAILABILITY ==========");

        displaySlotGroup("Two-Wheeler", "T");
        displaySlotGroup("Four-Wheeler", "F");

        System.out.println("==========================================");
    }

    // Display slots in rows of ten
    private void displaySlotGroup(String type, String prefix) {

        System.out.println("\n" + ConsoleColors.CYAN
                + type + " Slots" + ConsoleColors.RESET);

        int count = type.equals("Two-Wheeler") ? 30 : 20;

        int available = 0;

        for (int i = 1; i <= count; i++) {

            ParkingSlot slot = findSlot(
                String.format("%s%02d", prefix, i)
            );

            if (slot != null) {

                if (slot.isOccupied()) {

                    System.out.print(ConsoleColors.RED
                            + "[" + slot.getSlotId() + "] "
                            + ConsoleColors.RESET);

                } else {

                    available++;

                    System.out.print(ConsoleColors.GREEN
                            + "[" + slot.getSlotId() + "] "
                            + ConsoleColors.RESET);
                }
            }

            if (i % 10 == 0) {
                System.out.println();
            }
        }

        System.out.println("Available: " + available
                + " / " + count);
    }

    // Search vehicle
    public void searchVehicle(String number) {

        Vehicle vehicle = findVehicle(number);

        if (vehicle == null) {

            System.out.println("Vehicle not found.");
            return;
        }

        System.out.println("\n========== VEHICLE DETAILS ==========");

        vehicle.displayDetails();

        ParkingRecord record = findActiveRecord(number);

        if (record != null) {

            System.out.println("Status         : Parked");
            System.out.println("Parking Slot   : " + record.getSlotId());
            System.out.println("Entry Time     : "
                    + record.getEntryTimeText());

        } else {

            System.out.println("Status         : Not Parked");
        }
    }

    // Display complete parking history
    public void viewHistory() {

        System.out.println("\n========== PARKING HISTORY ==========");

        if (records.isEmpty()) {

            System.out.println("No parking history available.");
            return;
        }

        for (ParkingRecord record : records) {

            System.out.println("-----------------------------------");
            System.out.println("Vehicle Number : "
                    + record.getVehicleNumber());
            System.out.println("Slot ID        : "
                    + record.getSlotId());
            System.out.println("Entry Time     : "
                    + record.getEntryTimeText());
            System.out.println("Exit Time      : "
                    + record.getExitTimeText());
            System.out.println("Status         : "
                    + (record.isActive() ? "Parked" : "Exited"));
        }
    }

    // Display dashboard
    public void displayDashboard() {

        int occupiedTwo = 0;
        int occupiedFour = 0;

        for (ParkingSlot slot : slots) {

            if (slot.isOccupied()) {

                if (slot.getVehicleType().equals("Two-Wheeler")) {
                    occupiedTwo++;
                } else {
                    occupiedFour++;
                }
            }
        }

        int totalOccupied = occupiedTwo + occupiedFour;
        int totalAvailable = slots.size() - totalOccupied;

        System.out.println("\n========== SMARTPARK DASHBOARD ==========");
        System.out.println("Total Registered Users    : " + users.size());
        System.out.println("Total Registered Vehicles : " + vehicles.size());
        System.out.println("Total Parking Slots       : " + slots.size());
        System.out.println("Occupied Slots             : " + totalOccupied);
        System.out.println("Available Slots            : " + totalAvailable);

        System.out.println("\nTwo-Wheeler Slots:");
        System.out.println("Occupied  : " + occupiedTwo);
        System.out.println("Available : " + (30 - occupiedTwo));

        System.out.println("\nFour-Wheeler Slots:");
        System.out.println("Occupied  : " + occupiedFour);
        System.out.println("Available : " + (20 - occupiedFour));

        System.out.println("=========================================");
    }
}
