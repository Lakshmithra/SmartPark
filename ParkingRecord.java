
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingRecord {

    private String vehicleNumber;
    private String slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    private static final DateTimeFormatter FORMAT =
        DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Constructor for a new parking record
    public ParkingRecord(String vehicleNumber, String slotId) {

        this.vehicleNumber = vehicleNumber;
        this.slotId = slotId;
        this.entryTime = LocalDateTime.now();
        this.exitTime = null;
    }

    // Constructor for loading an existing record
    public ParkingRecord(String vehicleNumber, String slotId,
                         LocalDateTime entryTime,
                         LocalDateTime exitTime) {

        this.vehicleNumber = vehicleNumber;
        this.slotId = slotId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getSlotId() {
        return slotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public boolean isActive() {
        return exitTime == null;
    }

    public void closeRecord() {
        if (exitTime == null) {
            exitTime = LocalDateTime.now();
        }
    }

    public String getEntryTimeText() {
        return entryTime.format(FORMAT);
    }

    public String getExitTimeText() {
        if (exitTime == null) {
            return "Currently Parked";
        }
        return exitTime.format(FORMAT);
    }

    public String toFileString() {

        String exit = (exitTime == null)
                ? ""
                : exitTime.format(FORMAT);

        return vehicleNumber + "|" + slotId + "|"
                + entryTime.format(FORMAT) + "|" + exit;
    }
}
